<template>
  <div id="app">
    <Main></Main>
    <router-view/>
  </div>
</template>

<script>
import Main from './components/Main'
export default {
  components: {
    Main
  }
}
</script>

<style lang="scss">
* {
  outline: none;
}

body {
  margin: 0;
  padding: 0;
  height: 100vh;
}

#app {
  display: flex;
  width: 100%;
  height: 100%;
  flex-direction: row;
  justify-content: center;
}

#map {
  position: absolute;
  top: 0;
  bottom: 0;
  width: 100%;
}

.mapboxgl-ctrl mapboxgl-ctrl-attrib {
  display: none;
}
</style>
