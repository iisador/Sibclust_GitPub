<template>
  <div id="main">
    <v-app>
      <h1>Подбор исполнителей</h1>
      <span>Заполните все поля для рассчёта оптимального маршрута и подбора подходящих исполнителей</span>
      <ButtonToggle />
      <Route
        v-for="(zone, index) in getGeoZones"
        :items="zone.name"
        :key="zone.id"
        :id="zone.id"
      />
      <Gabarits />
      <Checkpoints />
      <Speed />
      <Additionals />
      <Buttons />
    </v-app>
  </div>
</template>

<script>
  import ButtonToggle from './ButtonToggle'
  import Route from './Route'
  import Gabarits from './Gabarits'
  import Checkpoints from './Checkpoints'
  import Speed from './Speed'
  import Additionals from './Additionals'
  import Buttons from './Buttons'
  import { mapGetters } from 'vuex';

  export default {
    components: {
      ButtonToggle,
      Route,
      Gabarits,
      Checkpoints,
      Speed,
      Additionals,
      Buttons
    },

    computed: {
      ...mapGetters(['GET_GEOZONES']),
      getGeoZones() {
        return this.GET_GEOZONES
      }
    },

    mounted() {
      this.$store.dispatch('GET_GEOZONES');
    },
  }
</script>

<style scoped>

#main {
  display: flex;
  flex-direction: column;
  width: 875px;
}
</style>