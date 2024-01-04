<template>
  <div class="userinfo">
    <h1>Your user Information</h1>
    <p>User ID: {{ user.userId }}</p>
    <p>User Name: {{ user.userName }}</p>
    <p>User Balance: {{ user.userBalance }}</p>
    <p>User Tel: {{ user.userTel }}</p>
    <p>User Description: {{ user.userDescription }}</p>
    <p>User Is VIP: {{ user.userIsvip }}</p>
    <el-form ref="form" :model="form" label-width="180px">
    <h1>Change your password</h1>
      <el-form-item label="Old password">
    <el-input type="password" v-model="form.old"></el-input>
      </el-form-item>
      <el-form-item label="New password">
    <el-input type="password" v-model="form.new"></el-input>
      </el-form-item>
      <el-form-item label="Confirm password">
    <el-input type="password" v-model="form.confirm"></el-input>
      </el-form-item>
      <el-form-item>

    <el-button type="primary" @click="changePwd">更改密码</el-button>
      <el-button type="primary" @click="addBalance">储值卡充值</el-button>
      </el-form-item>

  </el-form>
  <el-dialog title="储值卡充值" class="dialog-options" :visible.sync="addBalanceVisible">
            <p>请扫描二维码完成支付，完成支付后凭支付截图到酒店前台由管理人员完成充值</p>
            <img src="@/assets/wechat.png" alt="微信二维码" width="200px" height="200px">
            <img src="@/assets/alipay.png" alt="支付宝二维码" width="200px" height="200px">
        </el-dialog>
  </div>
  
</template>
  
</template>
  <script>
    export default {
      name: "my",
      data() {
        return {
          user: JSON.parse(localStorage.getItem('user')),
          form:{
          },
          addBalanceVisible:false
      }
      },
      mounted(){
        user= JSON.parse(localStorage.getItem('user'))
      },
      methods: {
        changePwd(){
          if(this.form.old==this.user.userPwd){
            if(this.form.new==this.form.confirm){
              this.user.userPwd=this.form.new
              this.$axios.put('/user',this.user).then(res=>{
                  localStorage.setItem('user',JSON.stringify(this.user))
                  this.$message({
                    message: '更改密码成功',
                    type: 'success'
                  });
              })
            }
            else{
              this.$message({
                message: '两次密码不一致',
                type: 'error'
              });
            }
          }
          else{
            this.$message({
              message: '旧密码错误',
              type: 'error'
            });
          }
        
        },
        addBalance(){
          this.addBalanceVisible=true;
        }
      }
    }
  </script>

  <style scoped>
  .userinfo{
    margin: 0 auto;
    width: 50%;
    text-align: center;
  }
  .userinfo h1{
    font-size: 30px;
    font-weight: 700;
    margin-bottom: 20px;
  }
  .userinfo p{
    font-size: 20px;
    font-weight: 500;
    margin-bottom: 10px;}

  </style>
  