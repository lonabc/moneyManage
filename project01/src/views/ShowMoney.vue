<template>
    <div>
        <div class="justify">
            <div class="parentBox" style=" background-color: #E4E7ED;">
                <p>本月消费共计</p>
                <h1 style="color:#409EFF" v-showNum v-if="showMoney">{{ allMoneyMonth }}</h1>
            </div>
            <div class="parentBox" style=" background-color: #67C23A;">
                <p>本周消费共计</p>
                <h1 style="color: #b3e19d;" v-showNum v-if="showWeek">{{ weekMoney }}</h1>
            </div>
            <div class="parentBox" style=" background-color: #e6a23a;">
                <p>本日消费共计</p>
                <h1 style="color:bisque" v-showNum v-if="showDay">{{ dayMoney }}</h1>
            </div>
        </div>

        <Fence v-if="chartShow1" :cost="allMoneyMonth" :input="inputMoney"></Fence>
        <div style="display: flex; justify-content:flex-start;">
            <div class="PieChart">
                <div v-if="chartShow">
                    <PieChart :PieChartName="PieChartName" :key="2" :classify="classify" :cost="cost" :map="map">
                    </PieChart>
                </div>
                <div v-if="chartShow1">
                    <PieChart :PieChartName="PieChartName1" :key="1" :map="inputMonth"></PieChart>
                </div>
            </div>
            <div>
                <LineChart></LineChart>
            </div>
        </div>
    </div>
</template>

<script>
import { mapState } from 'vuex';
import TestDao from '../static/DaoTest.vue'
import request from '@/utils/request.js'
import { getPicCode, getMoneyDay, getCostAll, getInputAll } from '@/utils/myApi.js'
import { Result } from 'element-ui';
import PieChart from '@/elementMy/PieChart.vue';
import Fence from '@/components/Fence.vue'
import LineChart from '@/elementMy/LineChart.vue';

export default {
    data() {
        return {
            PieChartName: '本月消费各类支出',
            PieChartName1: '本月消费和支出占比',
            chartShow: true,
            chartShow1: true,
            showMoney: false,
            showWeek: false,
            showDay: false,
            month: '2024-06',
            weekMoney: 0,
            allMoneyMonth: 0,
            dayMoney: 0,
            inputMoney: 0,
            inputMonth: [],
            classify: [],
            cost: [],
            map: [],
            cost: {
                name: '支出',
                vlaue: this.allMoneyMonth
            }
        }
    },
    computed: {
        ...mapState(['count', 'areLogin'])
    },
    watch: {
        allMoneyMonth: {
            handler() {
                this.chartShow = false;
                this.chartShow1 = false
                this.$nextTick(() => {
                    this.costAllMy()
                })
            }
        }
    },

    methods: {
        testww() {
            request({
                methods: "get",
                url: `http://127.0.0.1:8080/show/totalMonth/${this.month}`,
                headers: {
                    [TestDao.header]: localStorage.getItem('token')
                }
            }).then(
                (success) => {
                    this.allMoneyMonth = success.data;
                    this.showMoney = true;
                }

            )
        },
        testWeek1() {
            getPicCode(this.$moment().format('YYYY-MM-DD'), localStorage.getItem('token')).then(
                result => {
                    this.weekMoney = result.data
                    this.showWeek = true
                }
            )
        },
        testDay() {
            getMoneyDay(localStorage.getItem('token')).then(
                (success) => {
                    this.dayMoney = success.data
                    this.showDay = true
                }
            )
        },
        costAllMy() {
            getCostAll(localStorage.getItem('token')).then(
                (success) => {
                    this.classify = success.data.data;
                    this.cost = success.data.testData;
                    for (let i = 0; i < this.classify.length; i++) {
                        let freeTime = new Object();
                        freeTime.value = this.cost[i];
                        freeTime.name = this.classify[i];
                        this.map[i] = freeTime;
                        this.chartShow = true;
                    }
                }
            ),
                getInputAll(localStorage.getItem('token')).then(
                    (success) => {
                        let fire = {};
                        fire.value = success.data;
                        this.inputMoney = success.data;
                        fire.name = '收入'
                        let cost = {};
                        cost.name = '支出';
                        cost.value = this.allMoneyMonth;
                        this.inputMonth[0] = fire;
                        this.inputMonth[1] = cost;
                        this.chartShow1 = true;
                    }
                )

        }


    },
    created: function () {
        this.$nextTick(() => {
            this.testww();
            this.testWeek1();
            this.testDay();
        })
    },
    mounted: function () {


    }
}

</script>

<style>
.justify {
    display: flex;
    flex-direction: row;
    margin: auto;
    width: 100%;
    height: 50%;
    justify-content: space-around;
    /* 设置内减模式 */
    box-sizing: border-box;
    padding-bottom: 20px;
}


.parentBox {
    display: flex;
    flex-direction: column;
    justify-content: center;
    height: 250px;
    width: 200px;
    box-sizing: border-box;
}

.PieChart {
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    align-items: flex-start
}
</style>