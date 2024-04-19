<template id="app">
  <div>
  <el-table :data="tableData" style="width: 100%">
    <el-table-column prop="age" label="年龄" width="180">
    </el-table-column>
    <el-table-column prop="birthday" label="生日" width="180">
    </el-table-column>
    <el-table-column prop="name" label="姓名" width="180">
    </el-table-column>
    <el-table-column prop="school" label="地址">
    </el-table-column>
  </el-table>
  
</div>
  
</template>
  
  
<script>
//const AsyncComponent = () => import('../TestNew.vue')
import TestDao from '../static/DaoTest'
import DaoLogin from '../static/DaoLogin'
export default {
  name:"Table",
  data(){
    return {
      token:'',
      tableData:[],
    };
  },
    methods:{
      getTable()
      {
        TestDao.token = localStorage.getItem(TestDao.header)
        this.token=TestDao.token
        this.$axios.get("http://127.0.0.1:8080/test", {
        headers: {
          [TestDao.header]: this.token
        }
      })
      .then(
        // var myData=JSON.stringify(result.data.data)
        // this.tableData=myData
        // alert(this.tableData)
        (success)=>{
          console.log("请求成功");
          console.log(success);
          this.tableData=success.data.data;
        },
        (error)=>{
          console.log("请求失败");
        }
        
      ).catch((error) => {
        console.log(error);
      });
      }
    },
    mounted(){
      this.getTable()
    }

 
  
  
}
</script>../DaoLogin