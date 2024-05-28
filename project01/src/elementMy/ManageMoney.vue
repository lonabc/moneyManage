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
            </template>
          </el-table-column>
        </el-table>
      </el-main>

      <el-footer>
        <div class="block">

          <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
            :current-page.sync="currentPage1" :page-size="10" layout="total, prev, pager, next" :total="1000">
          </el-pagination>
        </div>
      </el-footer>
      <!-- 消费编辑弹框 -->
      <el-dialog :visible.sync="showSizeForm"> 
        <el-form ref="form" :model="sizeForm" label-width="80px" >
          <el-form-item label="活动名称" style="width: 40%;">
            <el-input v-model="sizeForm.classify"></el-input>
          </el-form-item>
          <el-form-item label="消费金额" style="width: 50%;">
            <el-input v-model="sizeForm.cost"></el-input>
          </el-form-item>
          <el-form-item label="活动时间" style="width: 60%;">
              <el-date-picker type="date" placeholder="选择日期" format="yyyy年MM月dd日" value-format="yyyy-MM-dd" v-model="sizeForm.dayTime"
                style="width: 100%;"></el-date-picker>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" round @click=updateMOney()>更新</el-button>
             <el-button type="danger" round>删除</el-button>
          </el-form-item>
        </el-form>

      </el-dialog>


    </el-container>

  </div>
</template>
<script>
import DaoTest from '@/static/DaoTest.vue';
export default {
  name: 'Echarts', //图标1
  refresh: true,
  methods: {
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
    },
    showMoney: function () {
      DaoTest.token = localStorage.getItem(DaoTest.header);
      this.token1 = DaoTest.token;
      this.$axios.get("http://127.0.0.1:8080/user/moneyAll",
        {
          headers: {
            [DaoTest.header]: this.token1
          }
        }
      ).then(
        (success) => {

          this.tableData = success.data.data;

        },
        (error) => {
          console.log("数据获取失败")
        }
      )
    },
    testMethod: function () {
      this.showMoeny = true;
    },
    alterMoney:function()
    {
      this.showSizeForm=true;
    },
    updateMOney:function(){

       this.$axios.post("http://127.0.0.1:8080/user/moneyChange",{
           dayTime:this.sizeForm.dayTime,
           cost:this.sizeForm.cost,
           classify:this.sizeForm.classify
       },{
         headers:{
          [DaoTest.header]: this.token1
         }
       }).then(
          (success)=>{
            console.log("发送成功")
          },
          (error)=>{
            console.log("发送失败")
          }
       )
    }



  },
  data() {

    return {
      sizeForm:{ 
        classify:'',
        dayTime:'',
        cost:''
      },
      showSizeForm:false,
      refresh: true,
      token1: '',
      currentPage1: 0,
      showMoeny: false,
      tableData: [{
        date1: '',
        cost: '',
        type: '',
      }],
      formPage: {
        pageSize: 6,
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