<!-- import './App.CSS'
import CLOUDS from 'vanta/src/vanta.clouds' -->
<template>
  <div class="box">
    <div ref="vantaRef" class="boxx" >
    <div class="banner">
  <!-- <div class="loginbody"> -->
    <div class="logindata">
      <div class="logintext" >
        <h2>LOGIN IN SYSTEM</h2>
      </div>
      <div class="formdata">
        <el-form ref="form" :model="user" >
          <el-form-item prop="userId">
            <el-input
              v-model="user.userId"
              clearable
              placeholder="USER ID"
            ></el-input>
          </el-form-item>
          <el-form-item prop="userPwd">
            <el-input
              v-model="user.userPwd"
              clearable
              placeholder="PASSWORD"
              show-password
            ></el-input>
          </el-form-item>
        </el-form>
      </div>
      <div class="tool">
        <div>
          <el-checkbox v-model="checked" class="shouu" @change="remenber"
            >Remember pw</el-checkbox
          >
        </div>
        <div>
          <span class="shouu" @click="forgetpas">Forget pw？</span>
        </div>
      </div>
      <div class="butt">
        <el-button type="primary" round v-on:click="login">login</el-button>
        <el-button class="shou" round v-on:click="openDialog">register</el-button>
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
      user: {
      },
      userinfo:null
    };
  },
  //方法区
  methods: {
    checked(){

    },
    onSubmit: function () {
      
    },
    openDialog: function () {
      // this.dialogFormVisible = true;
      // this.user = {};
      this.$router.push("/register");
    },
    
    login: function () {
      console.log(this.user)
      this.$axios.post("/login",this.user).then(res=>{
        console.log(res.data.code)
        if(res.data.code ==0){
          console.log(res.data.msg)
          this.$message({
            showClose: true,
            message: res.data.msg,
            type: 'warning'
          })
        }else if(res.data.code ==1){
          
          this.$axios.get("/user/"+this.user.userId).then(res=>{
            if(res.data.code == 1){
              this.userinfo = res.data.data
              localStorage.setItem("user",JSON.stringify(this.userinfo))
              if(this.userinfo.userIsvip == 0){
                this.$router.push("/admin/index")
              }
              else{
                this.$router.push("/")
              }
            }
          })

          
        }
      })
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
        message: "请联系管理员",
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
  }
.boxx{
    width: 100%;
    height: 100%;
    justify-content: center;
    /* overflow: hidden; */
  }
</style>
