<template>
    <div>
        <div class="modal-card" style="width: auto">
            <header class="modal-card-head">
                <p class="modal-card-title">Створення</p>
            </header>
            <section ref="form" class="modal-card-body">
                <b-field label="Ім'я">
                    <b-input
                        type="text"
                        v-model="executant.firstName"
                        required>
                    </b-input>
                </b-field>

                <b-field label="По-батькові">
                    <b-input
                        type="text"
                        v-model="executant.middleName"
                        required>
                    </b-input>
                </b-field>

                <b-field label="Прізвище">
                    <b-input
                        type="text"
                        v-model="executant.lastName"
                        required>
                    </b-input>
                </b-field>

                <b-field label="Контактний Телефон">
                    <b-input
                        type="text"
                        v-model="executant.phoneNumber"
                        required>
                    </b-input>
                </b-field>

                <b-field label="ДВС">
                    <b-select placeholder="Оберість ДВС" v-model="executant.facilityId" required expanded>
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
                <button class="button is-primary" @click="onCreate" :disabled="isDisabled">Прийняти</button>
            </footer>
        </div>
    </div>
</template>

<script lang="ts">
import {Component, Vue, Prop, Watch} from 'vue-property-decorator';
import { Executant, createExecutant } from '@/api/executant';
import { Facility, fetchFacilities } from '@/api/facility';
import { Events } from '@/admin/shared/events';
import { constants } from 'http2';

@Component
export default class ExecutantCreateModal extends Vue {
    public executant!: Executant;
    public facilities!: Facility[];
    public isDisabled: boolean;

    constructor() {
        super();
        this.init();
        this.isDisabled = false;
    }

    public async mounted(): Promise<void> {
        this.init();
        this.facilities = await fetchFacilities();
        this.$forceUpdate();
    }

    public async onCreate(): Promise<void> {
        await createExecutant(this.executant);
        this.$emit(Events.ExecutantCreated);
    }

    // TODO: Investigate why watch doesn't work
    @Watch('executant.firstName')
    public onPropertyChanged(val: string, oldVal: string): void {
        const refs = this.$refs as any;
        const valid = refs.form.checkValidity();
        this.isDisabled = !valid;
    }

    private init(): void {
        this.executant = {
            id: 0, firstName: '', middleName: '', lastName: '', phoneNumber: '', facilityId: 0, facilityName: '',
        };
        this.facilities = [];
    }
}
</script>

