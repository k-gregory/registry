<template>
    <section>
        <b-field grouped group-multiline is-grouped-right class="level">
            <b-field type="text" horizontal label="Пошук" class="query level-tem level-left">
                <b-input v-model="query"></b-input>
            </b-field>
            <div class="field level-item level-right">
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
                <b-table-column label="Ідентифікатор" sortable width="40">
                    {{ props.row.id }}
                </b-table-column>

                <b-table-column label="Повне ім'я" sortable :custom-sort="sortByName">
                    {{ getFullName(props.row) }}
                </b-table-column>

                <b-table-column label="Телефон" centered>
                    {{ props.row.phoneNumber }}
                </b-table-column>

                <b-table-column label="ДВС" sortable field="facilityName">
                    {{ props.row.facilityName }}
                </b-table-column>
            </template>

        </b-table>
        <b-modal :active.sync="isEditUserModalActive">
            <ExecutantEditModal @updated="onUpdate" :executant="selectedUser"></ExecutantEditModal>
        </b-modal>
        <b-modal :active.sync="isCreateUserModalActive">
            <ExecutantCreateModal @created="onUpdate"></ExecutantCreateModal>
        </b-modal>
    </section>
</template>

<script lang="ts">
import {Component, Vue} from 'vue-property-decorator';

import {Executant, getFullName, fetchExecutants} from '@/api/executant';
import {Events} from '@/admin/shared/events';
import {LoadingState, loadingProgress, loadingError, loadedData} from '@/shared/LoadingState';

import ExecutantEditModal from '@/admin/components/ExecutantEditModal.vue';
import ExecutantCreateModal from '@/admin/components/ExecutantCreateModal.vue';

@Component({
    components: {
        ExecutantEditModal,
        ExecutantCreateModal,
    },
})
export default class ExecutantTable extends Vue {
    public tableState: LoadingState<Executant[]> = loadingProgress();

    public query: string;

    get filtered(): Executant[] {
        if (!this.tableState.data) {
            return [];
        }

        const query = this.query.toLowerCase();

        return this.tableState.data.filter((e) => e.firstName.toLowerCase().includes(query) ||
                                                e.middleName.toLowerCase().includes(query) ||
                                                e.lastName.toLowerCase().includes(query));
    }

    public isEditUserModalActive: boolean;
    public isCreateUserModalActive: boolean;
    public selectedUser: Executant | null;

    constructor() {
        super();
        this.isEditUserModalActive = false;
        this.isCreateUserModalActive = false;
        this.selectedUser = null;
        this.query = '';
    }

    public update(): void {
        this.fetchData();
    }

    public created(): void {
        this.fetchData();
    }

    public sortByName(a: Executant, b: Executant, isAsc: boolean): number {
        if (isAsc) {
            const tmp = a;
            a = b;
            b = tmp;
        }

        return getFullName(a).localeCompare(getFullName(b));
    }

    public onRowClick(rowItem: Executant): void {
        this.selectedUser = rowItem;
        this.isEditUserModalActive = true;
    }

    public onUpdate(): void {
        this.fetchData();
        this.isEditUserModalActive = false;
        this.isCreateUserModalActive = false;
    }

    public onCreateClick(): void {
        this.isCreateUserModalActive = true;
    }

    private async fetchData(): Promise<void> {
        this.tableState = loadingProgress(this.tableState.data);
        const data: Executant[] = await fetchExecutants();
        this.tableState = loadedData(data);
    }

    private getFullName(e: Executant): string {
        return getFullName(e);
    }
}
</script>

<style lang="scss">
.clickable {
    cursor: pointer;
}    

.query {
    min-width: 500px;
}
</style>


