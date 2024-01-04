<template>
  <div>
    <div v-if="transVisible">编辑餐食订单信息
    <el-input v-model="transData.mealorderId" placeholder="请输入订单号"></el-input>
    <el-input v-model="transData.mealorderTiming" placeholder="请输入预定时间"></el-input>
    <el-input v-model="transData.mealorderCost" placeholder="请输入订单金额"></el-input>
    <el-input v-model="transData.mealorderNumber" placeholder="请输入数量"></el-input>
    <el-input v-model="transData.mealId" placeholder="请输入餐食号"></el-input>
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
    <el-table-column fixed prop="mealorderId" label="订单号" width="140"></el-table-column>
    <el-table-column prop="mealorderTiming" label="预定时间" width="200" ></el-table-column>
    <el-table-column prop="mealorderCost" label="订单价格" width="200" ></el-table-column>
    <el-table-column prop="mealorderNumber" label="数量" width="250" ></el-table-column>
    <el-table-column prop="userId" label="预定人" width="140" ></el-table-column>
    <el-table-column prop="mealId" label="餐食号" width="140" ></el-table-column>
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
  name: "mealorder",
  methods: {
      findAll:function(){
        console.log(this.params)
        axios
        .get("/mealorder")
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
        this.$router.push("/admin/mealorderadd");
      },
      updateHandle(row) {
        console.log(row);
        this.transData.mealorderId=row.mealorderId;
        this.transData.mealorderTiming=row.mealorderTiming;
        this.transData.mealorderCost=row.mealorderCost;
        this.transData.mealorderNumber=row.mealorderNumber;
        this.transData.userId=row.userId;
        this.transData.mealId=row.mealId;
        this.transVisible=true;
      },
      updateHandle1(){
//    把TransData中的数据传给后台
        axios.put("/mealorder",this.transData).then(res=>{
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
        axios.delete("/mealorder/"+row.mealorderId).then(res=>{
          if(res.data.code==1){
            this.$message({
              type:'success',
              message: '删除成功'
            });
            this.findAll()
          }
        })
      },
      searchHandle(mealorderId){
        console.log(mealorderId);
        axios.get("/mealorder/"+mealorderId).then(res=>{
          if(res.data.code==1){
            this.transData=res.data.data
          }
          // 浏览器弹窗显示出查询到的信息，每个信息隔行显示
          // 构建弹窗显示的信息字符串
          let infoString = `mealorderID: ${this.transData.mealorderId}\n`;
          infoString += `mealorderTiming: ${this.transData.mealorderTiming}\n`;
          infoString += `mealorderCost: ${this.transData.mealorderCost}\n`;
          infoString += `mealorderNumber: ${this.transData.mealorderNumber}`;
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
      mealorderId:5,
      transData:{
        mealorderId:0,
        mealorderTiming:"",
        mealorderCost:0,
        mealorderNumber:0,
        userId:0,
        mealId:0

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

