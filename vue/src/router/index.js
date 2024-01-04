import Vue from 'vue'
import Router from 'vue-router'
import login from '@/components/login'
import register from '@/components/register'

import Index from '@/components/Admin/Index'
import Main from '@/components/Admin/Main'
import my from '@/components/Admin/my'
import usermy from '@/components/User/my'

import User from '@/components/Admin/User'
import Useradd from '@/components/Admin/Useradd'
import Room from '@/components/Admin/Room'
import Roomadd from '@/components/Admin/Roomadd'
import Meal from '@/components/Admin/Meal'
import Mealadd from '@/components/Admin/Mealadd'
import Facility from '@/components/Admin/Facility'
import Facilityadd from '@/components/Admin/Facilityadd'


import Roomorder from '@/components/Admin/Roomorder'
import Mealorder from '@/components/Admin/Mealorder'
import Facilityorder from '@/components/Admin/Facilityorder'

import index from '@/components/User/index'
import book from '@/components/User/book'
import bookpage from '@/components/User/bookpage'
import Gym from '@/components/User/Gym'
import Pool from '@/components/User/Pool'
import hot from '@/components/User/hot'
import bookmeal from '@/components/User/bookmeal'

import roompay from '@/components/User/roompay'

Vue.use(Router)

export default new Router({
  routes: [
    // 这是我们的首页面，入口页面。
    
    {
      path: '/',
      name: 'index',
      component: index
    },
    {
      path: '/my',
      name: 'usermy',
      component: usermy
    },
    {
      path: '/book',
      name: 'book',
      component: book
    },
    {
      path: '/bookpage',
      name: 'bookpage',
      component: bookpage
    },
    {
      path: '/Meal',
      name: 'Meal',
      component: Meal
    },
    {
      path: '/Gym',
      name: 'Gym',
      component: Gym
    },
    {
      path: '/Pool',
      name: 'Pool',
      component: Pool
    },
    {
      path: '/bookmeal',
      name: 'bookmeal',
      component: bookmeal
    },
    {
      path: '/hot',
      name: 'hot',
      component: hot
    },
    {
      path: '/roompay',
      name: 'roompay',
      component: roompay
    },
    {
      path: '/login',
      name: 'login',
      component: login
    },
    {
      path: '/register',
      name: 'register',
      component: register
    },
    {
      path:'/admin',
      name:"admin",
      component: Main,
      children:[
        {
          path:"index",
          name:'index',
          component: Index
        },{
          path:"user",
          name:'user',
          component: User
        },{
          path:"my",
          name:'my',
          component: my
        },{
          path:"room",
          name:'room',
          component: Room
        },{
          path:"roomadd",
          name:'roomadd',
          component: Roomadd
        },{
          path:"useradd",
          name:'useradd',
          component: Useradd
        },{
          path:"meal",
          name:'meal',
          component: Meal
        },{
          path:"mealadd",
          name:'mealadd',
          component: Mealadd
        },
        {
          path:"facility",
          name:'facility',
          component: Facility
        },{
          path:"facilityadd",
          name:'facilityadd',
          component: Facilityadd
        },{
          path:"facilityorder",
          name:'facilityorder',
          component: Facilityorder
        },
        {
          path:"roomorder",
          name:'roomorder',
          component: Roomorder
        },{
          path:"mealorder",
          name:'mealorder',
          component: Mealorder
        },
      ]
    }
  ]
})
