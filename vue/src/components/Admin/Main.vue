<template>
  <el-container>
    <el-header style="height: 80px;">
      <el-row :gutter="20">
        <el-col :span="4">
          <div class="bgs" style="height: 80px; display: flex;line-height: 80px;" >
            <img
              src="../../assets/logo1.png"
              alt=""
              style="width: 80px; height: 80px"
            />
            <span>    酒店管理平台</span>
          </div>
        </el-col>
        <el-col :span="14" > 

          <el-tabs v-model="activeName" @tab-click="handleClick(activeName)"  >
            <el-tab-pane  name="first" >
              <span slot="label" style="color: #312c2c"><i class="el-icon-setting"></i> 管理</span>
            </el-tab-pane>
            <el-tab-pane  name="second" >
              <span slot="label" style="color: #312c2c"><i class="el-icon-s-goods"></i> 预定</span>
            </el-tab-pane>
            <el-tab-pane  name="third" >
              <span slot="label" style="color: #312c2c"><i class="el-icon-user"></i> 我的</span>
            </el-tab-pane>
            <el-tab-pane  name="fourth" >
              <span slot="label" style="color: #312c2c"><i class="el-icon-menu"></i> 首页</span>
            </el-tab-pane>
            
          </el-tabs>

          
          <!-- <img src="../../assets/icon1.svg" alt="" style="width: 40px;height: 40px;">
          <p>小李</p> -->
      </el-col>
        <el-col :span="6">
          <!-- <el-dropdown>
            <i class="el-icon-setting" style="margin-right: 15px"></i>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>查看</el-dropdown-item>
              <el-dropdown-item>新增</el-dropdown-item>
              <el-dropdown-item>删除</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown> -->
          <span>当前用户: {{userId}}</span>
        </el-col>
      </el-row>
    </el-header>
    <el-container>
      <el-aside width="220px">
        <el-menu @select="handleOpen">
          <el-submenu index=1>
            <template slot="title">
             <i class="el-icon-location"></i>
              <span>房间管理</span>
            </template>
            <el-menu-item-group>
              <el-menu-item index="/admin/room">房间列表</el-menu-item>
              <el-menu-item index="/admin/roomorder">房间预订列表</el-menu-item>
            </el-menu-item-group>
          </el-submenu>

          <el-submenu index=2>
            <template slot="title">
             <i class="el-icon-menu"></i>
              <span>点餐管理</span>
            </template>
            <el-menu-item-group>
              <el-menu-item index="/admin/meal">餐饮列表</el-menu-item>
              <el-menu-item index="/admin/mealorder">订单列表</el-menu-item>
            </el-menu-item-group>
          </el-submenu>

          <el-submenu index=3>
            <template slot="title">
             <i class="el-icon-document"></i>
              <span>设施管理</span>
            </template>
            <el-menu-item-group>
              <el-menu-item index="/admin/facility">设施列表</el-menu-item>
              <el-menu-item index="/admin/facilityorder">设施预定列表</el-menu-item>
            </el-menu-item-group>
          </el-submenu>

          <el-submenu index=4>
          <template slot="title">
             <i class="el-icon-user"></i>
              <span>用户管理</span>
            </template>
              <el-menu-item-group>
              <el-menu-item index="/admin/user">用户列表</el-menu-item>
              </el-menu-item-group>
              </el-submenu>


        </el-menu>
      </el-aside>
      <el-main>
        <router-view />
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {

  methods: {
    handleOpen(key, keyPath) {
      this.$router.push(key);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClick(val) {
       this.$router.push("/admin/index");
        console.log(val);
        if(val==="first"){
          //this.$router.push("/main/user1");
          this.$router.push("/admin/index");
          console.log("a");
        }
        if(val==="second"){
          this.$router.push("/book");
          console.log("b");
        }
        if(val==="third"){
          this.$router.push("/admin/my");
          console.log("c");
        }
        if(val==="fourth"){
          this.$router.push("/");
          console.log("d");
        }if(val==="fifth"){
          console.log("e");
          
        }
      }
  },
  mounted() {
    this.user = JSON.parse(localStorage.getItem("user"));
    this.userId = this.user.userId;
  },
  data() {
    return {
      loginUser: "",
      activeName:"first",
      src:"https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg",
      user:null,
      userId:null
    };
  },
};
</script>

<style scoped>
.bgs {
  background-color: #efefef;
  color: #333;
  line-height: 60px;
}
.visitor {
  cursor: pointer;
  background-color: #606663;
  color: #fefcff;
}
.el-header {
  height: 100px;
  background-color: #efefef;
  color: #333;
  line-height: 80px;
}

.el-aside {
  color: #333;
}
.el-aside::-webkit-scrollbar {display: none;}

.abc {
  height: 100%;
}


</style>
