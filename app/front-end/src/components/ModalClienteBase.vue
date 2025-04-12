<script setup>
const emit = defineEmits(["modalclose"]);
</script>

<template>
  <div class="modal flex f-centered">
    <div class="modal-bg" @click="emit('modalclose')"></div>
    <div class="modal-container">
        <slot></slot>
    </div>
    
  </div>
  
</template>

<style scoped>
.modal {
  width: 100%;
  min-height: 100vh;

  position: fixed;

  z-index: 1;
}

.modal-bg {
  width: 100%;
  min-height: 100vh;

  position: fixed;
  
  opacity: .8;
  background-color: var(--black);

  z-index: 2;
}

.modal-container {
  max-width: 90%;
  width: 700px;
  max-height: 90vh;
  padding: 20px;

  opacity: 1;

  border-radius: 20px;

  background-color: var(--white);

  z-index: 3;

  overflow-y: auto;
  overflow-x: hidden;
}

.modal-container::-webkit-scrollbar {
  width: 5px;
  max-height: 10px;
  
}

/* Track */
.modal-container::-webkit-scrollbar-track {
  /* box-shadow: inset 0 0 2px var(--gray);  */
  border-radius: 10px;
  margin-top: 17px;
  margin-bottom: 17px;
}
 
/* Handle */
.modal-container::-webkit-scrollbar-thumb {
  background: var(--gray); 
  border-radius: 10px;  
}
.modal-container::-webkit-scrollbar-track-piece:start {
  /* margin-top: 15px; */
}
.modal-container::-webkit-scrollbar-track-piece:end {
  /* margin-bottom: 15px; */
}
</style>