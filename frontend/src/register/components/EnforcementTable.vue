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

                <b-table-column label="Ім`я боржника" sortable field="senderName">
                    {{ props.row.senderName }}
                </b-table-column>

                <b-table-column label="Ім`я стягувача" sortable field="receiverName">
                    {{ props.row.receiverName }}
                </b-table-column>

                <b-table-column label="Дата відкриття">
                    {{ props.row.startedAt }}
                </b-table-column>

                <b-table-column label="ДВС">
                    {{ props.row.facilityName }}
                </b-table-column>

            </template>

        </b-table>

        <b-modal :active.sync="isCreateFacilityModalActive">
            <EnforcementCreateModal @updated="onCreate"></EnforcementCreateModal>
        </b-modal>
    </section>
</template>

<script lang="ts">
  import {Component, Vue} from 'vue-property-decorator';

  import {Enforcement, fetchAllEnforcements} from "@/api/enforcement";
  import {Events} from '@/admin/shared/events';
  import {LoadingState, loadingProgress, loadingError, loadedData} from '@/shared/LoadingState';

  import EnforcementCreateModal from '@/register/components/EnforcementCreateModal.vue';

  @Component({
    components: {
      EnforcementCreateModal,
    },
  })
  export default class EnforcementTable extends Vue {
    public tableState: LoadingState<Enforcement[]> = loadingProgress();

    public query: string;

    get filtered(): Enforcement[] {
      if (!this.tableState.data) {
        return [];
      }

      return this.tableState.data.filter((f) => f.senderName.toLowerCase().includes(this.query.toLowerCase()));
    }

    public isEditFacilityModalActive: boolean;
    public isCreateFacilityModalActive: boolean;
    public selectedFacility: Enforcement | null;


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

    public onRowClick(rowItem: Enforcement): void {
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
      const data: Enforcement[] = await fetchAllEnforcements();
      this.tableState = loadedData(data);
    }
  }
</script>

<style scoped>
    .clickable {
        cursor: pointer;
    }
</style>