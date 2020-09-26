import Vue from 'vue'
import VueRouter from 'vue-router'
import axios from 'axios'

axios.defaults.baseURL = '/'

Vue.use(VueRouter)

import Home from '../components/Main'
import Routes from '../components/Routes'

export default new VueRouter({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/all',
      name: 'all',
      component: Routes
    },
  ]
})
