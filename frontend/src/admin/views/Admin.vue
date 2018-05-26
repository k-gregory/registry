<template>

  <div>
    <nav class="level toolbar box">
        <div class="level-item">
            <div class="title">Адмін | Автоматизована система виконавчого провадження</div>
        </div>
     </nav>
    <div class="container">
        <section>
            <b-tabs v-model="activeTab" :animated="false">
                <b-tab-item label="Виконавці та Реєстратори">
                    <ExecutantTable></ExecutantTable>
                </b-tab-item>

                <b-tab-item label="Державні виконавчі служби">
                    <FacilityTable></FacilityTable>
                </b-tab-item>

                <b-tab-item label="Стягувачі та Боржники">
                </b-tab-item>

                <b-tab-item label="Статистика">
                </b-tab-item>
            </b-tabs>
        </section>
    </div>
  </div>

</template>

<script lang="ts">
  import {Component, Vue} from 'vue-property-decorator';
  import ExecutantTable from '@/admin/components/ExecutantTable.vue'; // @ is an alias to /src
  import FacilityTable from '@/admin/components/FacilityTable.vue'; // @ is an alias to /src
  import {Executant} from '@/api/executant';

  @Component({
    components: {
      ExecutantTable,
      FacilityTable,
    },
  })
  export default class Admin extends Vue {
    public activeTab: number;


    constructor() {
        super();
        this.activeTab = 0;
    }
  }
</script>