<template>
    <section>
        <b-field grouped group-multiline is-grouped-right class="level">
            <b-field type="text" horizontal label="Пошук" class="query level-tem level-left">
                <b-input v-model="query"></b-input>
            </b-field>
            <div class="field is-pulled-right level-item level-right">
                <button class="button field is-primary" @click="onCreateClick">
                    Створити
                </button>
            </div>
        </b-field>
        <b-table
                @click="onRowClick"
                paginated
                v-if="!tableState.failed"
                :loading="tableState.loading"
                :data="filtered"
                :row-class="(r, i) => 'clickable'"
                :per-page="10">
            <template slot-scope="props">
                <b-table-column label="Ідентифікатор" width="40">
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


