<template>
  <div>
    <nav class="level toolbar box">
      <div class="level-item">
        <b-field>
          <b-input placeholder="Знайти..."
              type="search"
              icon="magnify">
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
    <section class="search">
      <div class="container">
        <LandingSearchTable>
        </LandingSearchTable>
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator';
import LandingSearchTable from '@/components/LandingSearchTable.vue'; // @ is an alias to /src

@Component({
  components: {
    LandingSearchTable,
  },
})
export default class Home extends Vue {}
</script>

<style lang="scss" scoped>

.search {
  padding-top: 50px;
}

.toolbar {
  padding: 20px 0;
}

</style>