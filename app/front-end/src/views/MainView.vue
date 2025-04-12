<script setup>
import ModalCliente from "@/components/ModalCliente.vue";
import { ref } from "vue";

const showCreateModal = ref(false);


</script>

<template>
  <main class="flex f-column f-centered">
    <p>Hello, Crudier!</p>
    <button @click="showCreateModal = true">Abrir Modal</button>
    <ModalCliente v-if="showCreateModal" @modalclose="showCreateModal = false" />  
  </main>
  
  
</template>


<style scoped>
main {
  height: 100vh;
}

main p {
  font-size: 18pt;
}
</style>