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
                <b-table-column label="ID" width="40" numeric>
                    {{ props.row.id }}
                </b-table-column>

                <b-table-column field="fullname" sortable label="Full Name">
                    {{ props.row.fullname }}
                </b-table-column>

                <b-table-column label="Phone" centered>
                    {{ props.row.phone }}
                </b-table-column>
            </template>

        </b-table>
    </section>
</template>

<script lang="ts">
import {Component, Vue} from 'vue-property-decorator';

import {Executant} from '@/api/executant';
import {Events} from '@/admin/shared/events';
import {LoadingState, loadingProgress, loadingError, loadedData} from '@/shared/LoadingState';

@Component
export default class ExecutantTable extends Vue {
    public tableState: LoadingState<Executant[]> = loadingProgress();

    public created() {
        this.fetchData();
    }

    public onClick(rowItem: Executant): void {
        this.$emit(Events.UsersTableRowClick, rowItem);
    }

    private async fetchData() {
        const data: Executant[] = [{fullname: 'Maxim Petrovich Petrov', phone: '+380990990099', id: 1},
            {fullname: 'Maxim1 Petrovich1 Petrov1', phone: '+380990990099', id: 2}];
        this.tableState = loadedData(data);
    }
}
</script>

<style lang="scss">
.clickable {
    cursor: pointer;
}    
</style>


