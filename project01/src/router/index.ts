import Vue from 'vue'
import VueRouter, { RouteConfig } from 'vue-router'
import HomeView from '../views/HomeView.vue'


Vue.use(VueRouter)

const routes: Array<RouteConfig> = [
  // {
  //   path: '/',
  //   name: 'home',
  //   component: HomeView
  // },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  {
    path:'/emp',
    name:'emp',
    component:()=>import('../views/TestView.vue'),
    
  },
  {
    path:'/',
    name:'login',
    component:()=>import('../views/LoginMy.vue')
  }
]

const router = new VueRouter({
     routes
})
 


//导航守卫
router.beforeEach((to,form,next)=>{
  if(to.name !== 'login' && !localStorage.getItem("token"))
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
