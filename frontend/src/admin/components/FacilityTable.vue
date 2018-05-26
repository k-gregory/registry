<template>
    <section>
        <b-field grouped group-multiline is-grouped-right>
            <div class="control">
                <button class="button field" @click="onCreateClick">
                    Створити
                </button>
            </div>
        </b-field>
        <b-table
                @click="onRowClick"
                paginated
                v-if="!tableState.failed"
                :loading="tableState.loading"
                :data="tableState.data"
                :row-class="(r, i) => 'clickable'">
            <template slot-scope="props">
                <b-table-column label="Ідентифікатор" width="40">
                    {{ props.row.id }}
                </b-table-column>

                <b-table-column label="Назва" sortable field="name">
                    {{ props.row.name }}
                </b-table-column>
            </template>

        </b-table>
        <b-modal :active.sync="isEditFacilityModalActive">
            <FacilityModal title="Редагування" @updated="onUpdate" :facility="selectedFacility"></FacilityModal>
        </b-modal>
        <b-modal :active.sync="isCreateFacilityModalActive">
            <FacilityModal title="Створення" @updated="onCreate"></FacilityModal>
        </b-modal>
    </section>
</template>

<script lang="ts">
import {Component, Vue} from 'vue-property-decorator';

import {Facility, fetchFacilities, updateFacility, createFacility} from '@/api/facility';
import {Events} from '@/admin/shared/events';
import {LoadingState, loadingProgress, loadingError, loadedData} from '@/shared/LoadingState';

import FacilityModal from '@/admin/components/FacilityModal.vue';

@Component({
    components: {
        FacilityModal,
    },
})
export default class ExecutantTable extends Vue {
    public tableState: LoadingState<Facility[]> = loadingProgress();

    public isEditFacilityModalActive: boolean;
    public isCreateFacilityModalActive: boolean;
    public selectedFacility: Facility | null;

    constructor() {
        super();
        this.isEditFacilityModalActive = false;
        this.isCreateFacilityModalActive = false;
        this.selectedFacility = null;
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

    public async onUpdate(name: string): Promise<void> {
        const facility = this.selectedFacility as Facility;
        await updateFacility(facility.id, name);
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


