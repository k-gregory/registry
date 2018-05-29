<template>
  <div>
    <nav class="level toolbar box">
      <div class="level-item">
        <router-link tag="button" class="button is-primary" class-active="active" to="/register" exact>Реєстр</router-link>
      </div>
      <div class="level-item">
        <div class="title">Автоматизована система виконавчого провадження</div>
      </div>
      <div class="level-item">
        <router-link tag="button" class="button is-primary" class-active="active" to="/admin" exact>Адмін</router-link>
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