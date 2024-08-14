import Vue from 'vue'
import VueRouter, { RouteConfig } from 'vue-router'
import Begain from '../views/Begain.vue'
import HomeView from '../views/HomeView.vue'
import store from '@/store'
import ShowMoney from '@/views/ShowMoney.vue'


Vue.use(VueRouter)

const routes: Array<RouteConfig> = [
  // {
  //   path: '/',
  //   name: 'home',
  //   component: HomeView
  // },
  {
    path: '/about',
    redirect:'/ab',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  {
    path:'/emp',
    name:'emp',
    component:()=>import('../views/TestView.vue')
  },
  {
    path:'/login',
    name:'login',
    component:()=>import('../views/LoginMy.vue')
  },
  {
    path:'/show/:words?',
    name:'showMoney',
    component:()=> import('../views/ShowMoney.vue')
  },
  {
    path:'/',
    name:'begian',
    component:()=> import('../views/Begain.vue')
  },
  {
    path:'*',
    name:'404',
    component: ()=>{'../views/AboutView.vue'}
  }
]

const router = new VueRouter({
     routes,
})
 


//导航守卫 !store.state.areLogin
router.beforeEach((to,form,next)=>{
  if(to.name === 'begian')
  {
    next()
  }
  else if((to.name !== 'login') && !localStorage.getItem("token"))
  {
     next({name:'login'})
  }
  else 
  {
    next();
  }
  console.log(to)
  console.log(form)
})


export default router
