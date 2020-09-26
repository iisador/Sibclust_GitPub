import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import Vuetify from 'vuetify'
import moment from 'vuetify'
import 'vuetify/dist/vuetify.min.css'

window.axios = require('axios')

Vue.use(Vuetify)
Vue.use(moment);

const opts = {}

export default new Vuetify(opts)

Vue.config.productionTip = false

new Vue({
  router,
  store,
  vuetify : new Vuetify(),
  render: h => h(App)
}).$mount('#app')
