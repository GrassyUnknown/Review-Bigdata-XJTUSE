# 这些代码用于将json导入mysql

第一步，在原始json文件前后增加中括号，每行末尾增加逗号，方便mysql进行解析

第二步：新建json_xxx表，只有json_body一个字段，将json表导入到json_xxx表中

第三步：新建xxx表，将json_xxx中的各字段解析并导入xxx表中

注1：由于数据量较大，不方便使用可视化工具，故使用python脚本进行导入。

注2：导入后执行update user set host = '%' where user ='root'; 允许其他组员使用root访问数据库