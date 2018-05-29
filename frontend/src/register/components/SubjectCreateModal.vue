<template>
    <div>
        <div class="modal-card" style="width: auto">
            <header class="modal-card-head">
                <p class="modal-card-title">Створення</p>
            </header>
            <section class="modal-card-body">
                <b-field label="Ім`я">
                    <b-input
                            type="text"
                            v-model="editedFacility.name"
                            required>
                    </b-input>
                </b-field>

                <b-field label="ІНН">
                    <b-input
                            type="text"
                            v-model="editedFacility.inn"
                            required>
                    </b-input>
                </b-field>

            </section>
            <footer class="modal-card-foot">
                <button class="button" type="button" @click="$parent.close()">Відмінити</button>
                <button class="button is-primary" @click="onSubmit">Прийняти</button>
            </footer>
        </div>
    </div>
</template>

<script lang="ts">
  import {Component, Vue, Prop, Provide} from 'vue-property-decorator';
  import { Subject, createSubject } from '@/api/subject';
  import { Events } from '@/admin/shared/events';
  import { Dialog } from 'buefy';

  @Component
  export default class SubjectCreateModal extends Vue {
    private editedFacility!: Subject;

    constructor() {
      super();
      this.editedFacility = {
        id: 0, name: '', inn: ''
      };
    }

    public async onSubmit(): Promise<void> {
      const e: Subject = this.editedFacility as Subject;
      await createSubject(this.editedFacility);

      this.$emit(Events.FacilityUpdated, e.name);
    }
  }
</script>