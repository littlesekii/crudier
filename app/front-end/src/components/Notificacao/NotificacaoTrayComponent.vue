<script setup>
import { ref } from "vue";
import NotificacaoComponent from "./NotificacaoComponent.vue";

const queue = ref([]);

function notify(success, msg) {
  queue.value.push({id:performance.now(), success:success, msg:msg});
}

function freeNotification(id) {
  let auxQueue = []; 
  queue.value.forEach(e => {
    if (e.id !== id)
      auxQueue.push(e);
  });
  queue.value = auxQueue;
}

defineExpose({notify});

</script>

<template>
  <div class="notification-tray flex f-column" ref="div-notification-tray">
    <NotificacaoComponent @vanished="freeNotification(notification.id)" v-for="notification in queue" :key="notification.id" :success="notification.success" :msg="notification.msg" />

  </div>
</template>

<style scoped>
.notification-tray {

  max-width: 100%;
  width: 300px;
  /* min-height: 100vh; */

  /* padding: 20px; */

  justify-content: flex-end;

  position: fixed;
  right: 0;
  bottom: 0;

  /* background-color: aliceblue; */
}

/* @media (max-width: 400px) {
  .notification-tray {
    max-width: 100%;
  }
} */
</style>

