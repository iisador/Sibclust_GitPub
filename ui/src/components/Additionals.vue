<template>
 <v-container fluid>
   <span @click="getAdditionals" v-if="!additionals">{{ additionalsText }}</span>
   <span @click="getAdditionals" v-if="additionals">{{ additionalsTextHide }}</span>
   <v-icon color="#1771E6" small style="margin-left: 5px" v-if="!additionals">fas fa-angle-down</v-icon>
   <v-icon color="#1771E6" small style="margin-left: 5px" v-if="additionals">fas fa-angle-up</v-icon>
   <v-col v-if="additionals">
     <v-checkbox v-model="additionalsModel.lathing" class="mx-2" label="Обрешётка"></v-checkbox>
     <v-checkbox v-model="additionalsModel.backDocs" class="mx-2" label="Возврат сопроводительных документов"></v-checkbox>
     <v-checkbox v-model="additionalsModel.message" class="mx-2" label="СМС Уведомления"></v-checkbox>
     <v-checkbox v-model="additionalsModel.assemblyOfGoods" class="mx-2" label="Сборка товаров"></v-checkbox>
     <v-checkbox v-model="additionalsModel.dismantling" class="mx-2" label="Демонтаж бытовой техники"></v-checkbox>
     <v-checkbox v-model="additionalsModel.connection" class="mx-2" label="Подключение бытовой техники"></v-checkbox>
     <v-checkbox v-model="additionalsModel.interval" class="mx-2" label="Выбор интервала доставки"></v-checkbox>
     <v-checkbox v-model="additionalsModel.climbingToTheFloor" class="mx-2" label="Подъём на этаж"></v-checkbox>
     <v-checkbox v-model="additionalsModel.cargoHandling" class="mx-2" label="Погрузо-разгрузочные работы"></v-checkbox>
   </v-col>
 </v-container>
</template>

<script>
import { mdiChevronDown } from '@mdi/js';
export default {
  name: "Additionals",
  data: () => ({
    additionalsModel: {
      lathing: '',
      backDocs: '',
      message: '',
      assemblyOfGoods: '',
      dismantling: '',
      connection: '',
      interval: '',
      climbingToTheFloor: '',
      cargoHandling: '',
    },
    additionals: false,
    additionalsText: 'Показать дополнительные услуги',
    additionalsTextHide: 'Скрыть дополнительные услуги',
    model: [],
  }),
  methods: {
    getAdditionals() {
      this.additionals = !this.additionals;
    }
  }
}
</script>

<style scoped>

</style>