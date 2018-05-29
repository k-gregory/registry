<template>
    <div>
        <nav class="level toolbar box">
            <div class="level-item">
                <div class="title">Реєстр | Автоматизована система виконавчого провадження</div>
            </div>
        </nav>
        <div class="container">
            <section>
                <b-tabs v-model="activeTab">
                    <b-tab-item label="Стягувачі та Боржники">
                        <SubjectTable></SubjectTable>
                    </b-tab-item>

                    <b-tab-item label="Провадження">
                        <EnforcementTable></EnforcementTable>
                    </b-tab-item>
                </b-tabs>
            </section>
        </div>
    </div>
</template>

<script lang="ts">
  import {Component, Vue} from 'vue-property-decorator';
  import SubjectTable from '@/register/components/SubjectTable.vue';
  import EnforcementTable from '@/register/components/EnforcementTable.vue';

  @Component({
    components: {SubjectTable, EnforcementTable},
  })
  export default class Admin extends Vue {
    public activeTab: number;


    constructor() {
      super();
      this.activeTab = 0;
    }
  }
</script>

<style scoped>

</style>