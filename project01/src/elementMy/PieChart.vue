<template>
    <div class="Echarts">
        <div id="main" ref="echartM"></div>
    </div>
</template>

<script>
import { color, number } from 'echarts';

export default {
    // props: ['PieChartName', 'classify', 'cost', 'map','inputMonth'],
    props:{
        PieChartName:String,
        classify:{
            default:()=>(['支出','收入']),
            // validator(value){
            //     return value;
            // },
            required:false
        },
        cost:{

            required:false
        },
        map:{

            required:false
        },
    },
    name: "Echarts",
    methods: {
        myEcharts() {
            let charMoney = this.$echarts.init(this.$refs.echartM);
            // let charMoney = this.$echarts.init(document.getElementById("main"));
            //  也可以使用this.charMoney=this.$echarts.init(this.$refs.echartM);只要能获得对应的dom实例即可,但是推荐使用这个，因为
            //echarts是通过 id控制的 也就是 说我们在当前页面出现了 3个id相同的div。然后通过document.getElementById;一直在调用第一个,也就是说一个页面只会渲染一个类型的echart,dma
            // let option = { //注意使用option时要制定 option的类型
            //     title: {
            //         text: this.PieChartName,
            //         // x 设置水平安放位置，默认左对齐，可选值：'center' ¦ 'left' ¦ 'right' ¦ {number}（x坐标，单位px）
            //         x: 'left',
            //         // y 设置垂直安放位置，默认全图顶端，可选值：'top' ¦ 'bottom' ¦ 'center' ¦ {number}（y坐标，单位px）
            //         y: 'top',
            //         // itemGap设置主副标题纵向间隔，单位px，默认为10，
            //         itemGap: 30,
            //         // 主标题文本样式设置
            //         textStyle: {
            //             fontSize: 20,
            //             fontWeight: 'bolder',
            //             color: '#000080'
            //         }
            //     },

            //     legend: {
            //         orient: 'vertical',
            //         x: 'center',
            //         y: 'center',
            //         data:this.classify
            //     },

            //     // color:['#45C2E0', '#C1EBDD', '#FFC851','#5A5476','#1869A0','#FF9393'], //设置echart的圆环部分颜色
            //     series: [
            //         {
            //             type: 'pie',
            //             center: ["14%", "50%"], //设置饼状图位置,第一个表示水平位置，第二个表示垂直位置，以饼状图圆心为坐标
            //             label: {
            //                 show: false,
            //                 position: 'center'
            //             },
            //             labelLine: {
            //                 show: false
            //             },
            //             emphasis: {
            //                 label: {
            //                     show: true,
            //                     fontSize: '30',
            //                     fontWeight: 'bold'
            //                 }
            //             },
            //             data: this.map,
            //             radius: ['40%', '70%']
            //         }
            //     ]
            // };
            let option = {
                title: {
                    text: this.PieChartName,
                    // x 设置水平安放位置，默认左对齐，可选值：'center' ¦ 'left' ¦ 'right' ¦ {number}（x坐标，单位px）
                    x: 'left',
                    // y 设置垂直安放位置，默认全图顶端，可选值：'top' ¦ 'bottom' ¦ 'center' ¦ {number}（y坐标，单位px）
                    y: 'top',
                    // itemGap设置主副标题纵向间隔，单位px，默认为10，
                    itemGap: 30,
                    // 主标题文本样式设置
                    textStyle: {
                        fontSize: 20,
                        fontWeight: 'bolder',
                        color: '#000080'
                    }
                },
                tooltip: {
                    trigger: 'item',
                    textStyle: {
                        fontSize: '10px',
                        color: '#000'  // 设置文本颜色 默认#FFF
                    },

                },
                legend: {
                    orient: 'vertical',
                    x: 'center',
                    y: 'center',
                    data:this.classify
                },
                series: [
                    {
                        name: 'Access From',
                        type: 'pie',
                        radius: ['40%', '70%'],
                        center: ["14%", "50%"],
                        avoidLabelOverlap: false,
                        itemStyle: {
                            borderRadius: 10,
                            borderColor: '#fff',
                            borderWidth: 2
                        },
                        label: {
                            show: false,
                            position: 'center'
                        },
                        emphasis: {
                            label: {
                                show: true,
                                fontSize: 14,
                                fontWeight: 'bold'
                            }
                        },
                        labelLine: {
                            show: false
                        },
                        data: this.map,
                    }
                ]
            };
            charMoney.setOption(option);
        },
    },
    mounted() {
        this.myEcharts();
    }
}

</script>

<style scoped>
#main {
    width: 550px;
    height: 180px;
    margin: auto;
    margin-top: 50px
}
</style>