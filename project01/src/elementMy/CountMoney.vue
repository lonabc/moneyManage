<template>
    <div>
        <div style="width: 100%;">
            <animate-number from="0" to="1000" :formatter="formatter" ref="myNum" mode="manual"></animate-number>
        </div>
        <button type="button" @click="startAnimate">点击</button>
        <br>
    </div>
</template>
<script>


export default {
    data() {
        return {
            errorCount: 20 // 绑定ref和key，接口拿到的数据可以直接赋值；
        }
    },
    methods: {
        formatter: function (num) {
            return num.toFixed(2) //小数点后几位，数字就是几小数点后几位
        },
        startAnimate: function () { //按钮点击触发事件
            this.$refs.myNum.start()
        }
    },
    mounted:function(){
        this.startAnimate()
    },

}

</script>


<style></style>