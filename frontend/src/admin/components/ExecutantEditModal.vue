<template>
    <div>
        <div class="modal-card" style="width: auto">
            <header class="modal-card-head">
                <p class="modal-card-title">Редагування</p>
            </header>
            <section class="modal-card-body">
                <b-field label="Ім'я">
                    <b-input
                        type="text"
                        v-model="editedExecutant.firstName"
                        required>
                    </b-input>
                </b-field>

                <b-field label="По-батькові">
                    <b-input
                        type="text"
                        v-model="editedExecutant.middleName"
                        required>
                    </b-input>
                </b-field>

                <b-field label="Прізвище">
                    <b-input
                        type="text"
                        v-model="editedExecutant.lastName"
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
import {Component, Vue, Prop} from 'vue-property-decorator';
import { Executant, updateExecutant } from '@/api/executant';
import { Events } from '@/admin/shared/events';
import { Dialog } from 'buefy';

@Component
export default class ExecutantEditModal extends Vue {
    @Prop()
    public executant!: Executant;
    private editedExecutant: Executant | {};

    constructor() {
        super();
        this.editedExecutant = {};
    }

    public mounted(): void {
        if (this.executant == null) {
            return;
        }

        this.editedExecutant = {...this.executant};
    }

    public async onSubmit(): Promise<void> {
        const e: Executant = this.editedExecutant as Executant;
        await updateExecutant(e.id, e.firstName, e.middleName, e.lastName);
        this.$emit(Events.ExecutantUpdated);
    }
}
</script>

