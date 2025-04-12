<script setup>
import http from "@/utils/http";
import ModalClienteBase from "./ModalClienteBase.vue";
import { onMounted, ref, useTemplateRef } from "vue";

const estados = ref([]);
const cidades = ref([]);

const selectEstados = useTemplateRef("sel-estados");

onMounted(() => {

  http.get("https://servicodados.ibge.gov.br/api/v1/localidades/estados?orderBy=nome")
    .then(res => res.json())
    .then((data) => {
      estados.value = data;
    });

  http.get("https://viacep.com.br/ws/25535014/json")
    .then(res => res.json())
    .then(data => console.log(data));
});


function carregarCidades() {
  const idEstado = estados.value[selectEstados.value.value].id;
  http.get(`https://servicodados.ibge.gov.br/api/v1/localidades/estados/${idEstado}/municipios`)
    .then(res => res.json())
    .then((data) => {
      cidades.value = data;
    });
}



</script>

<template>
 <ModalClienteBase>
  <form class="modal-form flex f-column f-centered">
    <div class="modal-form-title">
      Novo Cliente
    </div>
    <div class="modal-form-inputs">

      <div class="modal-form-inputs-wrapper flex">

        <div class="modal-form-input flex f-column">
          <label for="nome">Nome:</label>
          <input type="text" id="nome" name="nome" placeholder="Fulano Silva" autocomplete="off" />
        </div>

        <div class="modal-form-inputs-wrapper-sp"></div>

        <div class="modal-form-input flex f-column">
          <label for="cpf">CPF:</label>
          <input type="text" id="cpf" name="cpf" placeholder="123.456.789-11" autocomplete="off" />
        </div>

      </div>

      <div class="modal-form-inputs-wrapper flex">
        
        <div class="modal-form-input flex f-column">
          <label for="email">E-mail:</label>
          <input type="text" id="email" name="email" placeholder="fulanosilva@gmail.com" autocomplete="off" />
        </div>

        <div class="modal-form-inputs-wrapper-sp"></div>

        <div class="modal-form-input flex f-column">
          <label for="telefone">Telefone:</label>
          <input type="text" id="telefone" name="telefone" placeholder="(99) 98765-4321" autocomplete="off" />
        </div>

      </div>

      <div class="modal-form-inputs-wrapper flex">

        <div class="modal-form-input flex f-column">
          <label for="cep">CEP:</label>
          <input type="text" id="cep" name="cep" placeholder="12345-000" autocomplete="off" />
        </div>

        <div class="modal-form-inputs-wrapper-sp"></div>

        <div class="modal-form-input flex f-column">
          <label for="estado">Estado:</label>
          <select 
            @change="carregarCidades" 
            name="estado" 
            id="estado" 
            ref="sel-estados"
          >
            <option 
              v-for="estado, index in estados" 
              :key="index" 
              :value="index"
            >
              {{ estado.nome }}
            </option>
          </select>
        </div>

      </div>

      <div class="modal-form-input flex f-column">
        <label for="cidade">Cidade:</label>
        <select 
          name="cidade" 
          id="cidade"
        >
          <option 
            v-for="cidade, index in cidades" 
            :key="index" 
            :value="index"
          >
            {{ cidade.nome }}
          </option>
        </select>
      </div>

      <div class="modal-form-input flex f-column">
        <label for="endereco">Endereço:</label>
        <input type="text" id="endereco" name="endereco" placeholder="ex.: Rua das Flores N°55 " autocomplete="off" />
      </div>

    </div>
    <button class="modal-form-button">
      SALVAR
    </button>
  </form>
 </ModalClienteBase> 
</template>



<style scoped>
.modal-form {
  width: 100%;
  /* height: 100%; */

  /* background-color: aqua; */

}

.modal-form-title {
  width: inherit;

  margin-bottom: 20px;

  font-size: 20pt;
  font-weight: bold;
  text-align: center;

  color: var(--black-soft);

  /* background-color: firebrick; */
}

.modal-form-inputs {
  width: inherit;

  flex: 1;

  margin-bottom: 10px;
}

.modal-form-inputs-wrapper {

}
.modal-form-inputs-wrapper-sp {
  margin: 0 5px;
}

.modal-form-input {
  margin: 10px 0px;

  flex: 1;
}
.modal-form-input label {
  color: var(--black-soft);
  margin-bottom: 5px;
}
.modal-form-input input {
  padding: 10px;

  border: none;
  border-radius: 5px;

  color: var(--black-softer);
  font-size: 12pt;
  box-shadow: rgba(0, 0, 0, 0.05) 0px 6px 24px 0px, rgba(0, 0, 0, 0.08) 0px 0px 0px 1px; 
}
.modal-form-input input::placeholder {
  color: var(--gray);
  font-size: 10pt;
}

.modal-form-input select {
  padding: 10px;

  border: none;
  border-radius: 5px;

  color: var(--black-softer);
  font-size: 12pt;
  box-shadow: rgba(0, 0, 0, 0.05) 0px 6px 24px 0px, rgba(0, 0, 0, 0.08) 0px 0px 0px 1px;
}

.modal-form-button {
  width: 100%;
  padding: 10px;

  border: none;
  border-radius: 5px;

  color: var(--white);
  font-size: 12pt;

  background-color: var(--brand-color);

  box-shadow: rgba(0, 0, 0, 0.05) 0px 6px 24px 0px,  var(--brand-color);

  cursor: pointer;
}

@media (max-width: 600px) {
  .modal-form-inputs-wrapper {
    flex-direction: column;
  }
  .modal-form-inputs-wrapper-sp {
    display: none;
  }
}

</style>