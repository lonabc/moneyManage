<template>
  <div class="home">
    <img alt="Vue logo" src="../assets/logo.png">
    <HelloWorld msg="Welcome to Your Vue.js + TypeScript App"/>
    <comment-box></comment-box>
    <test12></test12>
       
  </div>
 
  
  
</template>

<script lang="ts">
import Vue from 'vue'
import HelloWorld from '@/components/HelloWorld.vue' // @ is an alias to /src
import comment from '../elementMy/TestYin.vue'

export default Vue.extend({
  name: 'HomeView',
  components: {
    "comment-box":comment,                                                                                             
    HelloWorld
  }
})
</script>

