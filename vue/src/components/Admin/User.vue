<template>
  <div>
    <div v-if="transVisible">编辑客户信息
    <el-input v-model="transData.userId" placeholder="请输入客户编号"></el-input>
    <el-input v-model="transData.userName" placeholder="请输入客户姓名"></el-input>
    <el-input v-model="transData.userBalance" placeholder="请输入账户余额"></el-input>
    <el-input v-model="transData.userTel" placeholder="请输入客户电话"></el-input>
    <el-input v-model="transData.userPwd" placeholder="请输入客户密码"></el-input>
    <el-input v-model="transData.userDescription" placeholder="请输入客户描述"></el-input>
    <el-input v-model="transData.userIsvip" placeholder="请输入客户VIP状态"></el-input>
    <el-button type="primary" @click="updateCustomer1">修改</el-button>
    </div>

    <div v-if="!transVisible">
      <div class="flex-container">
    <el-popconfirm
        confirm-button-text="是"
        cancel-button-text="否"
        :icon="InfoFilled"
        icon-color="#626AEF"
        title="是否前往添加页面？"
        @confirm="addCustomer"
        @cancel="cancelEvent"
        >
        <el-button type="primary" style="color: aliceblue;margin-left:15px;margin-right:25px;margin-bottom:20px" slot="reference">添加客户</el-button>
    </el-popconfirm>
    
        <el-input v-model="transData.userId" style="width: 200px;margin-bottom:20px" placeholder="请输入客户编号"></el-input>
        <el-button type="primary" style="color: aliceblue;margin-left:15px;margin-bottom:20px" @click="searchCustomer(searchUserId)">查询</el-button>
    </div>


    



    <el-table
    :data="tableData"
    border
    style="width: 100%">
    <el-table-column fixed prop="userId" label="客户编号" width="130"></el-table-column>
    <el-table-column prop="userName" label="客户姓名" width="150" ></el-table-column>
    <el-table-column prop="userBalance" label="账户余额" width="150" ></el-table-column>
    <el-table-column prop="userTel" label="客户电话" width="180" ></el-table-column>
    <el-table-column prop="userPwd" label="客户密码" width="180" ></el-table-column>
    <el-table-column prop="userDescription" label="客户描述" width="200" ></el-table-column>
    <el-table-column prop="userIsvip" label="客户VIP状态" width="100" ></el-table-column>
    <el-table-column fixed="right" label="操作" width="100"><template slot-scope="scope">
        <el-button @click="deleteCustomer(scope.row)" type="text" size="small">删除</el-button>
        <el-button @click="updateCustomer(scope.row)" type="text" size="small">编辑</el-button>
      </template>
    </el-table-column>
  </el-table>
  


  <div class="block">
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="params.page"
      :page-sizes="pageSizes"
      :page-size="params.size"
      layout="total, sizes, prev, pager, next, jumper"
      :total="params.total">
    </el-pagination>
  </div>
  </div>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  name: "user",
  methods: {
      findAll:function(){
        console.log(this.params)
        axios
        .get("/user")
        .then(res=>{
        if(res.data.code==1){
          this.tableData=res.data.data
          console.log(this.tableData)
          this.params.total=res.data.total
        }
      });
      },
      handleSizeChange(val) {
        console.log(`每页 ${val} 条`);
        this.params.size=val
        this.findAll()
      },
      handleCurrentChange(val) {
        console.log(`当前页: ${val}`);
        this.params.page=val
        this.tableData=[]
        console.log(this.params)
        this.findAll()
      },
      addCustomer() {
        this.$router.push("/admin/useradd");
      },
      updateCustomer(row) {
        console.log(row);
        this.transData.userId=row.userId;
        this.transData.userName=row.userName;
        this.transData.userBalance=row.userBalance;
        this.transData.userTel=row.userTel;
        this.transData.userPwd=row.userPwd;
        this.transData.userDescription=row.userDescription;
        this.transData.userIsvip=row.userIsvip;
        this.transVisible=true;
      },
      updateCustomer1(){
//    把TransData中的数据传给后台
        axios.put("/user",this.transData).then(res=>{
          if(res.data.code==1){
            this.$message({
              type:'success',
              message: '修改成功'
            });
            this.findAll()
          }
          else if(res.data.code==0){
            this.$message({
              type:'warning',
              message: '修改失败'
            });
          }
        })
        this.transVisible=false;
      },
      deleteCustomer(row) {
        console.log(row);
        axios.delete("/user/"+row.userId).then(res=>{
          if(res.data.code==1){
            this.$message({
              type:'success',
              message: '删除成功'
            });
            this.findAll()
          }
          else if(res.data.code==0){
            this.$message({
              type:'warning',
              message: '删除失败'
            });
          }
        })
      },
      searchCustomer(userId){
        console.log(userId);
        axios.get("/user/"+userId).then(res=>{
          if(res.data.code==1){
            this.transData=res.data.data
          }
          // 浏览器弹窗显示出查询到的信息，每个信息隔行显示
          // 构建弹窗显示的信息字符串
          let infoString = `UserID: ${this.transData.userId}\n`;
          infoString += `UserName: ${this.transData.userName}\n`;
          infoString += `UserPwd: ${this.transData.userPwd}\n`;
          infoString += `UserDescription: ${this.transData.userDescription}`;
          // 使用浏览器弹窗显示信息
          window.alert(infoString);
          }
        )
      },

  },
  mounted() {
   this.findAll();
  },
  data() {
    return {
      tableData: [
      ],
      params:{
        page: 1,
        size: 10,
        total: 100,
      },
      pageSizes: [10,2,4,6,8],
      userId:5,
      transData:{
        userId:0,
        userName:"",
        userBalance:0,
        userTel:"",
        userPwd:"",
        userDescription:"",
        userIsvip:0
      }
      ,transVisible:false
    };
  },
};
</script>
<style scoped>
.flex-container {
  display: flex;
  justify-content: flex-end; /* 靠右对齐 */
  align-items: center; /* 垂直居中对齐 */
}

</style>

