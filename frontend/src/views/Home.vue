<template>
  <div>
    <nav class="level toolbar box">
      <div class="level-item">
        <b-field>
          <b-input placeholder="Знайти..."
              type="search"
              icon="magnify"
              v-model="searchQuery">
          </b-input>
          <p class="control">
              <button class="button is-primary">Пошук</button>
          </p>
        </b-field>
      </div>
      <div class="level-item">
        <div class="title">Автоматизована система виконавчого провадження</div>
      </div>
      <div class="level-item">
        <button class="button is-primary">Увійти</button>
      </div>
    </nav>
    <div class="container">
      <LandingSearchTable></LandingSearchTable>
     </div>
  </div>
</template>

<script lang="ts">
  import {Component, Vue} from 'vue-property-decorator';
  import LandingSearchTable from '@/components/LandingSearchTable.vue'; // @ is an alias to /src

  interface HomePageData {
    searchQuery: string;
  }

  @Component({
    components: {
      LandingSearchTable,
    },
  })
  export default class Home extends Vue {
    public data(): HomePageData {
      return {
        searchQuery: '',
      };
    }
  }
</script>

<style lang="scss" scoped>
.toolbar {
  padding: 20px 0;
}

</style>