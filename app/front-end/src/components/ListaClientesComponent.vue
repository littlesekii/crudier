<script setup>
import http from "@/utils/http";
import { onMounted, ref } from "vue";

const backendBaseURL = "http://localhost:1001";

let clientes = ref([]);

onMounted(() => {
  http.get(`${backendBaseURL}/cliente`)
    .then(res => res.json())
    .then(data => {
      clientes.value = data;
    });
});


</script>

<template>
  <div class="list flex f-column">
    <div class="list-row">
      <div class="list-column lc-title lc-check">
        <input type="checkbox" />
      </div>
      <div class="list-column lc-title">
        ID
      </div>
      <div class="list-column lc-title f-3">
        NOME
      </div>
      <div class="list-column lc-title f-2">
        E-MAIL
      </div>
      <div class="list-column lc-title f-2">
        TELEFONE
      </div>
      <div class="list-column lc-title lc-menu">
        
      </div>
  </div>
  <div v-for="cliente, index in clientes" :key="index" class="list-row">
      <div class="list-column lc-check">
        <input type="checkbox" />
      </div>
      <div class="list-column">
        {{ cliente.id }}
      </div>
      <div class="list-column f-3">
        {{ cliente.nome }}
      </div>
      <div class="list-column f-2">
        {{ cliente.email }}
      </div>
      <div class="list-column f-2">
        {{ cliente.telefone }}
      </div>
      <div class="list-column lc-menu">
        ...
      </div>
  </div>  

  </div>
</template>



<style scoped>
  .list {
    width: 1040px;
    max-width: 90%;

    height: 70vh;
    max-height: 90vh;

    /* padding: 20px; */

    border-radius: 15px;
    border: 1px solid var(--black-softer);

    overflow-y: auto;
  }

  .list-row {
    display: flex;
    /* justify-content: space-around; */

    border-bottom: 1px solid var(--black-softer);

    padding: 15px;

    /* background-color: aqua; */
  }

  .list-column {
    flex: 1;
    width: 50px;
    
    margin-right: 20px;

    /* justify-content: center;
    align-items: center; */

    /* background-color: antiquewhite; */


    -o-text-overflow: ellipsis;
    -ms-text-overflow: ellipsis;
    text-overflow: ellipsis;
    overflow: hidden;
    white-space: nowrap;
    /* text-overflow: ellipsis; */
  }

  .lc-title {
    font-weight: bold;
    font-size: 12pt;
  }

  .lc-check {
    padding: 0px;
    max-width: 25px;
  }

  .lc-menu {
    padding: 0px;
    max-width: 15px;
    text-align: center;   
  }

  .f-2 {
    flex: 2;
  }
  .f-3 {
    flex: 3;
  }
</style>