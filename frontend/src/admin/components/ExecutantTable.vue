<template>
    <section>
        <b-table
                @click="onClick"
                paginated
                v-if="!tableState.failed"
                :loading="tableState.loading"
                :data="tableState.data"
                :row-class="(r, i) => 'clickable'">
            <template slot-scope="props">
                <b-table-column label="Ідентифікатор" width="40">
                    {{ props.row.id }}
                </b-table-column>

                <b-table-column label="Повне ім'я" sortable :custom-sort="sortByName">
                    {{ getFullName(props.row) }}
                </b-table-column>

                <b-table-column label="Телефон" centered>
                    {{ props.row.phoneNumber }}
                </b-table-column>
            </template>

        </b-table>
    </section>
</template>

<script lang="ts">
import {Component, Vue} from 'vue-property-decorator';

import {Executant, getFullName, fetchExecutants} from '@/api/executant';
import {Events} from '@/admin/shared/events';
import {LoadingState, loadingProgress, loadingError, loadedData} from '@/shared/LoadingState';

@Component({})
export default class ExecutantTable extends Vue {
    public tableState: LoadingState<Executant[]> = loadingProgress();

    public created() {
        this.fetchData();
    }

    public onClick(rowItem: Executant): void {
        this.$emit(Events.UsersTableRowClick, rowItem);
    }

    public sortByName(a: Executant, b: Executant, isAsc: boolean): number {
        if (isAsc) {
            const tmp = a;
            a = b;
            b = tmp;
        }

        return getFullName(a).localeCompare(getFullName(b));
    }

    private async fetchData() {
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
</style>


