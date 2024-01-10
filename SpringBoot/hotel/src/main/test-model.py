import torch
import torch.nn as nn
import torch.optim as optim
import numpy as np

# 假设你的ALS模型定义如下
class ALSModel(nn.Module):
    def __init__(self, num_users, num_items, embedding_dim):
        super(ALSModel, self).__init__()
        self.user_embeddings = nn.Embedding(num_users, embedding_dim)
        self.item_embeddings = nn.Embedding(num_items, embedding_dim)

    def forward(self, user_indices, item_indices):
        user_factors = self.user_embeddings(user_indices)
        item_factors = self.item_embeddings(item_indices)
        return torch.sum(user_factors * item_factors, dim=1)

# 加载模型
num_users = 2001
num_items = 10001
embedding_dim = 5
model = ALSModel(num_users, num_items, embedding_dim)
checkpoint = torch.load('E:\\学\\大3-1\\生产实习\\big-data\\SpringBoot\\hotel\\src\\main\\als_model_checkpoint.pth')
model.load_state_dict(checkpoint['model_state_dict'])
optimizer = optim.SGD(model.parameters(), lr=0.05)
optimizer.load_state_dict(checkpoint['optimizer_state_dict'])
model.eval()

# rating = np.empty((2000, 10000), dtype=float)
# for uid in range(2000):
#     print(uid)
#     for iid in range(10000):
#         user_id = torch.tensor([uid+1])
#         item_id = torch.tensor([iid+1])
#         with torch.no_grad():
#             prediction = model(user_id, item_id)
#         rating[uid, iid] = prediction.item()

# # 创建HDF5文件并写入矩阵
# with h5py.File('rating.h5', 'w') as hdf5_file:
#     hdf5_file.create_dataset('rating_dataset', data=rating)
# 准备输入数据
user_id = torch.tensor([int(input())])
item_id = torch.tensor([int(input())])
# 进行预测
with torch.no_grad():
    prediction = model(user_id, item_id)

# 处理预测结果，例如打印或进行其他后处理操作
print("预测评分:", prediction.item())
