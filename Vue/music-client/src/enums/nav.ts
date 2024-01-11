import {RouterName} from "./router-name";

export const enum NavName {
  Home = "首页",
  SongSheet = "商户",
  Singer = "朋友圈",
  Search = "搜索",
  Personal = "个人主页",
  // Setting = "搜索",
  SignIn = "登录",
  SignUp = "",
  SignOut = "退出",
}

// 左侧导航栏
export const HEADERNAVLIST = [
  {
    name: NavName.Home,
    path: RouterName.Home,
  },
  {
    name: NavName.SongSheet,
    path: RouterName.SongSheet,
  },
  {
    name: NavName.Singer,
    path: RouterName.Singer,
  },
  {
    name: NavName.Search,
    path: RouterName.MySearch,
  },
];

// 右侧导航栏
export const SIGNLIST = [
  {
    name: NavName.SignIn,
    path: RouterName.SignIn,
  },
  {
    name: NavName.SignUp,
    path: RouterName.SignUp,
  },
];

// 用户下拉菜单项
export const MENULIST = [
  {
    name: NavName.Personal,
    path: RouterName.Personal,
  },
  {
    name: NavName.SignOut,
    path: RouterName.SignOut,
  },
];
