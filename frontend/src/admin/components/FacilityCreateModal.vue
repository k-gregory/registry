<template>
    <div>
        <div class="modal-card" style="width: auto">
            <header class="modal-card-head">
                <p class="modal-card-title">Створення</p>
            </header>
            <section class="modal-card-body">
                <b-field label="Назва">
                    <b-input
                        type="text"
                        v-model="editedFacility.name"
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
import { Facility } from '@/api/facility';
import { Events } from '@/admin/shared/events';
import { Dialog } from 'buefy';

@Component
export default class FacilityCreateModal extends Vue {
    private editedFacility: Facility | {};

    constructor() {
        super();
        this.editedFacility = {};
    }

    public async onSubmit(): Promise<void> {
        const e: Facility = this.editedFacility as Facility;
        this.$emit(Events.FacilityUpdated, e.name);
    }
}
</script>

