<script setup>
import ListaClientesComponent from "@/components/ListaClientesComponent.vue";
import ModalClienteComponent from "@/components/ModalCliente/ModalClienteComponent.vue";
import NotificacaoTrayComponent from "@/components/Notificacao/NotificacaoTrayComponent.vue";
import { ref, useTemplateRef } from "vue";

const showCreateModal = ref(false);

const notificationTray = useTemplateRef("notification-tray");

function modalSaved(success, msg) {
  showCreateModal.value = false;
  notificationTray.value.notify(success, msg);
}

</script>

<template>
  <main class="flex f-column f-centered">
    <!-- <p>Hello, Crudier!</p>
    <button @click="showCreateModal = true">Abrir Modal</button> -->
    
    <ListaClientesComponent />

    <ModalClienteComponent 
    v-if="showCreateModal" 
    @modalclose="showCreateModal = false"
    @modalsave="modalSaved" 
    />  
    <NotificacaoTrayComponent ref="notification-tray"/>
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