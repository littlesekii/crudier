<script setup>
import http from "@/utils/http";
import ModalClienteBase from "./ModalClienteBaseComponent.vue";
import { onMounted, ref, /*useTemplateRef*/ } from "vue";

const backendBaseURL = "http://localhost:1001";

const estados = ref([]);
const cidades = ref([]);

// const selectEstados = useTemplateRef("sel-estados");
// const selectCidades = useTemplateRef("sel-cidades");

const emit = defineEmits(["modalsave"]);

const formData = ref({
  nome: "",
  cpf: "",
  email: "",
  telefone: "",
  cep: "",
  estado: 0,
  cidade: 0,
  endereco: ""
});

function formatarCPF() {
  const formatoCPF = formData.value.cpf
    .replace(/\D/g, "") // Impede qualquer coisa que não seja número
    .replace(/(\d{3})(\d)/, "$1.$2") // Adiciona ponto após o terceiro dígito
    .replace(/(\d{3})(\d)/, "$1.$2") // Adiciona ponto após o sexto dígito
    .replace(/(\d{3})(\d)/, "$1-$2") // Adiciona traço após o nono dígito
    .replace(/(-\d{2})\d+?$/, "$1"); // Impede entrada de mais de 11 dígitos
  formData.value.cpf = formatoCPF;
}

function formatarTelefone() {
  let formatoTelefone = formData.value.telefone
    .replace(/\D/g, "") // Impede qualquer coisa que não seja número
    .replace(/(\d{2})(\d)/, "($1) $2") // Adiciona parênteses no DDD e separa o próximo dígito
    .replace(/(\d{4})(\d)/, "$1-$2") // Adiciona o traço após o quarto dígito
    .replace(/(-\d{5})\d+?$/, "$1"); // Impede entrada de mais de 11 dígitos

  // altera a posição do dash
  if(formatoTelefone.length >= 15) {
    const dashPos = formatoTelefone.match("-").index;
    const auxArray = formatoTelefone.split("");

    auxArray[dashPos] = auxArray[dashPos + 1];
    auxArray[dashPos + 1] = "-";

    formatoTelefone = auxArray.join("");
  }

  formData.value.telefone = formatoTelefone;
}

function formatarCEP() {
  const formatoCEP = formData.value.cep
    .replace(/\D/g, "") // Impede qualquer coisa que não seja número
    .replace(/(\d{5})(\d)/, "$1-$2") // Adiciona traço após o quinto dígito
    .replace(/(-\d{3})\d+?$/, "$1"); // Impede entrada de mais de 8 dígitos
  formData.value.cep = formatoCEP;
}

function autoPreencherCidade(nomeCidade) {
  const indiceCidade = cidades.value.findIndex(cidade => cidade.nome == nomeCidade);
  formData.value.cidade = indiceCidade;
}

function carregarCidades(autoCompletar = false, nomeCidade = "") {
  const idEstado = estados.value[formData.value.estado].id;
  http.get(`https://servicodados.ibge.gov.br/api/v1/localidades/estados/${idEstado}/municipios`)
    .then(res => res.json())
    .then((data) => cidades.value = data)
    .finally(() => { 

      if (!autoCompletar)
        formData.value.cidade = 0;
      else
        autoPreencherCidade(nomeCidade);
    });
}

function verificaAutoPreenchimentoCEP(e) {
  const cep = e.target.value.replace("-", "");

  if (cep.length >= 8) {
    http.get(`https://viacep.com.br/ws/${cep}/json`)
      .then(res => res.json())
      .then(async data => {
        if (!data.erro)
        {
          const indiceEstado = estados.value.findIndex(estado => estado.nome == data.estado);
          formData.value.estado = indiceEstado;

          carregarCidades(true, data.localidade);
        }
      });
  }
}

function removeMask(value) {
  return value.replaceAll(".", "")
    .replaceAll("-", "")
    .replaceAll("(", "")
    .replaceAll(")", "")
    .replaceAll(" ", "");
}

function salvarCliente() {
  const body = {
    nome: formData.value.nome,
    cpf: removeMask(formData.value.cpf),
    email: formData.value.email,
    telefone: removeMask(formData.value.telefone),
    cep: removeMask(formData.value.cep),
    estado: estados.value[formData.value.estado].nome,
    cidade: cidades.value[formData.value.cidade].nome,
    endereco: formData.value.endereco
  };

  http.post(`${backendBaseURL}/cliente`, JSON.stringify(body))
    .then((res) => {
      if (!res.ok) {
        emit("modalsave", false, "Erro ao inserir o cliente!");
        return;
      }

      emit("modalsave", true, "Cliente inserido com sucesso!");
    });
}

onMounted(() => {

  http.get("https://servicodados.ibge.gov.br/api/v1/localidades/estados?orderBy=nome")
    .then(res => res.json())
    .then((data) => estados.value = data)
    .finally(() => carregarCidades());

  const debugMode = true;

  if (debugMode)
  {
    formData.value = {
      cep: "23860000",
      cidade: 39,
      cpf: "06525442745",
      email: "bacalhaudavi@gmail.com",
      endereco: "Rua da Cachoeira Nº 17",
      estado: 18,
      nome: "Davi Bacalhau",
      telefone: "21970242955"
    };
  }

  formatarCEP();
  formatarCPF();
  formatarTelefone();
});

</script>

<template>
 <ModalClienteBase>
  <form @submit.prevent="salvarCliente" class="modal-form flex f-column f-centered">
    <div class="modal-form-title">
      Novo Cliente
    </div>
    <div class="modal-form-inputs">

      <div class="modal-form-inputs-wrapper flex">

        <div class="modal-form-input flex f-column">
          <label for="nome">Nome:</label>
          <input 
            v-model="formData.nome" 
            type="text" 
            id="nome" 
            name="nome" 
            placeholder="Fulano Silva" 
            required 
          />
        </div>

        <div class="modal-form-inputs-wrapper-sp"></div>

        <div class="modal-form-input flex f-column">
          <label for="cpf">CPF:</label>
          <input 
            v-model="formData.cpf" 
            @input="formatarCPF" 
            type="text" 
            id="cpf" 
            name="cpf" 
            placeholder="123.456.789-11" 
            required 
          />
        </div>

      </div>

      <div class="modal-form-inputs-wrapper flex">
        
        <div class="modal-form-input flex f-column">
          <label for="email">E-mail:</label>
          <input 
            v-model="formData.email" 
            type="email" 
            id="email" 
            name="email" 
            placeholder="fulanosilva@gmail.com" 
          />
        </div>

        <div class="modal-form-inputs-wrapper-sp"></div>

        <div class="modal-form-input flex f-column">
          <label for="telefone">Telefone:</label>
          <input 
            v-model="formData.telefone" 
            @input="formatarTelefone" 
            type="text" 
            id="telefone" 
            name="telefone" 
            placeholder="(99) 98765-4321" 
          />
        </div>

      </div>

      <div class="modal-form-inputs-wrapper flex">

        <div class="modal-form-input flex f-column">
          <label for="cep">CEP:</label>
          <input 
            v-model="formData.cep"
            @input="formatarCEP" 
            @keyup="verificaAutoPreenchimentoCEP" 
            type="text" 
            id="cep" 
            name="cep" 
            placeholder="12345-000" 
            required 
          />
        </div>

        <div class="modal-form-inputs-wrapper-sp"></div>

        <div class="modal-form-input flex f-column">
          <label for="estado">Estado:</label>
          <select 
            v-model="formData.estado"
            @change="carregarCidades()" 
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
          v-model="formData.cidade"
          name="cidade" 
          id="cidade"
          ref="sel-cidades"
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
        <input 
          v-model="formData.endereco"
          type="text" 
          id="endereco" 
          name="endereco" 
          placeholder="ex.: Rua das Flores N°55 " 
          autocomplete="off" 
        />
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
  line-height: 20pt;

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
  font-weight: 300;

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