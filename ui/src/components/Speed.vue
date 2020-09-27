<template>
  <v-container>
    <span>Скорость доставки</span>
    <v-card
        width="100%"
    >
      <v-list shaped style="padding: 0">
        <v-list-item-group
            v-model="model"
            style="display: flex; flex-direction: row"
        >
          <template v-for="(item, i) in items">
            <v-divider
                v-if="!item"
                :key="`divider-${i}`"
            ></v-divider>

            <v-list-item
                v-else
                :key="`item-${i}`"
                :value="item"
                active-class="deep-purple--text text--accent-4"
                style="border-right: 1px solid #C7C7C7 !important;"
                @click="getSelect(item.id)"
            >
              <template v-slot:default="{ active }">
                <v-list-item-action>
                  <v-radio
                      :input-value="active"
                      color="deep-purple accent-4"
                  ></v-radio>
                </v-list-item-action>
                <v-list-item-content>
                  <v-list-item-title v-text="item.val"></v-list-item-title>
                </v-list-item-content>
              </template>
            </v-list-item>
          </template>
        </v-list-item-group>
      </v-list>
    </v-card>
  </v-container>

</template>

<script>
export default {
  name: "Speed",
  data: () => ({
    items: [
      {
        id: 1,
        val: '1-3 дня'
      },
      {
        id: 2,
        val: '3-10 дней'
      },
      {
        id: 3,
        val: '10+ дней'
      },
    ],
    model: ['Carrots'],
  }),
  methods: {
    getSelect(item) {
      this.$emit('speed', item)
    }
  }
}
</script>

<style lang="scss" scoped>
.v-application--is-ltr .v-list.v-sheet--shaped .v-list-item, .v-application--is-ltr .v-list.v-sheet--shaped .v-list-item:before, .v-application--is-ltr .v-list.v-sheet--shaped .v-list-item>.v-ripple__container {
  border: none !important;
  border-bottom-right-radius: 0px!important;
  border-top-right-radius: 0px!important;
}
</style>