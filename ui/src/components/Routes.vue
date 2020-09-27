<template>
  <div style="display: flex; flex-direction: row; align-items: baseline">
    <div style="width: 60%; margin-right: 40px">
      <h1>Маршрут и исполнители подобраны</h1>
      <img src="../assets/map.png" alt="" style="width: 100%">
      <div style="display: flex; width: 100%; flex-direction: row; justify-content: space-around">
        <div>
          <h4 class="subtitle">Дата и время забора</h4>
          <p>15 Окт 2020</p>
        </div>
        <div>
          <h4 class="subtitle">Дата и время доставки</h4>
          <p>21 Окт 2020</p>
        </div>
      </div>
    </div>
    <div style="width: 30%;">
      <h3>Список исполнителей</h3>
      <v-flex v-for="param in params.data.chains" :key="param.id">
        <v-card
            class="mx-auto getcard"
            width="100%"
        >
          <v-card-actions class="card-top">
            <div style="display: flex; flex-direction: column;">
              <h4>{{ param.geozoneId }}</h4>
              <p>Исполнитель: {{ param.name }}</p>
              <p>Рейтинг: {{ param.rating }}</p>
              <a href="#" style="color: #1771E6; text-decoration: none">Изменить исполнителя</a>
            </div>
            <v-btn
                icon
                @click="param.id = !param.id"
            >
              <v-icon>{{ show1 ? 'mdi-chevron-up' : 'mdi-chevron-down' }}</v-icon>
            </v-btn>
          </v-card-actions>

          <v-expand-transition>
            <div v-show="param.id">
              <v-divider></v-divider>

              <div style="display: flex; flex-direction: column; padding: 15px">
                <span>Вес: {{ param.weight }} кг.</span>
                <span>{{ param.serviceType }} {{ param.sum }} руб</span>
                <div style="display: flex; flex-direction: row; justify-content: space-between">
                  <span>Время исполнения: {{ param.time }} часов</span>
                </div>
              </div>
            </div>
          </v-expand-transition>
        </v-card>
      </v-flex>
      <span>Сумма заказа: {{ params.data.totalSum }} руб</span>
    </div>
  </div>
</template>

<script>
import VueCookies from "vue-cookies";

export default {
  name: "Routes",
  data: () => ({
    show1: true,
    show2: false,
    show3: false,
    show4: false,
    params: VueCookies.get("params")
  }),
}
</script>

<style lang="scss" scoped>
  .getcard {
    margin-bottom: 15px;
  }

  .card-top {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    padding: 15px;
    background-color: #F5F5F6
  }

  .subtitle {
    font-style: normal;
    font-weight: 500;
    font-size: 12px;
    line-height: 12px;
    color: #7A7A7A;
  }
</style>