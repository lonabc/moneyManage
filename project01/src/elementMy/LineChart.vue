<template>
    <div>
        <div ref="lineChart" id="main" class="LineSpace"></div>
    </div>
</template>
<script>
import {getLineData} from '@/utils/myApi.js'
import { Result } from 'element-ui'
export default {
    
    data() {
        return {
            daytime:[],
            costday:[],
            costImage:[],
            complete:false
        }
    },
    watch:{
        complete:{
            handler()
            {
                this.myLine();
            }
        }
    },
    methods: {
        getLineDataMy(){
             getLineData(localStorage.getItem('token')).then(
                 (success)=>{
                    this.daytime=success.data.data;
                    this.costday=success.data.testData;
                    console.log(this.daytime);
                    console.log(this.costday);
                    for(let i=0;i<this.daytime.length;i++)
                    {
                        this.costImage[i]=30;
                    }
                    console.log(this.costImage);
                    this.complete=true;
                 }
            )
        },
        myLine() {
            let lineC = this.$echarts.init(this.$refs.lineChart);
            let option = {
                xAxis: {
                    data: this.daytime
                },
                legend: {
                    // orient 设置布局方式，默认水平布局，可选值：'horizontal'（水平） ¦ 'vertical'（垂直）
                    orient: 'horizontal',
                    // x 设置水平安放位置，默认全图居中，可选值：'center' ¦ 'left' ¦ 'right' ¦ {number}（x坐标，单位px）
                    x: 'right',
                    // y 设置垂直安放位置，默认全图顶端，可选值：'top' ¦ 'bottom' ¦ 'center' ¦ {number}（y坐标，单位px）
                    y: 'center',
                    data: ['预期', '实际']
                },
                grid: {
                    // top: '16%',   // 等价于 y: '16%'
                    left: '2%',
                    right: '16%',
                    bottom: '3%',
                    containLabel: true
                },

                yAxis: {},
                series: [
                    {
                        name: '预期',
                        data: this.costday,
                        type: 'line',
                        // 设置小圆点消失
                        symbolSize: 8,
                        // itemStyle: {
                        //     normal: {
                        //         // 拐点上显示数值
                        //         label: {
                        //             show: true
                        //         },
                        //         borderColor: 'red',  // 拐点边框颜色
                        //         lineStyle: {
                        //             width: 5,  // 设置线宽
                        //             type: 'solid'  //'dotted'虚线 'solid'实线
                        //         }
                        //     }
                        // },
                        // 注意：设置symbol: 'none'以后，拐点不存在了，设置拐点上显示数值无效
                        // symbol: 'none',
                        // 设置折线弧度，取值：0-1之间
                        smooth: 0.5,
                    },

                    {
                        name: '实际',
                        data: this.costImage,
                        type: 'line',
                        // 设置折线上圆点大小
                        symbolSize: 8,
                        itemStyle: {
                            normal: {
                                // 拐点上显示数值
                                label: {
                                    show: true
                                },
                                borderColor: 'red',  // 拐点边框颜色
                                lineStyle: {
                                    width: 5,  // 设置线宽
                                    type: 'dotted'  //'dotted'虚线 'solid'实线
                                }
                            }
                        },
                        smooth: 0.5,
                    },
                ]
            };
            lineC.setOption(option)
        },

    },
    
    mounted() {
       this.getLineDataMy()
    }
}
</script>
<style>
.LineSpace {
    display: flex;
    justify-content: right;
    height: 500px;
    width: 1080px;
    margin: 0;

}
</style>