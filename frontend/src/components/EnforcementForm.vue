<template>
    <form @submit.prevent="addEnforcement">
        <label>
            Facility
            <input v-model="facility" type="text"/>
        </label>
        <label>
            Data vidkrutya
            <input v-model="date" type="text"/>
        </label>
        <label>
            Stan
            <input v-model="stan" type="text"/>
        </label>
        <input type="submit" value="Add"/>
    </form>
</template>

<script lang="ts">
  import {Component, Vue} from 'vue-property-decorator';
  import {postEnforcement} from "../api/enforcement";
  import {Enforcement} from "../model/Enforcement";

  @Component
  export default class EnforcementForm extends Vue {
    public facility: string = "";
    public date: string = "";
    public stan: string = "";

    addEnforcement() {
      postEnforcement(new Enforcement(this.facility, this.date, this.stan));
    }
  }
</script>

<style scoped>

</style>