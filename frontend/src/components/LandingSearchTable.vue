<template>
    <section>
        <b-message v-if="tableState.failed" class="error" has-icon type="is-danger" :title="tableState.error">
            {{ tableState.message }}
        </b-message>
        <b-table
                v-if="!tableState.failed"
                :loading="tableState.loading"
                :data="tableState.data"
                detailed
                detail-key="id">
            <template slot-scope="props">
                <b-table-column label="№ АСВП" width="50">
                    {{props.row.id}}
                </b-table-column>
                <b-table-column label="Facility" width="50">
                    {{props.row.facility}}
                </b-table-column>
                <b-table-column label="Дата відкриття" width="50">
                    {{props.row.date.toLocaleDateString('uk-UA', dateViewOptions)}}
                </b-table-column>
                <b-table-column label="Стан" width="50">
                    {{props.row.state}}
                </b-table-column>
            </template>
            <template slot="detail" slot-scope="props">
                Тут повинна бути інформація про боржників та стягувачів для впровадження номер {{props.row.id}}.
            </template>
        </b-table>
    </section>
</template>

<style scoped>
    .error {
        max-width: 40em;
    }
</style>

<script lang="ts">
    import {Component, Vue} from 'vue-property-decorator';
    import DateViewOptions from '@/shared/DateViewOptions';
    import {fetchTopEnforcements, TopEnforcement} from '@/api/enforcement';
    import {LoadingState, loadingProgress, loadingError, loadedData} from '@/shared/LoadingState';

    @Component
    export default class LandingSearch extends Vue {
        public tableState: LoadingState<TopEnforcement[]> = loadingProgress();

        public dateViewOptions: DateViewOptions = {
            year: 'numeric', month: 'long', day: 'numeric',
        };

        public created(): void {
            this.fetchData();
            setInterval(() => {
                this.fetchData();
            }, 3000);
        }

        private async fetchData(): Promise<void> {
            try {
                this.tableState = loadingProgress(this.tableState.data);
                const data = await fetchTopEnforcements();
                this.tableState = loadedData(data);
            } catch (err) {
                if (err.response) {
                    this.tableState = loadingError(err.response.data.error, err.response.data.message);
                } else {
                    this.tableState = loadingError('Unknown error', 'lol');
                }
            }
        }
    }
</script>
