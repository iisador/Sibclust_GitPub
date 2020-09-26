<template>
  <div id="main">
    <v-app>
      <h1>Подбор исполнителей</h1>
      <span>Заполните все поля для рассчёта оптимального маршрута и подбора подходящих исполнителей</span>
      <ButtonToggle />
      <Route
          @getDateParam='onGetDateParam'
          @fromGeozoneId="onFromGeozoneId"
          @toGeozoneId="onToGeozoneId"
      />
      <Gabarits />
      <Checkpoints />
      <Speed />
      <Additionals
        @supplementaries="onSupplementaries"
      />
      <Buttons :setSupply="setSupply"/>
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
      getDateParam() {
        console.log(this)
      }
    },
    data() {
      return {
        params: {
          fromDtm: '',
          fromGeozone: '',
          toGeozone: '',
          supplementaries: ''
        },
      }
    },
    methods: {
      setSupply() {
        // this.$router.push('/all').catch(()=>{})
        axios
            .post('http://localhost:9000/rest/getChain', this.params)
            .catch(error => {
              console.log(error.response)
            });
        console.log(this.params)
      },
      onGetDateParam(data) {
        this.params.fromDtm = data
      },
      onFromGeozoneId(data) {
        this.params.fromGeozone = data
      },
      onToGeozoneId(data) {
        this.params.toGeozone = data
      },
      onSupplementaries(data) {
        this.params.supplementaries = data
      }
    }
  }
</script>

<style scoped>

#main {
  display: flex;
  flex-direction: column;
  width: 875px;
}
</style>