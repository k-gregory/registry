<template>
    <section>
        <div class="columns is-vcentered is-mobile is-multiline">
            <div class="column is-1-desktop is-2-mobile">
                <div class="label">
                    Пошук
                </div>
            </div>
            <div class="column is-6-desktop is-10-mobile">
                <b-input class="level-item" v-model="query"></b-input>
            </div>
            <div class="column is-1-desktop is-offset-4-desktop is-full-mobile">
                <button class="button is-primary" @click="onCreateClick">
                    Створити
                </button>
            </div>
        </div>
        <b-table
                @click="onRowClick"
                paginated
                v-if="!tableState.failed"
                :loading="tableState.loading"
                :data="filtered"
                :row-class="(r, i) => 'clickable'"
                :per-page="10">
            <template slot-scope="props">
                <b-table-column label="Ідентифікатор" field="id" sortable width="40">
                    {{ props.row.id }}
                </b-table-column>

                <b-table-column label="Ім`я" sortable field="name">
                    {{ props.row.name }}
                </b-table-column>

                <b-table-column label="ІНН" sortable field="inn">
                    {{ props.row.inn }}
                </b-table-column>
            </template>

        </b-table>

        <b-modal :active.sync="isCreateFacilityModalActive">
            <SubjectCreateModal @updated="onCreate"></SubjectCreateModal>
        </b-modal>
    </section>
</template>

<script lang="ts">
  import {Component, Vue} from 'vue-property-decorator';

  import {Subject, fetchSubjects} from "@/api/subject";
  import {Events} from '@/admin/shared/events';
  import {LoadingState, loadingProgress, loadingError, loadedData} from '@/shared/LoadingState';

  import SubjectCreateModal from '@/register/components/SubjectCreateModal.vue';

  @Component({
    components: {
      SubjectCreateModal,
    },
  })
  export default class ExecutantTable extends Vue {
    public tableState: LoadingState<Subject[]> = loadingProgress();

    public query: string;

    get filtered(): Subject[] {
      if (!this.tableState.data) {
        return [];
      }

      return this.tableState.data.filter((f) => f.name.toLowerCase().includes(this.query.toLowerCase()));
    }

    public isEditFacilityModalActive: boolean;
    public isCreateFacilityModalActive: boolean;
    public selectedFacility: Subject | null;


    constructor() {
      super();
      this.isEditFacilityModalActive = false;
      this.isCreateFacilityModalActive = false;
      this.selectedFacility = null;
      this.query = '';
    }

    public update(): void {
      this.fetchData();
    }

    public created(): void {
      this.fetchData();
    }

    public onRowClick(rowItem: Subject): void {
      this.selectedFacility = rowItem;
      this.isEditFacilityModalActive = true;
    }


    public async onCreate(): Promise<void> {
      this.fetchData();
      this.isCreateFacilityModalActive = false;
    }

    public onCreateClick(): void {
      this.isCreateFacilityModalActive = true;
    }

    private async fetchData(): Promise<void> {
      this.tableState = loadingProgress(this.tableState.data);
      const data: Subject[] = await fetchSubjects();
      this.tableState = loadedData(data);
    }
  }
</script>

<style scoped>
    .clickable {
        cursor: pointer;
    }
</style>