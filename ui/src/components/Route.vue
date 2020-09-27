<template>
  <v-container fluid>
    <span>Маршрут</span>
    <v-row align="center">
      <div style="display: flex; width: 100%">
        <v-col class="d-flex" cols="12" sm="5">
          <v-select
              v-model="selectedItem.name"
              :items="zones"
              item-text="name"
              id="id"
              filled
              label="Откуда забрать?"
              @change="getSelectFrom(selectedItem.name)"
              dense
          ></v-select>
        </v-col>
        <v-col class="d-flex" sm="1" style="justify-content: center">
          <v-icon color="#1771E6">fas fa-exchange-alt</v-icon>
        </v-col>
        <v-col class="d-flex" cols="12" sm="5">
          <v-select
              v-model="selectedItemTo.name"
              :items="zonesTo"
              item-text="name"
              id="id"
              filled
              label="Куда доставить?"
              @change="getSelectTo(selectedItemTo.name)"
              dense
          ></v-select>
        </v-col>
      </div>
    </v-row>
    <v-row align="center">
      <v-col>
        <v-radio-group v-model="fromAdresse" :mandatory="true">
          <v-radio label="Отправить из отделения" value="fromPost" @click="getDateAndTime"></v-radio>
          <v-radio label="Забрать ко времени" value="FromTime" @click="getDateAndTime"></v-radio>
        </v-radio-group>
        <v-row>
          <v-col>
            <v-btn-toggle
                tile
                color="#505050"
                group
                active-class="active-button"
            >
              <v-menu
                  ref="menu"
                  v-model="menu"
                  :close-on-content-click="false"
                  :return-value.sync="date"
                  transition="scale-transition"
                  offset-y
              >
                <template v-slot:activator="{ on, attrs }">
                  <v-btn
                      style="display: flex; flex-direction: row; width: 105px; justify-content: end;"
                      :disabled="!enabled"
                  >
                    <v-text-field
                        v-model="date"
                        :getDateParam="getDateParam"
                        readonly
                        v-bind="attrs"
                        v-on="on"
                        style="width: 105px"
                    ></v-text-field>
                  </v-btn>
                </template>
                <v-date-picker v-model="date" no-title scrollable>
                  <v-spacer></v-spacer>
                  <v-btn text color="primary" @click="menu = false">Cancel</v-btn>
                  <v-btn text color="primary" @click="$refs.menu.save(date), getFromDate()">OK</v-btn>
                </v-date-picker>
              </v-menu>
              <v-menu
                  ref="firstTime"
                  v-model="firstTime"
                  :close-on-content-click="false"
                  :nudge-right="40"
                  :return-value.sync="time"
                  transition="scale-transition"
                  offset-y
                  max-width="290px"
                  min-width="290px"
              >
                <template v-slot:activator="{ on, attrs }">
                  <v-btn
                      style="display: flex; flex-direction: row; width: 65px; justify-content: end;"
                      :disabled="!enabled"
                  >
                    <v-text-field
                        v-model="time"
                        readonly
                        v-bind="attrs"
                        v-on="on"
                        style="width: 65px"
                    ></v-text-field>
                  </v-btn>
                </template>
                <v-time-picker
                    v-if="firstTime"
                    v-model="time"
                    full-width
                    @click:minute="$refs.firstTime.save(time)"
                ></v-time-picker>
              </v-menu>
              <v-menu
                  ref="secondTime"
                  v-model="secondTime"
                  :close-on-content-click="false"
                  :nudge-right="40"
                  :return-value.sync="time2"
                  transition="scale-transition"
                  offset-y
                  max-width="290px"
                  min-width="290px"
              >
                <template v-slot:activator="{ on, attrs }">
                  <v-btn
                      style="display: flex; flex-direction: row; width: 65px; justify-content: end;"
                      :disabled="!enabled"
                  >
                    <v-text-field
                        v-model="time2"
                        readonly
                        v-bind="attrs"
                        v-on="on"
                        style="width: 65px"
                    ></v-text-field>
                  </v-btn>
                </template>
                <v-time-picker
                    v-if="secondTime"
                    v-model="time2"
                    full-width
                    @click:minute="$refs.secondTime.save(time2)"
                ></v-time-picker>
              </v-menu>
            </v-btn-toggle>
          </v-col>
        </v-row>
      </v-col>
      <v-col>
        <v-radio-group v-model="toAdresse" :mandatory="true">
          <v-radio label="Доставить до отделения" value="toPost" @click="getToDateAndTime"></v-radio>
          <div style="display: flex; align-items: center">
            <v-radio label="Доставить ко времени" value="toTime" @click="getToDateAndTime"></v-radio>
            <v-tooltip v-model="show" bottom>
              <template v-slot:activator="{ on, attrs }">
                <v-btn icon v-bind="attrs" v-on="on">
                  <v-icon color="#1771E6" small style="margin-left: 5px">fas fa-ruble-sign</v-icon>
                </v-btn>
              </template>
              <span>Данная услуга предоставляется за отдельную плату</span>
            </v-tooltip>
          </div>
        </v-radio-group>
        <v-row>
          <v-col>
            <v-btn-toggle
                tile
                color="#505050"
                group
                active-class="active-button"
            >
              <v-menu
                  ref="toMenu"
                  v-model="toMenu"
                  :close-on-content-click="false"
                  :return-value.sync="toDate"
                  transition="scale-transition"
                  offset-y
              >
                <template v-slot:activator="{ on, attrs }">
                  <v-btn
                      style="display: flex; flex-direction: row; width: 105px; justify-content: end;"
                      :disabled="!toEnabled"
                  >
                    <v-text-field
                        v-model="toDate"
                        readonly
                        v-bind="attrs"
                        v-on="on"
                        style="width: 105px"
                    ></v-text-field>
                  </v-btn>
                </template>
                <v-date-picker v-model="toDate" no-title scrollable>
                  <v-spacer></v-spacer>
                  <v-btn text color="primary" @click="toMenu = false">Cancel</v-btn>
                  <v-btn text color="primary" @click="$refs.toMenu.save(toDate)">OK</v-btn>
                </v-date-picker>
              </v-menu>
              <v-menu
                  ref="toFirstTime"
                  v-model="toFirstTime"
                  :close-on-content-click="false"
                  :nudge-right="40"
                  :return-value.sync="toTime"
                  transition="scale-transition"
                  offset-y
                  max-width="290px"
                  min-width="290px"
              >
                <template v-slot:activator="{ on, attrs }">
                  <v-btn
                      style="display: flex; flex-direction: row; width: 65px; justify-content: end;"
                      :disabled="!toEnabled"
                  >
                    <v-text-field
                        v-model="toTime"
                        readonly
                        v-bind="attrs"
                        v-on="on"
                        style="width: 65px"
                    ></v-text-field>
                  </v-btn>
                </template>
                <v-time-picker
                    v-if="toFirstTime"
                    v-model="toTime"
                    full-width
                    @click:minute="$refs.toFirstTime.save(toTime)"
                ></v-time-picker>
              </v-menu>
              <v-menu
                  ref="toSecondTime"
                  v-model="toSecondTime"
                  :close-on-content-click="false"
                  :nudge-right="40"
                  :return-value.sync="toTime2"
                  transition="scale-transition"
                  offset-y
                  max-width="290px"
                  min-width="290px"
              >
                <template v-slot:activator="{ on, attrs }">
                  <v-btn
                      style="display: flex; flex-direction: row; width: 65px; justify-content: end;"
                      :disabled="!toEnabled"
                  >
                    <v-text-field
                        v-model="toTime2"
                        readonly
                        v-bind="attrs"
                        v-on="on"
                        style="width: 65px"
                    ></v-text-field>
                  </v-btn>
                </template>
                <v-time-picker
                    v-if="toSecondTime"
                    v-model="toTime2"
                    full-width
                    @click:minute="$refs.toSecondTime.save(toTime2)"
                ></v-time-picker>
              </v-menu>
            </v-btn-toggle>
          </v-col>
        </v-row>
      </v-col>
    </v-row>

  </v-container>
</template>

<script>
import {mapGetters} from "vuex";
import VueCookies from "vue-cookies";
import moment from 'moment'

export default {
  name: "Route",
  computed: {
    ...mapGetters(['GET_GEOZONES']),
    getGeoZones() {
      return this.GET_GEOZONES
    }
  },
  data: () => ({
    items: JSON.parse(VueCookies.get("geoZones")),
    itemsTo: JSON.parse(VueCookies.get("geoZones")),
    selectedItem: {
      id: '',
      name: ''
    },
    selectedItemTo: {
      id: '',
      name: ''
    },
    zones: [],
    zonesTo: [],
    select: { state: 'Florida', abbr: 'FL' },
    fromAdresse: 'fromPost',
    toAdresse: 'toPost',
    show: false,
    date: new Date().toISOString().substr(0, 10),
    toDate: new Date().toISOString().substr(0, 10),
    menu: false,
    toMenu: false,
    modal: false,
    firstTime: false,
    secondTime: false,
    toFirstTime: false,
    toSecondTime: false,
    menu3: false,
    time: null,
    time2: null,
    toTime: null,
    toTime2: null,
    enabled: false,
    toEnabled: false,
    getDateParam: null
  }),
  created() {
    this.items.map(item => {
      this.zones.push(item);
    })
    this.itemsTo.map(item => {
      this.zonesTo.push(item);
    })
  },

  mounted() {
    this.$store.dispatch('GET_GEOZONES');
  },
  methods: {
    getDateAndTime() {
      this.enabled = !this.enabled;
    },
    getToDateAndTime() {
      this.toEnabled = !this.toEnabled;
    },
    getSelectFrom(id) {
      const obj  = this.zones.filter(item=>item.name == id )[0]
      // const objTo  = this.zonesTo.filter(item=>item.name == id )[0]
      // console.log(objTo)
      this.selectedItem.id = obj.id
      // this.selectedItemTo.id = objTo.id
      this.$emit('fromGeozoneId', this.selectedItem.id)
      // this.$emit('toGeozoneId', this.selectedItemTo.id)
    },
    getSelectTo(id) {
      const objTo  = this.zonesTo.filter(item=>item.name == id )[0]
      this.selectedItemTo.id = objTo.id
      this.$emit('toGeozoneId', this.selectedItemTo.id)
    },
    getFromDate() {
      this.getDateParam = moment(this.date).format('DD.MM.YYYY');
      this.$emit('getDateParam', this.getDateParam)
    }
  }
}
</script>

<style lang="scss">
.v-text-field__details {
  display: none !important;
}

.theme--light.v-text-field>.v-input__control>.v-input__slot:before {
  border: none !important;
}
  //.v-text-field>.v-input__control>.v-input__slot:before,
  //.theme--light.v-text-field:not(.v-input--has-state):hover>.v-input__control>.v-input__slot:before,
  //.theme--light.v-text-field>.v-input__control>.v-input__slot:before {
  //  content: '';
  //  border: none;
  //}
</style>