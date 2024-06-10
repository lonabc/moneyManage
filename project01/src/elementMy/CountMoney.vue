<template>
    <div>
  
  <animate-number
    from="1" 
    to="10" 
    duration="1000" 
    easing="easeOutQuad"
    :formatter="formatter"
  ></animate-number>
  
   <!-- 最简单的案例，from是开始值，to是结束值 -->
  <animate-number from="1" to="10"></animate-number>
  
  <animate-number ref="myNum" from="0" to="10" mode="manual" :formatter="formatter"></animate-number><br>
  
   <!-- 也可以通过按钮去触发-->
  <button type="button" @click="startAnimate()"> animate! </button>
</div>
</template>

<script>
  export default {
    methods: {
      formatter: function (num) {
        return num.toFixed(2)
      },

      startAnimate: function () {
        this.$refs.myNum.start()
      }
    }
  }
</script>

<style>

</style>