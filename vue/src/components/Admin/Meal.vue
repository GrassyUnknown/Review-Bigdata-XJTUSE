<template>
  <div>
    <div v-if="transVisible">编辑餐食信息
    <el-input v-model="transData.mealId" placeholder="请输入餐食号"></el-input>
    <el-input v-model="transData.mealName" placeholder="请输入餐食名"></el-input>
    <el-input v-model="transData.mealCost" placeholder="请输入餐食金额"></el-input>
    <el-input v-model="transData.mealDescription" placeholder="请输入餐食描述"></el-input>
    <el-button type="primary" @click="updateHandle1">修改</el-button>
    </div>
    <div v-if="!transVisible">
      <div class="flex-container">
      <el-popconfirm
          confirm-button-text="是"
          cancel-button-text="否"
          :icon="InfoFilled"
          icon-color="#626AEF"
          title="是否前往添加页面？"
          @confirm="addHandle"
          @cancel="cancelEvent"
          >
          <el-button type="primary" style="color: aliceblue;margin-left:15px;margin-right:25px;margin-bottom:20px" slot="reference">添加餐食</el-button>
      </el-popconfirm>
    
        <el-input v-model="transData.userId" style="width: 200px;margin-bottom:20px" placeholder="请输入餐食号"></el-input>
        <el-button type="primary" style="color: aliceblue;margin-left:15px;margin-bottom:20px" @click="search(searchUserId)">查询</el-button>
    </div>

    <el-table
    :data="tableData"
    border
    style="width: 100%">
    <el-table-column fixed prop="mealId" label="餐食号" width="200"></el-table-column>
    <el-table-column prop="mealName" label="餐食名" width="250" ></el-table-column>
    <el-table-column prop="mealCost" label="餐食价格" width="250" ></el-table-column>
    <el-table-column prop="mealDescription" label="餐食描述" width="400" ></el-table-column>
    <el-table-column fixed="right" label="操作" width="100"><template slot-scope="scope">
        <el-button @click="deleteHandle(scope.row)" type="text" size="small">删除</el-button>
        <el-button @click="updateHandle(scope.row)" type="text" size="small">编辑</el-button>
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
  name: "meal",
  methods: {
      findAll:function(){
        console.log(this.params)
        axios
        .get("/meal")
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
        this.$router.push("/admin/mealadd");
      },
      updateHandle(row) {
        console.log(row);
        this.transData.mealId=row.mealId;
        this.transData.mealName=row.mealName;
        this.transData.mealCost=row.mealCost;
        this.transData.mealDescription=row.mealDescription;
        this.transVisible=true;
      },
      updateHandle1(){
//    把TransData中的数据传给后台
        axios.put("/meal",this.transData).then(res=>{
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
        axios.delete("/meal/"+row.mealId).then(res=>{
          if(res.data.code==1){
            this.$message({
              type:'success',
              message: '删除成功'
            });
            this.findAll()
          }
        })
      },
      searchHandle(mealId){
        console.log(mealId);
        axios.get("/meal/"+mealId).then(res=>{
          if(res.data.code==1){
            this.transData=res.data.data
          }
          // 浏览器弹窗显示出查询到的信息，每个信息隔行显示
          // 构建弹窗显示的信息字符串
          let infoString = `mealID: ${this.transData.mealId}\n`;
          infoString += `mealName: ${this.transData.mealName}\n`;
          infoString += `mealCost: ${this.transData.mealCost}\n`;
          infoString += `mealDescription: ${this.transData.mealDescription}`;
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
      mealId:5,
      transData:{
        mealId:0,
        mealName:'',
        mealCost:0,
        mealDescription:''

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

