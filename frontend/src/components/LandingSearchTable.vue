<template>
    <section>
        <div class="container" v-if="error">
            <b-message class="error" has-icon type="is-danger" title="Error">
                {{ error }}
            </b-message>
        </div>
        <b-table
                v-if="!error"
                :loading="loading"
                :data="tableData"
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
                Тут повинна бути інформація про боржників та стягувачів для впровадження номер {{props.row.id}}
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

  @Component
    export default class LandingSearch extends Vue {
        public error: string | null = null;
        public loading = true;
        public tableData: TopEnforcement[] = [];
        public dateViewOptions: DateViewOptions = {
          year: 'numeric', month: 'long', day: 'numeric',
        };

        public created() {
          this.fetchData();
        }

        private async fetchData() {
          try {
            this.tableData = await fetchTopEnforcements();
          } catch (err) {
            this.error = err.response.data.message;
          } finally {
            this.loading = false;
          }
        }
    }
</script>