<template>
    <div>
        <div class="modal-card" style="width: auto">
            <header class="modal-card-head">
                <p class="modal-card-title">Редагування</p>
            </header>
            <section class="modal-card-body">
                <b-field label="Назва">
                    <b-input
                        type="text"
                        v-model="editedFacility.name"
                        required>
                    </b-input>
                </b-field>

                <b-field label="Голова">
                    <b-select placeholder="Оберіть Голову" v-model="editedFacility.headId" expanded>
                        <option
                            v-for="option in executants"
                            :value="option.id"
                            :key="option.id">
                            {{ getFullName(option) }}
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
import { Facility } from '@/api/facility';
import { Executant, getFullName, fetchExecutants } from '@/api/executant';
import { Events } from '@/admin/shared/events';
import { Dialog } from 'buefy';

@Component
export default class FacilityEditModal extends Vue {
    @Prop()
    public facility!: Facility;
    @Prop()
    public title!: string;

    public executants: Executant[];

    private editedFacility: Facility | {};

    constructor() {
        super();
        this.editedFacility = {};
        this.executants = [];
    }

    public async mounted(): Promise<void> {
        if (this.facility == null) {
            return;
        }

        this.editedFacility = {...this.facility, headId: this.facility.headId === null ? 0 : this.facility.headId};
        const executants = await fetchExecutants();
        const noFacility: any = {id: 0, firstName: 'Голову не призначено', middleName: '', lastName: ''};
        this.executants = [noFacility].concat(executants.filter((f) => f.facilityId === this.facility.id));
        this.$forceUpdate();
    }

    public async onSubmit(): Promise<void> {
        const e: Facility = this.editedFacility as Facility;
        this.$emit(Events.FacilityUpdated, {...e, headId: e.headId === 0 ? null : e.headId});
    }

    public getFullName(e: Executant): string {
        return getFullName(e);
    }
}
</script>

