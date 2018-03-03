<template>
    <section>
        <b-table
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
import { Component, Prop, Vue } from 'vue-property-decorator';

@Component
export default class LandingSearch extends Vue {
  public data(): object {
      const tableData = [
          {id: 112228, facility: 'Вишневецьке УСОЯОРОРО імені Скоропадського',
            date: new Date(), state: 'Відкрито'},
          {id: 2212328, facility: 'Дарницьке АСОІОУ імені Васюка',
            date: new Date(), state: 'Відкрито'},
          {id: 32131321, facility: 'КРинацьке ВАСОУ імені Сікорського',
            date: new Date(), state: 'Закрито'},
          {id: 2212312328, facility: 'Шевченківське КРПАН імені Лобачевського',
            date: new Date(), state: 'На примусовому виконанні'},
          {id: 22123128, facility: 'Рололітове УСОЯОРОРО імені Сікорського',
            date: new Date(), state: 'Відмовлено у відкритті'},
      ];
      return {
          tableData,
          searchQuery: '',
          dateViewOptions: {
              year: 'numeric', month: 'long', day: 'numeric',
            },
      };
  }
}
</script>