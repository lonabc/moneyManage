<template>
  <div>
    <el-container>
   
  <el-table :data="tableData" style="width: 800px ;" v-if="refresh">
    <el-table-column fixed prop="dayTime" label="消费时间" width="150">
    </el-table-column>
    <el-table-column prop="cost" label="消费金额/元" width="120">
    </el-table-column>
    <el-table-column prop="classify" label="消费类别" width="120">
    </el-table-column>
    <el-table-column fixed="right" label="操作" width="100">
      <template slot-scope="scope">
        <el-button type="text" size="small" @click.native="showMoney()">刷新</el-button>
        <el-button type="text" size="small" >编辑</el-button>
      </template>
    </el-table-column>
  </el-table>
</el-container>
  
</div>
</template>
<script>
import DaoTest from '@/static/DaoTest.vue';
export default {
  name: 'Echarts', //图标1
  refresh:true,
  methods: {
    showMoney:function(){
      DaoTest.token=localStorage.getItem(DaoTest.header);
      this.token1=DaoTest.token;
      this.$axios.get("http://127.0.0.1:8080/user/moneyAll",
        {
          headers:{
          [DaoTest.header]: this.token1
          }
        }
      ).then(
        (success)=>{
             
          this.tableData=success.data.data;

        },
        (error)=>{
          console.log("数据获取失败")
        }
      )
    },
    testMethod: function () {
      this.showMoeny = true;
    },

   
   
  },
  data() {

    return {
      refresh:true,
      token1:'',
      showMoeny:false,
      tableData: [{
        date1: '',
        cost: '',
        type: '',
      }],
     
    }
  },
  mounted()
  {
    this.showMoney();
  }
}
</script>
<style>

</style>