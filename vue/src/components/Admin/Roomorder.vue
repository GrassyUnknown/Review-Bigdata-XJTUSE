<template>
  <div>
    <div v-if="transVisible">编辑房间订单信息
    <el-input v-model="transData.roomorderId" placeholder="请输入订单号"></el-input>
    <el-input v-model="transData.roomorderReservedate" placeholder="请输入预定日期"></el-input>
    <el-input v-model="transData.roomorderCost" placeholder="请输入订单金额"></el-input>
    <el-input v-model="transData.roomorderDescription" placeholder="请输入订单描述"></el-input>
    <el-input v-model="transData.roomId" placeholder="请输入房间号"></el-input>
    <el-input v-model="transData.userId" placeholder="请输入预定人"></el-input>
    <el-button type="primary" @click="updateHandle1">修改</el-button>
    </div>
    <div v-if="!transVisible">
      <div class="flex-container">
    
        <el-input v-model="transData.userId" style="width: 200px;margin-bottom:20px" placeholder="请输入订单号"></el-input>
        <el-button type="primary" style="color: aliceblue;margin-left:15px;margin-bottom:20px" @click="search(searchUserId)">查询</el-button>
    </div>

    <el-table
    :data="tableData"
    border
    style="width: 100%">
    <el-table-column fixed prop="roomorderId" label="订单号" width="140"></el-table-column>
    <el-table-column prop="roomorderReservedate" label="预定日期" width="200" ></el-table-column>
    <el-table-column prop="roomorderCost" label="订单价格" width="200" ></el-table-column>
    <el-table-column prop="roomorderDescription" label="订单描述" width="250" ></el-table-column>
    <el-table-column prop="userId" label="预定人" width="140" ></el-table-column>
    <el-table-column prop="roomId" label="房间号" width="140" ></el-table-column>
    <el-table-column fixed="right" label="操作" width="100"><template slot-scope="scope">
        <el-button @click="deleteHandle(scope.row)" type="text" size="small">删除</el-button>
        <!-- <el-button @click="updateHandle(scope.row)" type="text" size="small">编辑</el-button> -->
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
  name: "roomorder",
  methods: {
      findAll:function(){
        console.log(this.params)
        axios
        .get("/roomorder")
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
      addHandle() {
        this.$router.push("/admin/roomorderadd");
      },
      updateHandle(row) {
        console.log(row);
        this.transData.roomorderId=row.roomorderId;
        this.transData.roomorderReservedate=row.roomorderReservedate;
        this.transData.roomorderCost=row.roomorderCost;
        this.transData.roomorderDescription=row.roomorderDescription;
        this.transData.userId=row.userId;
        this.transData.roomId=row.roomId;
        this.transVisible=true;
      },
      updateHandle1(){
//    把TransData中的数据传给后台
        axios.put("/roomorder",this.transData).then(res=>{
          if(res.data.code==1){
            this.$message({
              type:'success',
              message: '修改成功'
            });
            this.findAll()
          }
        })
        this.transVisible=false;
      },
      deleteHandle(row) {
        console.log(row);
        axios.delete("/roomorder/"+row.roomorderId).then(res=>{
          if(res.data.code==1){
            this.$message({
              type:'success',
              message: '删除成功'
            });
            this.findAll()
          }
        })
      },
      searchHandle(roomorderId){
        console.log(roomorderId);
        axios.get("/roomorder/"+roomorderId).then(res=>{
          if(res.data.code==1){
            this.transData=res.data.data
          }
          // 浏览器弹窗显示出查询到的信息，每个信息隔行显示
          // 构建弹窗显示的信息字符串
          let infoString = `roomorderID: ${this.transData.roomorderId}\n`;
          infoString += `roomorderReservedate: ${this.transData.roomorderReservedate}\n`;
          infoString += `roomorderCost: ${this.transData.roomorderCost}\n`;
          infoString += `roomorderDescription: ${this.transData.roomorderDescription}`;
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
      roomorderId:5,
      transData:{
        roomorderId:0,
        roomorderReservedate:"",
        roomorderCost:0,
        roomorderDescription:"",
        userId:0,
        roomId:0

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

