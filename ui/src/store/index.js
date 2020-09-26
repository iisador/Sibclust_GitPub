import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    geoZones: ''
  },

  mutations: {
    GET_GEOZONES(state, payload) {
      state.geoZones = payload
    }
  },

  actions: {
    GET_GEOZONES: async ({commit}) => {
      await axios
        .get('/rest/geozones')
        .then(response => {
          commit('GET_GEOZONES',response)
        })
    }
  },

  getters: {
    GET_GEOZONES(state) {
      return state.geoZones;
    }
  }
})
