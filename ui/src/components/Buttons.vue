<template>
  <v-container fluid class="pa-0">
    <div>
      <v-btn
          color="#FFF5C0"
          width="202"
          height="56"
          tile
          retain-focus-on-click="false"
          style="border: none"
          @click="setSupply"
      >
        Подобрать
      </v-btn>
    </div>
  </v-container>
</template>

<script>
export default {
  name: "Buttons",
  props: ['setSupply'],
}
</script>

<style scoped>

</style>