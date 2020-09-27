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
      <Gabarits
        @weight="onWeight"
      />
      <Checkpoints />
      <Speed
        @speed="onSpeed"
      />
      <Additionals
        @supplementaries="onSupplementaries"
      />
      <Buttons
          :setSupply="setSupply"
      />
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
    data() {
      return {
        params: {
          fromDtm: '',
          fromGeozone: '',
          toGeozone: '',
          supplementaries: [],
          speed: 1,
          weight: ''
        },
      }
    },
    methods: {
      setSupply() {
        this.$router.push('/all').catch(()=>{})
        axios
            .post('http://localhost:9000/rest/getChain', this.params)
            .then(response => {
              this.$store.dispatch("LOAD_PARAMS", response);
            })
            .catch(error => {
              console.log(error.response)
            });
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
      },
      onSpeed(data) {
        this.params.speed = data
      },
      onWeight(data) {
        this.params.weight = data
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