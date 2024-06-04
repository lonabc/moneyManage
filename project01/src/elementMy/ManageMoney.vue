<template>
  <div>
    <el-container>

      <el-main>
        <el-table :data="tableData" style="width: 800px ;" v-if="refresh">
          <el-table-column fixed prop="dayTime" label="消费时间" width="150">
          </el-table-column>
          <el-table-column prop="cost" label="消费金额/元" width="120">
          </el-table-column>
          <el-table-column prop="classify" label="消费类别" width="120">
          </el-table-column>
          <el-table-column fixed="right" label="操作" width="120">
            <template slot-scope="scope">
              <el-button type="text" size="small" @click.native="showMoney()">刷新</el-button>
              <el-button type="text" size="small" @click.native="alterMoney()">编辑</el-button>
              <el-button type="text" size="small" @click.native="show1fun()">查找</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-main>

      <el-footer>
        <div class="block">

          <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
            :current-page.sync="currentPage1" :page-size="page_size" layout="total, prev, pager, next"
            :total="totalMoneyItem">
          </el-pagination>
        </div>
      </el-footer>
      <!-- 消费编辑弹框 -->
      <el-dialog :visible.sync="showSizeForm">
        <el-form ref="form" :model="sizeForm" label-width="80px">
          <!-- <el-form-item label="活动名称" style="width: 40%;">
            <el-input v-model="sizeForm.classify"></el-input>
          </el-form-item> -->
          <el-form-item label="消费类型">
						<el-checkbox-group v-model="sizeForm.classify">
							<el-checkbox label="饮食类" name="type"></el-checkbox>
							<el-checkbox label="日用品" name="type"></el-checkbox>
							<el-checkbox label="数码类" name="type"></el-checkbox>
							<el-checkbox label="旅游类" name="type"></el-checkbox>
							<el-checkbox label="学习用品" name="type"></el-checkbox>
							<el-checkbox label="其他" name="type"></el-checkbox>
						</el-checkbox-group>
					</el-form-item>
          <el-form-item label="消费金额" style="width: 50%;">
            <el-input v-model="sizeForm.cost"></el-input>
          </el-form-item>
          <el-form-item label="活动时间" style="width: 60%;">
            <el-date-picker type="date" placeholder="选择日期" format="yyyy年MM月dd日" value-format="yyyy-MM-dd"
              v-model="sizeForm.dayTime" style="width: 100%;"></el-date-picker>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" round @click=updateMOney()>更新</el-button>
            <el-button type="danger" round @click=deleteItem()>删除</el-button>
          </el-form-item>
        </el-form>

      </el-dialog>
      <el-dialog :visible.sync="show1">
        <el-date-picker v-model="textarea1" type="date" placeholder="选择日期" format="yyyy 年 MM 月 dd 日"
          value-format="yyyy-MM-dd">
        </el-date-picker>

        <div style="margin: 20px 0;"></div>
        <el-button round @click="selectMoneyByTime1()">确认</el-button>
        <el-button type="primary" round @click="show1fun()">取消</el-button>
      </el-dialog>


    </el-container>

  </div>
</template>
<script>
import DaoTest from '@/static/DaoTest.vue';
export default {
  name: 'Echarts', //图标1
  refresh: true,
  watch: {
    trigger: {
      handler(item1, item2) {
        this.showMoney();

      }
    }
  },
  methods: {

    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      this.trigger = val
      this.formPage.pageStart = val;
      console.log(`当前页: ${this.formPage.pageStart}`);
    },
    selectMoneyByTime1: function () {
      this.$axios.get(`http://127.0.0.1:8080/user/CostTime/${this.textarea1}`,
       {
          headers: {
            [DaoTest.header]: this.token1
          }
        }).then(
        (success)=>{
          this.refresh=false;
          this.tableData=success.data.testData;
          console.log("传数成功");
          this.refresh=true
        }
      ).catch(error)
      {
        console.log("传输失败");
      }
    },
    show1fun: function () {
      this.show1 = !this.show1;
    },
    showMoney: function () {
      DaoTest.token = localStorage.getItem(DaoTest.header);
      this.token1 = DaoTest.token;

      this.$axios.get(`http://127.0.0.1:8080/user/moneyAll/${this.formPage.pageStart}&${this.formPage.pageSize}`,
        {
          headers: {
            [DaoTest.header]: this.token1
          }
        }
      ).then(
        (success) => {
          this.tableData = success.data.data;
          this.totalMoneyItem = success.data.testData;
        },
        (error) => {
          console.log("数据获取失败")
        }
      )
    },
    testMethod: function () {
      this.showMoeny = true;
    },
    alterMoney: function () {
      this.showSizeForm = true;
    },
    deleteItem:function()
    {
      this.$axios.post("http://127.0.0.1:8080/user/deleteItem",{
        dayTime: this.sizeForm.dayTime,
        cost: this.sizeForm.cost,
        classify: this.sizeForm.classify
      },{
        headers: {
          [DaoTest.header]: this.token1
        }
      }).then(
        (success)=>{
           console.log("删除成功")
        }
      )
    },
    updateMOney: function () {

      this.$axios.post("http://127.0.0.1:8080/user/moneyChange", {
        dayTime: this.sizeForm.dayTime,
        cost: this.sizeForm.cost,
        classify: this.sizeForm.classify
      }, {
        headers: {
          [DaoTest.header]: this.token1
        }
      }).then(
        (success) => {
          this.refresh=false;
          console.log("发送成功");
          this.refresh=true;
        },
        (error) => {
          console.log("发送失败")
        }
      )
    },
    selectMoneyByTime: function () {
      this.$axios.get("http://127.0.0.1:8080/user/selectMoneyByTime", this.selectTime, {
        headers: {
          [DaoTest.header]: this.token1
        }
      }).then(
        (success) => {
          this.tableData = success.data.data;
        }
      ).catch(error)
      {
        alert("传输失败");
      }
    }



  },
  data() {

    return {
      sizeForm: {
        classify: [],
        dayTime: '',
        cost: ''
      },
      textarea1: '',
      show1: false,
      selectTime: '',
      page_size: 5,
      trigger: 0,
      showSizeForm: false,
      refresh: true,
      token1: '',
      currentPage1: 0,
      totalMoneyItem: 110,
      showMoeny: false,
      tableData: [{
        date1: '',
        cost: '',
        type: '',
      }],
      formPage: {
        pageSize: 5,
        pageStart: 1,
      }

    }
  },
  mounted() {
    this.showMoney();
  }
}
</script>
<style></style>