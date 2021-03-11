import Vue from 'vue'
import VueRouter from 'vue-router'
// import Home from '../views/Home.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Main',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/BackgroundNav.vue'),
    children: [
      {
        path:'/',
        name: 'MainBook',
        component: () => import('../views/MainBook.vue')
      },
      {
        path:'/login',
        name: 'Login',
        component: () => import('../components/Login/Login.vue')
      },
      {
        path:'/signup',
        name: 'Signup',
        component: () => import('../components/Signup/Signup.vue'),
        children: [
          {
            path: '1',
            name: 'SubmitEmail',
            component: () => import('../components/Signup/SubmitEmail.vue'),
          },
          {
            path: '2',
            name: 'CheckEmail',
            component: () => import('../components/Signup/CheckEmail.vue'),
          },
          {
            path: '3',
            name: 'InputName',
            component: () => import('../components/Signup/InputName.vue'),
          },
          {
            path: '4',
            name: 'SubmitPref',
            component: () => import('../components/Signup/SubmitPref.vue'),
          },
          {
            path: '5',
            name: 'SignupComplete',
            component: () => import('../components/Signup/SignupComplete.vue'),
          },
        ]
      },
      {
        path:'/subsignup',
        name: 'SubSignup',
        component: () => import('../components/SubSignup/SubSignup.vue'),
        children: [
          {
            path: '1',
            name: 'SubmitGender',
            component: () => import('../components/SubSignup/SubmitGender.vue'),
          },
          {
            path: '2',
            name: 'SubmitBirth',
            component: () => import('../components/SubSignup/SubmitBirth.vue'),
          },
          {
            path: '3',
            name: 'SubmitJob',
            component: () => import('../components/SubSignup/SubmitJob.vue'),
          },
          {
            path: '4',
            name: 'SubmitPic',
            component: () => import('../components/SubSignup/SubmitPic.vue'),
          },
          {
            path: '5',
            name: 'SubSignComplete',
            component: () => import('../components/SubSignup/SubSignComplete.vue'),
          },
        ]
      },
    ]
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})
// router guard로 이메일 인증 건너뛰기 막아줘야함

export default router