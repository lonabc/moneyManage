<template>
    <div class="textBoxLine">
        <div class="textTwo">
            <span>开支</span>
            <span><strong style="font-size: 30px;color:#909399" v-showNum  v-if="costShow">{{ costAll }}</strong></span>
        </div>
        <div class="textTwo">
            <span>收入</span>
            <span >
                <strong style="font-size: 30px; color:#909399" v-showNum v-if="getShow">{{ getAll }}</strong>
            </span> 
        </div>

    </div>
</template>


<script>

export default {
    props:['cost','input'],
    data() {
        return {
            costAll: 0,
            costShow:false,
            getAll: 0 ,
            getShow:false
        }
    },
    watch:{
        costAll:{
            handler(item1,item2){
                this.costShow=true;
            }
        },
        getAll:{
            handler(item1,item2){
                this.getShow=true;
            }
        }
    },
    
    mounted(){
   
        this.$nextTick(()=>{
            this.costAll=this.cost;
            this.getAll=this.input
        })
    }
}
</script>

<style>
.textBoxLine {
    display: flex;
    width: 100%;
    height: 80px;
    flex-direction: row;

    /*使用内减模式*/
    box-sizing: border-box;
    justify-content: space-between;
    border-top: 8px solid rgb(193, 190, 194);
    border-bottom: 8px solid rgb(193, 190, 194);
    border-radius: 10%
}

.textTwo {
    width: 40%;
    display: flex;
    flex-direction: column;
    align-items: start;
    /*使用内减模式*/
    box-sizing: border-box;
    justify-content: flex-start;

}
</style>