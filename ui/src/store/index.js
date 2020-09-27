import Vue from 'vue'
import Vuex from 'vuex'
import VueCookies from "vue-cookies";

Vue.use(Vuex)
Vue.use(VueCookies)

export default new Vuex.Store({
  state: {
    geoZones: '',
    supplementary: '',
    params: ''
  },

  mutations: {
    GET_GEOZONES(state, payload) {
      VueCookies.set('geoZones', JSON.stringify(payload.data))
      state.geoZones = payload
    },
    GET_SUPLEMENTARY(state, payload) {
      VueCookies.set('supplementary', JSON.stringify(payload.data))
      state.supplementary = payload
    },
    LOAD_PARAMS(state, payload) {
      VueCookies.set('params', JSON.stringify(payload))
      state.params = payload
    },
  },

  actions: {
    GET_GEOZONES: async ({commit}) => {
      await axios
        .get('http://localhost:9000/rest/geozones')
        .then(response => {
          commit('GET_GEOZONES',response)
        })
    },
    GET_SUPLEMENTARY: async ({commit}) => {
      await axios
        .get('http://localhost:9000/rest/supplementary')
        .then(response => {
          commit('GET_SUPLEMENTARY',response)
        })
    },
    LOAD_PARAMS({commit}, payload) {
      commit('LOAD_PARAMS', payload)
    },
  },

  getters: {
    GET_GEOZONES(state) {
      return state.geoZones;
    },
    GET_SUPLEMENTARY(state) {
      return state.supplementary;
    }
  }
})
