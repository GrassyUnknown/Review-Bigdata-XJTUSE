import json
import mysql.connector

# MySQL连接参数
db_config = {
    'host': 'localhost',
    'user': 'root',
    'password': '123456',
    'database': 'bigdata'
}
conn = mysql.connector.connect(**db_config)
cursor = conn.cursor()

# 执行MySQL语句
sql_statement = '''
insert into checkin (business_id, checkin_dates) 
select 
json_unquote(json_extract(json_body, '$.business_id')),
json_unquote(json_extract(json_body, '$.date'))
from json_checkin;
'''
cursor.execute(sql_statement)

# 关闭游标和连接
conn.commit()
cursor.close()
conn.close()



