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
checkpoint = torch.load('D:\\suncaper\\Project\\big-data\\SpringBoot\\hotel\\src\\main\\als_model_checkpoint.pth')
model.load_state_dict(checkpoint['model_state_dict'])
optimizer = optim.SGD(model.parameters(), lr=0.05)
optimizer.load_state_dict(checkpoint['optimizer_state_dict'])
model.eval()

uid = int(input())
inum = int(input())
for i in range(inum):
    iid = int(input())
    user_id = torch.tensor([uid])
    item_id = torch.tensor([iid])
    with torch.no_grad():
        prediction = model(user_id, item_id)
    print(prediction.item())
