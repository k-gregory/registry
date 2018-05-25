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
                        :value="editedExecutant.firstName"
                        required>
                    </b-input>
                </b-field>

                <b-field label="По-батькові">
                    <b-input
                        type="text"
                        :value="editedExecutant.middleName"
                        required>
                    </b-input>
                </b-field>

                <b-field label="Прізвище">
                    <b-input
                        type="text"
                        :value="editedExecutant.lastName"
                        required>
                    </b-input>
                </b-field>

            </section>
            <footer class="modal-card-foot">
                <button class="button" type="button" @click="$parent.close()">Відмінити</button>
                <button class="button is-primary">Прийняти</button>
            </footer>
        </div>
    </div>
</template>

<script lang="ts">
import {Component, Vue, Prop} from 'vue-property-decorator';
import { Executant } from '@/api/executant';

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
}
</script>

