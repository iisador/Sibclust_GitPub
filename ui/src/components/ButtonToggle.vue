<template>
  <v-container fluid>
    <v-row>
      <v-col>
        <v-btn-toggle
            tile
            color="#505050"
            group
            mandatory
            active-class="active-button"
        >
          <v-btn value="left" block>Физическое лицо</v-btn>
          <v-btn value="center" block>Юридическое лицо</v-btn>
        </v-btn-toggle>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  name: "ButtonToggle"
}
</script>

<style lang="scss">
.container{
  max-width: 100vw;
  padding:0px;
}

.v-btn-toggle>.v-btn.v-btn--active {
  color: #FFFFFF;
  background-color: #505050 !important;
}

.v-btn-toggle>.v-btn:not(.v-btn--active) {
  border: 1px solid #C7C7C7 !important;
  color: #212121;
}
</style>