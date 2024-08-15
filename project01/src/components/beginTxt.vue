<template>
    <div>
        <lottie :options="defaultOptions" :width="240" :height="240" v-on:animCreated="handleAnimation" />
    </div>
</template>

<script>
import Anim from "../static/word.json"
export default {
    data() {
        return {
            defaultOptions: { animationData: Anim, loop: false, autoplay: true},
            defaultAnim: ''
        }
    },
    methods: {
        handleAnimation(anim) {
            this.defaultAnim = anim;
        }
    },
    mounted(){
        Anim.play(); 
    }
}


</script>

<style></style>