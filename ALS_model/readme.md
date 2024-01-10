# 1. 模型介绍

## 1.1 作用

该模型的作用是根据review表中已知的用户对商家的评分，预测每个用户对应每个商家的评分，用于搜索推荐系统，仅对活跃用户和热门商家有效

## 1.2 数据范围

该模型根据review表，从200万个用户中筛选了发表评论数前2000的活跃用户，从15万商家中筛选了被评论数前1万的热门商家，从700万条review中筛选活跃用户评论热门商家的评论（评分字段）用作训练数据，共25万条。这种数据清洗方式有以下两点好处：

1. 数据量有所减小，能使用个人电脑进行训练，训练速度较快，可以反复调整参数
2. 涉及用户和商家均在训练过程中多次出现，预测结果有理有据更加准确，否则涉及不发表或极少发表评论的用户和冷门商家时，预测评分结果偏差极大

数据清洗在MySQL中进行，通过对应字段添加索引大幅提高MySQL性能

## 1.3 模型选择

使用PyTorch深度学习库中的ALS模型，理由如下：

1. ALS模型是推荐算法中的常用模型，对预测评分有良好的效果

2. 相比Spark只能使用CPU训练，PyTorch可以使用GPU进行深度学习，更好地发挥计算机性能，以得到更好的效果


# 2. 代码及注释

```
import torch
import torch.nn as nn
import torch.optim as optim
from torch.utils.data import DataLoader, TensorDataset
import mysql.connector
import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import LabelEncoder
import matplotlib.pyplot as plt

# 替换以下信息为你的MySQL连接信息
DB_USERNAME = 'root'
DB_PASSWORD = '123456'
DB_HOST = 'localhost'
DB_PORT = '3306'
DB_NAME = 'bigdata'

# 创建MySQL连接
connection = mysql.connector.connect(
    host=DB_HOST,
    user=DB_USERNAME,
    password=DB_PASSWORD,
    port=DB_PORT,
    database=DB_NAME
)

# 从MySQL数据库中读取数据
query = "SELECT user_index, business_index, rev_stars FROM reviewppro"
data = pd.read_sql(query, connection)

# 划分训练集和测试集
train_df, test_df = train_test_split(data, test_size=0.1, random_state=42)

# 数据转换
user_indices = torch.tensor(train_df['user_index'].values, dtype=torch.long)
business_indices = torch.tensor(train_df['business_index'].values, dtype=torch.long)
ratings = torch.tensor(train_df['rev_stars'].values, dtype=torch.float)

# 移动数据到GPU上
device = torch.device("cuda")
user_indices = user_indices.to(device)
business_indices = business_indices.to(device)
ratings = ratings.to(device)

# 定义ALS模型
class ALSModel(nn.Module):
    def __init__(self, num_users, num_items, embedding_dim):
        super(ALSModel, self).__init__()
        self.user_embeddings = nn.Embedding(num_users, embedding_dim)
        self.item_embeddings = nn.Embedding(num_items, embedding_dim)

    def forward(self, user_indices, item_indices):
        user_factors = self.user_embeddings(user_indices)
        item_factors = self.item_embeddings(item_indices)
        return torch.sum(user_factors * item_factors, dim=1)

# 创建ALS模型实例
num_users = 2001
num_items = 10001
embedding_dim = 5  
als_model = ALSModel(num_users, num_items, embedding_dim)
als_model.to(device)

# 定义损失函数和优化器
criterion = nn.MSELoss()
optimizer = optim.SGD(als_model.parameters(), lr=0.05)

# 将训练数据转换为PyTorch DataLoader
train_dataset = TensorDataset(user_indices, business_indices, ratings)
train_loader = DataLoader(train_dataset, batch_size=32, shuffle=True)

# 训练ALS模型
num_epochs = 50
losses = []
for epoch in range(num_epochs):
    for batch in train_loader:
        user_idx, item_idx, rating = batch
        optimizer.zero_grad()
        predictions = als_model(user_idx, item_idx).squeeze()
        loss = criterion(predictions, rating)
        loss.backward()
        optimizer.step()

    print(f'Epoch {epoch + 1}, Loss: {loss.item()}')
    losses.append(loss.item())

# 保存模型
torch.save({
    'model_state_dict': als_model.state_dict(),
    'optimizer_state_dict': optimizer.state_dict(),
}, 'als_model_checkpoint.pth')

# 模型评估
test_user_indices = torch.tensor(test_df['user_index'].values, dtype=torch.long)
test_business_indices = torch.tensor(test_df['business_index'].values, dtype=torch.long)
test_ratings = torch.tensor(test_df['rev_stars'].values, dtype=torch.float)

# 移动测试数据到GPU上
test_user_indices = test_user_indices.to(device)
test_business_indices = test_business_indices.to(device)
test_ratings = test_ratings.to(device)

# 使用训练好的模型进行预测
test_predictions = als_model(test_user_indices, test_business_indices).squeeze()
print(test_predictions)

# 计算RMSE
test_rmse = torch.sqrt(criterion(test_predictions, test_ratings))
print(f'Test RMSE: {test_rmse.item()}')

# 绘制loss-epoch图
plt.plot(range(1, num_epochs + 1), losses, marker='o')
plt.xlabel('Epoch')
plt.ylabel('Loss')
plt.title('Training Loss over Epochs')
plt.show()
```

# 3. 参数设置

采用绘制loss-epoch图的方式直观感受参数对模型的影响，进行多次尝试取最佳值。

附件图片名称意义依次为以下四个参数。

## 3.1 embedding_dim

`embedding_dim` 是嵌入层的维度，用于将离散的用户和物品索引映射到连续的低维空间。在协同过滤等推荐系统中，用户和物品通常被表示为嵌入向量，而这个参数就定义了嵌入向量的维度。通过学习这些嵌入向量，模型可以捕捉用户和物品之间的隐含关系。过低的维度会造成模型准确度不够，过高的维度会过拟合影响泛化性能，本模型设置为5

## 3.2 lr

`lr` 是学习率（learning rate），它是优化算法中的一个超参数，决定了在每次参数更新中应用的步长大小。学习率越大，模型参数更新得越快，但可能会导致模型不稳定。学习率越小，模型更新得越慢，但更稳定。选择适当的学习率是优化算法的关键，通常需要通过实验来确定。本模型中由于计算机性能有限，需要较快收敛，设置为0.05

## 3.3 num_epoches

`num_epochs` 是训练过程中的轮数，表示整个训练数据集被完整遍历的次数。增加训练轮数可能有助于模型更好地学习数据的模式，但过多的训练轮次可能导致过拟合。通常使用验证集的性能来确定合适的训练轮次。根据loss-epoch图，在50次时基本已经收敛

## 3.4 rmse

`rmse` 是均方根误差（Root Mean Square Error），通常用于评估回归模型的性能。在推荐系统中，`rmse` 可以用来度量模型预测评分与实际评分之间的差异。`rmse` 越小，表示模型的预测越准确。其计算方式是对预测值与实际值的差异取平方，求平均，然后取平方根。在推荐系统中，`rmse` 的计算可以帮助评估模型的准确性和泛化性。比较各次训练得到模型的rmse，在参数按上述进行设置时，得到最优的值为1.03







