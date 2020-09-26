import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    geoZones: '',
    supplementary: ''
  },

  mutations: {
    GET_GEOZONES(state, payload) {
      state.geoZones = payload
    },
    GET_SUPLEMENTARY(state, payload) {
      state.supplementary = payload
    }
  },

  actions: {
    GET_GEOZONES: async ({commit}) => {
      await axios
        .get('/rest/geozones')
        .then(response => {
          commit('GET_GEOZONES',response)
        })
    },
    GET_SUPLEMENTARY: async ({commit}) => {
      await axios
        .get('/rest/supplementary')
        .then(response => {
          commit('GET_SUPLEMENTARY',response)
        })
    }
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
