<!-- import './App.CSS'
import CLOUDS from 'vanta/src/vanta.clouds' -->
<template>
    <div class="box">
      <div ref="vantaRef" class="boxx" >
      <div class="banner">
    <!-- <div class="loginbody"> -->
      <div class="logindata">
        <div class="logintext" >
          <h2>REGISTER</h2>
        </div>
        <div>
          <el-form  >
            
            <el-form-item prop="userId" style="margin-bottom: 10px;">
              <el-input
                v-model="user1.userId"
                placeholder="userId"
              ></el-input>
            </el-form-item>

            <el-form-item prop="userName" style="margin-bottom: 10px;">
              <el-input
                v-model="user1.userName"
                placeholder="userName"
              ></el-input>
            </el-form-item>
              <el-form-item prop="userPwd" style="margin-bottom: 10px;">
                <el-input
                  v-model="user1.userPwd"
                  placeholder="userPwd"
                ></el-input>
              </el-form-item>
              <el-form-item prop="userDescription" style="margin-bottom: 10px;">
                <el-input
                  v-model="user1.userDescription"
                  placeholder="userDescription"
                ></el-input>
              </el-form-item>
              <el-form-item prop="userBalance" style="margin-bottom: 10px;">
                <el-input
                  v-model="user1.userBalance"
                  placeholder="userBalance"
                ></el-input>
              </el-form-item>
              <el-form-item prop="userTel" style="margin-bottom: 10px;">
                <el-input
                  v-model="user1.userTel"
                  placeholder="userTel"
                ></el-input>
              </el-form-item>
              <el-form-item prop="userIsvip" style="margin-bottom: 10px;">
                <el-input
                  v-model="user1.user_isvip"
                  placeholder="userIsvip"
                ></el-input>
              </el-form-item>
              <el-button type="primary" @click="onSubmit">提交</el-button>
              <el-button @click="reset">重置</el-button>
          </el-form>
        </div>
        
        <div >
          
        </div>
      </div>
      
        </div>
      </div>
    </div>
  </template>
  
  <script>
  // import './App.CSS'
  import * as THREE from "three";
      import globe from "vanta/src/vanta.globe";
      import 'vanta/src/vanta.dots.js'
  export default {
    name: "login",
    data() {
      return {
        //定义数据的地方
        user1: {}
      };
    },
    //方法区
    methods: {
      checked(){
  
      },
      onSubmit: function () {
        
        console.log(this.user1)
        this.$axios.post("/user",this.user1).then(res=>{
            console.log(this.user1)
            if(res.data.code==1){
              this.$message({
                type: "success",
                message: "注册成功",
                showClose: true,
            });}
            else{
              this.$message({
                type: "error",
                message: "注册失败",
                showClose: true,
            });
            }
            this.$router.push("/login")
        })  
      },
      openDialog: function () {
        this.dialogFormVisible = true;
        this.user = {};
      },
      reset(){
        this.user1 = {}
      },

      App(){
        useEffect( () => {
        CLOUDS({
          el: '#vanta'
        })
      })
        
        return(
          <div className = "app">
            <div className="bg" id="vanta"></div>
            </div>
        )
  
      },
      remenber(data) {
        this.checked = data;
        if (this.checked) {
          localStorage.setItem("news", JSON.stringify(this.form));
        } else {
          localStorage.removeItem("news");
        }
      },
      forgetpas() {
        this.$message({
          type: "info",
          message: "功能尚未开发额😥",
          showClose: true,
        });
      },
      register() {},
    },
    mounted() {
      this.vantaEffect = globe({
                  el: this.$refs.vantaRef,
                  THREE: THREE,
              });
              // 修改颜色时 cells 需要全大写字母 可生效
               VANTA.GLOBE({
                  el: this.$refs.vantaRef,
                  mouseControls: true,
                  touchControls: true,
                  gyroControls: false,
                  minHeight: 200.00,
                  minWidth: 200.00,
                  scale: 1.00,
                  scaleMobile: 1.00,
                  color: 0x3fc9ff,
                  size: 0.60
              });
          },
          beforeDestroy() {
              if (this.vantaEffect) {
                  this.vantaEffect.destroy();
              }
          },
          watch: {
              loginForm: {
                  handler: function () {
                      this.error = ''
                  },
                  deep: true
              }
          },
      };
  </script>
  
  <style scoped>
  .loginbody {
    width: 100%;
    height: 100%;
    min-width: 1000px;
    background-image: url("../assets/login.png");
    background-size: 100% 100%;
    background-position: center center;
    overflow: auto;
    background-repeat: no-repeat;
    position: fixed;
    line-height: 100%;
    padding-top: 150px;
  }
  .logintext {
    margin-bottom: 20px;
    line-height: 100px;
    text-align: center;
    font-size: 35px;
    font-weight: bolder;
    color: white;
    text-shadow: 8px 8px 16px #40727b;
  }
  
  .logindata {
    width: 400px;
    height: 300px;
    transform: translate(-50%);
    margin-left: 50%;
    justify-content: center;
  }
  .tool {
    display: flex;
    justify-content: space-between;
    color: #e0e7ac;
    text-shadow: 4px 4px 8px #000000;
  }
  
  .butt {
    margin-top: 10px;
    text-align: center;
  }
  
  .shou {
    cursor: pointer;
    color: #606663;
  }
  .visitor {
    cursor: pointer;
    background-color: #606663;
    color: #fefcff;
  }
  .shouu {
    cursor: pointer;
    color: #bac5c0;
    text-shadow: 4px 4px 8px #252551;
  }
  .box {
    width: 100%;
    height: 100%;
    justify-content: center;
    /* position: relative; */
    
    
  }
  .banner {
      z-index: 999;
      position: absolute;
      top: 30%;
      left: 10%;
      color: #fff;
      justify-content: center;
      margin-top: -50px;
    }
  .boxx{
      width: 100%;
      height: 100%;
      justify-content: center;
      /* overflow: hidden; */
    }
  </style>
  