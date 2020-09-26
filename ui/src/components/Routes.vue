<template>
  <div style="display: flex; flex-direction: row; align-items: baseline">
    <div style="width: 70%; margin-right: 40px">
      <h1>Маршрут и исполнители подобраны</h1>
      <img src="../assets/map.png" alt="" style="width: 100%">
      <div style="display: flex; width: 100%; flex-direction: row; justify-content: space-around">
        <div>
          <h4>Дата и время забора</h4>
          <p>15 Окт 2020</p>
        </div>
        <div>
          <h4>Дата и время доставки</h4>
          <p>21 Окт 2020</p>
        </div>
      </div>
    </div>
    <div>
      <h3>Список исполнителей</h3>
      <v-card
          class="mx-auto getcard"
          width="344"
      >
        <v-card-actions class="card-top">
          <div style="display: flex; flex-direction: column;">
            <h4 >Новосибирск</h4>
            <p>Исполнитель: Почта России</p>
            <a href="#" style="color: #1771E6; text-decoration: none">Изменить исполнителя</a>
          </div>
          <v-btn
              icon
              @click="show1 = !show1"
          >
            <v-icon>{{ show1 ? 'mdi-chevron-up' : 'mdi-chevron-down' }}</v-icon>
          </v-btn>
        </v-card-actions>

        <v-expand-transition>
          <div v-show="show1">
            <v-divider></v-divider>

            <div style="display: flex; flex-direction: column; padding: 15px">
              <span>Забор товара (15 Окт 2020, 10:30 - 11:00) - 200 руб</span>
              <span>Обрешётка - 350 руб</span>
              <span>СМС Уведомления - 40 руб</span>
              <span>Сумма: 599 руб</span>
            </div>
          </div>
        </v-expand-transition>
      </v-card>
      <v-card
          class="mx-auto getcard"
          width="344"
      >
        <v-card-actions class="card-top">
          <div style="display: flex; flex-direction: column;">
            <h4 >Тюмень</h4>
            <p>Исполнитель: Деловые линии</p>
            <a href="#" style="color: #1771E6; text-decoration: none">Изменить исполнителя</a>
          </div>
          <v-btn
              icon
              @click="show2 = !show2"
          >
            <v-icon>{{ show2 ? 'mdi-chevron-up' : 'mdi-chevron-down' }}</v-icon>
          </v-btn>
        </v-card-actions>

        <v-expand-transition>
          <div v-show="show2">
            <v-divider></v-divider>

            <div style="display: flex; flex-direction: column; padding: 15px">
              <span>Забор товара (15 Окт 2020, 10:30 - 11:00) - 200 руб</span>
              <span>Обрешётка - 350 руб</span>
              <span>СМС Уведомления - 40 руб</span>
              <span>Сумма: 599 руб</span>
            </div>
          </div>
        </v-expand-transition>
      </v-card>
      <v-card
          class="mx-auto getcard"
          width="344"
      >
        <v-card-actions class="card-top">
          <div style="display: flex; flex-direction: column;">
            <h4 >Воронеж</h4>
            <p>Исполнитель: Почта России</p>
            <a href="#" style="color: #1771E6; text-decoration: none">Изменить исполнителя</a>
          </div>
          <v-btn
              icon
              @click="show3 = !show3"
          >
            <v-icon>{{ show3 ? 'mdi-chevron-up' : 'mdi-chevron-down' }}</v-icon>
          </v-btn>
        </v-card-actions>

        <v-expand-transition>
          <div v-show="show3">
            <v-divider></v-divider>

            <div style="display: flex; flex-direction: column; padding: 15px">
              <span>Забор товара (15 Окт 2020, 10:30 - 11:00) - 200 руб</span>
              <span>Обрешётка - 350 руб</span>
              <span>СМС Уведомления - 40 руб</span>
              <span>Сумма: 599 руб</span>
            </div>
          </div>
        </v-expand-transition>
      </v-card>
      <v-card
          class="mx-auto getcard"
          width="344"
      >
        <v-card-actions class="card-top">
          <div style="display: flex; flex-direction: column;">
            <h4 >Москва</h4>
            <p>Исполнитель: Почта России</p>
            <a href="#" style="color: #1771E6; text-decoration: none">Изменить исполнителя</a>
          </div>
          <v-btn
              icon
              @click="show4 = !show4"
          >
            <v-icon>{{ show4 ? 'mdi-chevron-up' : 'mdi-chevron-down' }}</v-icon>
          </v-btn>
        </v-card-actions>

        <v-expand-transition>
          <div v-show="show4">
            <v-divider></v-divider>

            <div style="display: flex; flex-direction: column; padding: 15px">
              <span>Забор товара (15 Окт 2020, 10:30 - 11:00) - 200 руб</span>
              <span>Обрешётка - 350 руб</span>
              <span>СМС Уведомления - 40 руб</span>
              <span>Сумма: 599 руб</span>
            </div>
          </div>
        </v-expand-transition>
      </v-card>
    </div>
  </div>
</template>

<script>
export default {
  name: "Routes",
  data: () => ({
    show1: true,
    show2: false,
    show3: false,
    show4: false,
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
</style>