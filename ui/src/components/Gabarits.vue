<template>
  <v-container fluid>
    <span>Габариты</span>
    <v-row style="width: 70%">
      <v-col>
        <v-text-field label="Длина" filled></v-text-field>
      </v-col>
      <v-col>
        <v-text-field label="Высота" filled></v-text-field>
      </v-col>
      <v-col>
        <v-text-field label="Ширина" filled></v-text-field>
      </v-col>
      <v-col>
        <v-text-field label="Вес" filled></v-text-field>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  name: "Gabarits",
  data: () => ({
    rules: [
      value => !!value || 'Required.',
      value => (value && value.length >= 3) || 'Min 3 characters',
    ],
  })
}
</script>

<style scoped>

</style>