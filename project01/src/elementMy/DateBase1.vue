<template>
	<div class="Echarts">
<<<<<<< Updated upstream
=======

		<e-header>
			<!-- <el-button type="info" @click="testMethod()">编辑</el-button>
			<el-button type="primary" icon="el-icon-search" @click="showDateChange()">搜索</el-button> -->
			<div class="div2">
				<i class="el-icon-search" @click="showDateChange()"></i>
				<!-- <div style="margin: 0px 20px;"></div> -->
				<i class="el-icon-edit" @click="testMethod()"></i>
				<i class="el-icon-refresh" @click="costAll()"></i>
			</div>
		</e-header>


>>>>>>> Stashed changes
		<el-main>
			<div id="main" style="width: 600px;height:400px;"  v-if="refresh"></div>
		</el-main>
		<el-footer>
			<el-button type="danger" @click="testMethod()">危险按钮</el-button>
			<el-dialog :visible.sync="showMoeny">
				<el-form ref="form" :model="form" label-width="80px" class="d">
					<el-form-item label="今日消费">
						<el-input v-model="form.cost"></el-input>
					</el-form-item>
					<el-form-item label="当日时间">
						<el-col>
							<el-date-picker type="date" placeholder="选择日期" v-model="form.dayTime"
								value-format="yyyy-MM-dd"></el-date-picker>
						</el-col>
					</el-form-item>
					<el-form-item label="消费类型">
						<el-checkbox-group v-model="form.classify" >
							<el-checkbox label="饮食类" name="type"></el-checkbox>
							<el-checkbox label="日用品" name="type"></el-checkbox>
							<el-checkbox label="数码类" name="type"></el-checkbox>
							<el-checkbox label="旅游类" name="type"></el-checkbox>
							<el-checkbox label="学习用品" name="type"></el-checkbox>
							<el-checkbox label="其他" name="type"></el-checkbox>
						</el-checkbox-group>
					</el-form-item>
				</el-form>
				<el-button type="primary" @click="submitMoney">立即创建</el-button>
				<el-button @click="closeForm">取消</el-button>
			</el-dialog>
		</el-footer>
	</div>
</template>
<script>
import DaoTest from '@/static/DaoTest.vue';
export default {
	name: 'Echarts', //图标
	data() {
		return {
		
			form: {
				cost: '',
				dayTime: '',
				classify: [],
			},
			token1: "",
			showMoeny: false,
			refresh: true,
			sum: 0,
			classify: [],
			cost: []
		}
	},
	watch: {
		sum: {
			handler(item1, item2) {
				this.costAll();
			}
		},
		deep: true
	},
	methods: {
		closeForm: function () {
			this.showMoeny = false;
		},
		showFormMy: function () {
			this.showFormMy1 = true;
		},
		testMethod: function () {
			this.showMoeny = true;
		},
		submitMoney() {
			let form1 = new FormData();
			form1.append("form1", JSON.stringify(this.form));
			this.$axios.post("http://127.0.0.1:8080/user/moneyRember", form1, {
				headers: {
					[DaoTest.header]: localStorage.getItem(DaoTest.header)
				}
			}).
				then(
					(success) => {

						this.refresh = false;
						this.sum++;
						this.refresh = true;
						this.closeForm();
						//   alert("数据传输成功");
					},
					(error) => {
						alert("数据传输失败");
					}
				).
				catch(error)
			{
				alert("数据传输错误");
			}
		},
		costAll() {
			DaoTest.token = localStorage.getItem(DaoTest.header);

			this.token1 = DaoTest.token;
			this.$axios.get("http://localhost:8080/user/CostAll", {
				headers: {
					[DaoTest.header]: this.token1
				}
			}).then(
				(success) => {
					this.classify = success.data.data;
					this.cost = success.data.testData;
					this.myEcharts();
				}

			)
		},


		myEcharts() {
			//图标方法
			// 基于准备好的dom，初始化echarts实例

			var myChart = this.$echarts.init(document.getElementById('main'));

			// 指定图表的配置项和数据
			 var option= {
				title: {
					text: '个人开销'
				},
				tooltip: {},
				legend: {
					data: ['个人开销']
				},

				xAxis: {
					data: this.classify
				},
				yAxis: {},
				cost1: '',
				series: [{
					name: '个人开销',
					type: 'bar',
					data: this.cost,
					animationDelay: function () {
						return 100 * 10;
					}
				}
				]
			}
			// 使用刚指定的配置项和数据显示图表。
			myChart.setOption(option);
			
		}
	},
	mounted() {
		this.costAll();
		

	}
}

</script>
<style>
.d {
	display: flex;
	flex-wrap: wrap;
	flex-direction: column;
	align-items: flex-start;
}
</style>