import { createRouter, createWebHistory } from 'vue-router'

import layout from '@/views/layout/pageLayout.vue'


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {path: '/login', component: () => import('@/views/login/loginPage.vue')},
    {path: '/404', component: () => import('@/views/404.vue')},
    {
      path:"/",
      component:layout,
      redirect:"/home",
      children:[{
        path:'home',
        name:'home',
        component:()=>import('@/views/home/homePage.vue'),
        meta:{title:'首页'}
      },{
        path:'roomlist',
        name:'roomlist',
        component:()=>import('@/views/roomlist/roomList.vue'),
        meta:{title:'直播间列表'}
      },{
        path:'movielist',
        name:'movielist',
        component:()=>import('@/views/movielist/movieList.vue'),
        meta:{title:'放映厅列表'}
      },{
        path:'about',
        name:'about',
        component:()=>import('@/views/about/about.vue'),
        meta:{title:'关于页'}
      }
    ]
    },
    {
      path:'/room/:id',
      name:'livingRoom',
      component:()=>import('@/views/roomlist/roomLiving.vue'),
      meta:{title:'直播间'}
    },

    {
      path: '/movie/:id',
      name: 'movieDetail',
      component: () => import('@/views/movielist/movieDetail.vue'),
      meta: { title: '电影详情页' }
    },
    {path:'/:chapters*',redirect:'/404'}
  ]
})

//必须登录才能访问
// router.beforeEach((to,from,next) =>{
//   //获取用户信息
//   //跳转到首页
//   next({path:'/'})
//   next('/login')
// })

export default router
