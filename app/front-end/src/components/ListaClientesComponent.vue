<script setup>
import http from "@/utils/http";
import { onMounted, ref } from "vue";

import iconTrash  from "@/assets/icons/trash.svg";
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
    <div class="list-row lr-title">
      <div class="list-column lc-title lc-check">
        <input type="checkbox" />
      </div>
      <div class="list-column lc-title">
        ID
      </div>
      <div class="list-column lc-title f-2">
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
    <div class="list-rows">
      <div 
        v-for="cliente, index in clientes" 
        :key="index" 
        class="list-row lr-data"
      >
        <div class="list-column lc-check">
          <input type="checkbox" />
        </div>
        <div class="list-column">
          {{ cliente.id }}
        </div>
        <div class="list-column f-2">
          {{ cliente.nome }}
        </div>
        <div class="list-column f-2">
          {{ cliente.email }}
        </div>
        <div class="list-column f-2">
          {{ cliente.telefone }}
        </div>
        <div class="list-column lc-menu">
          <img class="button-trash" :src="iconTrash" alt="jj">
        </div>
      </div>  
    </div>
    <div class="list-row lr-footer">
      fsdfdsf
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
  }

  .list-rows {
    overflow-y: auto;
  }

  .list-row {
    max-height: 51px;
    padding: 15px;
    
    display: flex;
    /* justify-content: space-around; */

    /* border-bottom: 1px solid var(--black-softer); */



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

  .lr-title {
    border-bottom: 1px solid var(--black-softer);
  }
  .lr-footer {
    border: none;
    border-top: 1px solid var(--black-softer);
  }
  
  .lr-data {
    border-bottom: 1px solid var(--black-soft);
    font-size: 11pt;
  }
  .lr-data:last-of-type {
    border: none;
  }
  .lc-title {
    font-weight: bold;
    font-size: 12pt;    
  }

  .lc-check {
    padding: 0px;
    padding-top: 1.5px;
    max-width: 25px;
  }
  .lc-check input {
    width: 18px;
  }

  .lc-menu {
    padding: 0px;

    display: flex;
    align-items: center;
    justify-content: flex-end;
    /* max-width: 15px; */  
    /* background-color: aliceblue;  */
  }

  .f-2 {
    flex: 4;
  }
  .f-3 {
    flex: 3;
  }

  .button-trash {
    width: 18px;

    cursor: pointer;
    user-select: none;
  }
</style>
