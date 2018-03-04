<template>
    <section>
        <div v-if="loading">Loading...</div>
        <div v-if="error">{{ error }}</div>
        <b-table
                v-if="tableData.length"
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

<script lang="ts">
    import {Component, Vue} from "vue-property-decorator";
    import DateViewOptions from "@/shared/DateViewOptions";
    import {fetchTopEnforcements, TopEnforcement} from "@/api/enforsement";

    @Component
export default class LandingSearch extends Vue {
        public error: string | null = null;
        public loading = true;
        public tableData: TopEnforcement[] = [];
        public dateViewOptions: DateViewOptions = {
            year: "numeric", month: "long", day: "numeric",
        };

        public created() {
            this.fetchData();
        }

        private async fetchData() {
            const r = await fetchTopEnforcements();
            this.loading = false;
            if (typeof r === "string") {
                this.error = r;
            } else {
                this.tableData = r;
            }
        }
}
</script>