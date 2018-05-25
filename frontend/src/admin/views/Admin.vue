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
                    <ExecutantTable @rowClick="onRowClick"></ExecutantTable>
                </b-tab-item>

                <b-tab-item label="Стягувачі та Боржники">
                </b-tab-item>

                <b-tab-item label="Статистика">
                </b-tab-item>
            </b-tabs>
        </section>
    </div>
    <b-modal :active.sync="isEditUserModalActive">
        <ExecutantEditModal :executant="selectedUser"></ExecutantEditModal>
    </b-modal>
  </div>

</template>

<script lang="ts">
  import {Component, Vue} from 'vue-property-decorator';
  import ExecutantTable from '@/admin/components/ExecutantTable.vue'; // @ is an alias to /src
  import ExecutantEditModal from '@/admin/components/ExecutantEditModal.vue';
  import {Executant} from '@/api/executant';

  @Component({
    components: {
      ExecutantTable, ExecutantEditModal,
    },
  })
  export default class Admin extends Vue {
    public activeTab: number;

    public isEditUserModalActive: boolean;
    public selectedUser: Executant | null;

    constructor() {
        super();
        this.activeTab = 0;
        this.isEditUserModalActive = false;
        this.selectedUser = null;
    }

    public onRowClick(rowItem: Executant): void {
        this.selectedUser = rowItem;
        this.isEditUserModalActive = true;
    }
  }
</script>