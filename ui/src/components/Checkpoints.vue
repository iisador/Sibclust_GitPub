<template>
  <v-container fluid>
    <span>Особые пометки</span>
    <v-row style="width: 45%">
      <v-col>
        <v-checkbox v-model="delicate" class="mx-2" label="Хрупкий груз"></v-checkbox>
      </v-col>
      <v-col>
        <v-checkbox v-model="danger" class="mx-2" label="Опасный груз"></v-checkbox>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  name: "Checkpoints",
  data: () => ({
    delicate: false,
    danger: false,
  }),
}
</script>

<style scoped>

</style>