<template>
    <div>
        <div class="modal-card" style="width: auto">
            <header class="modal-card-head">
                <p class="modal-card-title">Створення</p>
            </header>
            <section class="modal-card-body">
                <b-field label="Отримувач">
                    <b-select placeholder="Оберість отримувача" v-model="editedFacility.receiverId" required expanded>
                        <option
                                v-for="option in subjects"
                                v-if="option.id !== editedFacility.senderId"
                                :value="option.id"
                                :key="option.id">
                            {{ option.name }}
                        </option>
                    </b-select>
                </b-field>

                <b-field label="Стягувач">
                    <b-select placeholder="Оберість стягувача" v-model="editedFacility.senderId" required expanded>
                        <option
                                v-for="option in subjects"
                                v-if="option.id !== editedFacility.receiverId"
                                :value="option.id"
                                :key="option.id">
                            {{ option.name }}
                        </option>
                    </b-select>
                </b-field>

                <b-field label="ДВС">
                    <b-select placeholder="Оберість ДВС" v-model="editedFacility.facilityId" required expanded>
                        <option
                                v-for="option in facilities"
                                :value="option.id"
                                :key="option.id">
                            {{ option.name }}
                        </option>
                    </b-select>
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
  import { Enforcement, createEnforcement } from '@/api/enforcement';
  import { Events } from '@/admin/shared/events';
  import { Dialog } from 'buefy';
  import {Facility, fetchFacilities} from "../../api/facility";
  import {EnforcementS} from "../../api/enforcement";
  import {fetchSubjects, Subject} from "../../api/subject";

  @Component
  export default class EnforcementCreateModal extends Vue {
    private editedFacility!: EnforcementS;
    public facilities!: Facility[];
    public subjects!: Subject[];

    constructor() {
      super();

      this.facilities = [];
      this.subjects = [];

      this.editedFacility = {
        senderId: 0,
        receiverId: 0,
        facilityId: 0
      };
    }

    public async mounted(){
      this.facilities = await fetchFacilities();
      this.subjects = await fetchSubjects();
    }

    public async onSubmit(): Promise<void> {
      await createEnforcement(this.editedFacility);
      this.$emit(Events.FacilityUpdated, 0);
    }
  }
</script>