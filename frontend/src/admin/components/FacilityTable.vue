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

                <b-table-column label="Назва" sortable field="name">
                    {{ props.row.name }}
                </b-table-column>

                <b-table-column label="Голова" sortable field="headName">
                    {{ props.row.headName || "Голову не призначено" }}
                </b-table-column>
            </template>

        </b-table>
        <b-modal :active.sync="isEditFacilityModalActive">
            <FacilityEditModal @updated="onUpdate" :facility="selectedFacility"></FacilityEditModal>
        </b-modal>
        <b-modal :active.sync="isCreateFacilityModalActive">
            <FacilityCreateModal @updated="onCreate"></FacilityCreateModal>
        </b-modal>
    </section>
</template>

<script lang="ts">
import {Component, Vue} from 'vue-property-decorator';

import {Facility, fetchFacilities, updateFacility, createFacility} from '@/api/facility';
import {Events} from '@/admin/shared/events';
import {LoadingState, loadingProgress, loadingError, loadedData} from '@/shared/LoadingState';

import FacilityCreateModal from '@/admin/components/FacilityCreateModal.vue';
import FacilityEditModal from '@/admin/components/FacilityEditModal.vue';

@Component({
    components: {
        FacilityEditModal, FacilityCreateModal,
    },
})
export default class ExecutantTable extends Vue {
    public tableState: LoadingState<Facility[]> = loadingProgress();

    public query: string;

    get filtered(): Facility[] {
        if (!this.tableState.data) {
            return [];
        }

        return this.tableState.data.filter((f) => f.name.toLowerCase().includes(this.query.toLowerCase()));
    }

    public isEditFacilityModalActive: boolean;
    public isCreateFacilityModalActive: boolean;
    public selectedFacility: Facility | null;


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

    public onRowClick(rowItem: Facility): void {
        this.selectedFacility = rowItem;
        this.isEditFacilityModalActive = true;
    }

    public async onUpdate(facility: Facility): Promise<void> {
        await updateFacility(facility.id, facility.name, facility.headId as number);
        await this.fetchData();
        this.isEditFacilityModalActive = false;
    }

    public async onCreate(name: string): Promise<void> {
        await createFacility(name);
        await this.fetchData();
        this.isCreateFacilityModalActive = false;
    }

    public onCreateClick(): void {
        this.isCreateFacilityModalActive = true;
    }

    private async fetchData(): Promise<void> {
        this.tableState = loadingProgress(this.tableState.data);
        const data: Facility[] = await fetchFacilities();
        this.tableState = loadedData(data);
    }
}
</script>

<style lang="scss">
.clickable {
    cursor: pointer;
}    
</style>


