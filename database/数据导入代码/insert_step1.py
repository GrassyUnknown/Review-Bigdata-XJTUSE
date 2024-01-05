import json
import mysql.connector

# MySQL连接参数
db_config = {
    'host': 'localhost',
    'user': 'root',
    'password': '123456',
    'database': 'bigdata'
}

# JSON文件路径
json_file_path = r'C:\Users\24040\Desktop\source\1sc\yelp_academic_dataset_checkin.json'

# 连接到MySQL数据库
conn = mysql.connector.connect(**db_config)
cursor = conn.cursor()

# 读取JSON文件并插入数据
with open(json_file_path, 'r', encoding='utf-8') as file:
    data = json.load(file)
    for entry in data:
        json_body = json.dumps(entry)  # 将JSON对象转换为字符串
        cursor.execute("INSERT INTO json_checkin (json_body) VALUES (%s)", (json_body,))

# 提交更改并关闭连接
conn.commit()
cursor.close()
conn.close()
