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
      </el-form-item>

  </el-form>
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
          }
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
  