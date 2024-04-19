<template>
  <el-container style="height: 500px; border: 1px solid #eee">
    <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
      <el-menu :default-openeds="['1', '3']">
        <el-submenu index="1">
          <template slot="title"><i class="el-icon-message"></i>导航一</template>
          <el-menu-item-group>
            <template slot="title">分组一</template>
            <el-menu-item index="1-1">选项1</el-menu-item>
            <el-menu-item index="1-2">选项2</el-menu-item>
          </el-menu-item-group>
          <el-menu-item-group title="分组2">
            <el-menu-item index="1-3">选项3</el-menu-item>
          </el-menu-item-group>
          <el-submenu index="1-4">
            <template slot="title">选项4</template>
            <el-menu-item index="1-4-1">选项4-1</el-menu-item>
          </el-submenu>
        </el-submenu>
        <el-submenu index="2">
          <template slot="title"><i class="el-icon-menu"></i>导航二</template>
          <el-menu-item-group>
            <template slot="title">分组一</template>
            <el-menu-item index="2-1">选项1</el-menu-item>
            <el-menu-item index="2-2">选项2</el-menu-item>
          </el-menu-item-group>
          <el-menu-item-group title="分组2">
            <el-menu-item index="2-3">选项3</el-menu-item>
          </el-menu-item-group>
          <el-submenu index="2-4">
            <template slot="title">选项4</template>
            <el-menu-item index="2-4-1">选项4-1</el-menu-item>
          </el-submenu>
        </el-submenu>
        <el-submenu index="3">
          <template slot="title"><i class="el-icon-setting"></i>导航三</template>
          <el-menu-item-group>
            <template slot="title">分组一</template>
            <el-menu-item index="3-1">选项1</el-menu-item>
            <el-menu-item index="3-2">选项2</el-menu-item>
          </el-menu-item-group>
          <el-menu-item-group title="分组2">
            <el-menu-item index="3-3">选项3</el-menu-item>
          </el-menu-item-group>
          <el-submenu index="3-4">
            <template slot="title">选项4</template>
            <el-menu-item index="3-4-1">选项4-1</el-menu-item>
          </el-submenu>
        </el-submenu>
      </el-menu>
    </el-aside>


    <el-container>
      <el-header style="text-align: right; font-size: 12px">
        <el-dropdown>
          <i class="el-icon-setting" style="margin-right: 15px"></i>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item @click.native="setFind()">查找</el-dropdown-item>

            <el-dialog :visible.sync="visible" :append-to-body="true">
              <el-input v-model="inputValue" placeholder="请输入需要查找的人员姓名" id="yinFirst" @keyup.enter.native="handleEnter">
              </el-input>
              <el-button type="button" @click="sumbitFind()">确认</el-button>
              <el-button type="button" @click="closeDialog()">取消</el-button>
            </el-dialog>


            <el-dropdown-item @click.native="showFormMy()">新增</el-dropdown-item>

            <el-dialog :visible.sync="showFormMy1" :append-to-body="true">

              <el-radio-group v-model="labelPosition" size="small">
                <el-radio-button label="left">左对齐</el-radio-button>
                <el-radio-button label="right">右对齐</el-radio-button>
                <el-radio-button label="top">顶部对齐</el-radio-button>
              </el-radio-group>
              <div style="margin: 20px;"></div>
              <el-form :label-position="labelPosition" label-width="80px" :model="formLabelAlign">
                <el-form-item label="名称">
                  <el-input v-model="formLabelAlign.name"></el-input>
                </el-form-item>
                <!-- <el-form-item label="活动日期">
                  <el-input v-model="formLabelAlign.birthday"></el-input>
                </el-form-item> -->
                <el-form-item label="活动时间">
                  <el-col :span="11">
                    <el-date-picker type="date" placeholder="选择日期"  value-format="yyyy-MM-dd" v-model="formLabelAlign.birthday"
                      style="width: 100%;"></el-date-picker>
                  </el-col>
                  <el-col class="line" :span="2">-</el-col>
                </el-form-item>
                <el-form-item label="年龄">
                  <el-input v-model="formLabelAlign.age"></el-input>
                </el-form-item>
                <el-form-item label="活动地点">
                  <el-input v-model="formLabelAlign.school"></el-input>
                </el-form-item>
              </el-form>

              <el-button type="button" @click="testForm()" id="ha" class=".testSumbit">sumbit</el-button>
            </el-dialog>

            <el-dropdown-item @click.native="updataTest()">更新</el-dropdown-item>
            <el-dialog :visible.sync="updataReally" :append-to-body="true">
              <el-radio-group v-model="labelPosition" size="small">
                <el-radio-button label="left">左对齐</el-radio-button>
                <el-radio-button label="right">右对齐</el-radio-button>
                <el-radio-button label="top">顶部对齐</el-radio-button>
              </el-radio-group>
              <div style="margin: 20px;"></div>
              <el-form :label-position="labelPosition" label-width="80px" :model="formLabelAlignUpdata">
                <el-form-item label="名称">
                  <el-input v-model="formLabelAlignUpdata.name" placeholder="必填"></el-input>
                </el-form-item>
                <el-form-item label="活动时间">
                  <el-col :span="11">
                    <el-date-picker type="date" placeholder="选择日期"  value-format="yyyy-MM-dd" v-model="formLabelAlignUpdata.birthday"
                      style="width: 100%;"></el-date-picker>
                  </el-col>
                  <el-col class="line" :span="2">-</el-col>
                </el-form-item>
                <el-form-item label="参加年龄">
                  <el-input v-model="formLabelAlignUpdata.age"></el-input>
                </el-form-item>
                <el-form-item label="活动地点">
                  <el-input v-model="formLabelAlignUpdata.school"></el-input>
                </el-form-item>
              </el-form>

              <el-button type="button" @click="updataForm()">sumbit</el-button>
            </el-dialog>

            <el-dropdown-item @click.native="setEyes()">删除</el-dropdown-item>
            <!--  对话框出现时弹框未出现，添加:append-to-body="true"即可· -->
            <el-dialog :visible.sync="visableReally" :append-to-body="true">
              <el-input v-model="inputValue" placeholder="请输入需要删除的人员姓名"> </el-input>
              <el-button type="button" @click="sumbit()" onclick="location.reload();">确认删除</el-button>
              <el-button type="button" @click="closeDialog()">取消操作</el-button>
            </el-dialog>

          </el-dropdown-menu>
        </el-dropdown>
        <span>殷传国的自制网站测试版</span>
      </el-header>

      <el-main>
        <el-table :data="tableData">
          <el-table-column prop="age" label="今日消费" width="140">
          </el-table-column>
          <el-table-column prop="birthday" label="日期" width="140">
          </el-table-column>
          <el-table-column prop="name" label="姓名" width="120">
          </el-table-column>
          <el-table-column prop="school" label="地址" width="120">
          </el-table-column>
        </el-table>
      </el-main>


    </el-container>
  </el-container>
</template>



<script>
import comment from '../elementMy/TestYin.vue'
import TestDao from '../static/DaoTest.vue'

export default {
  data() {
    return {
      inputValue: '',
      tableData: [],
      visible: false,
      visableReally: false,
      showFormMy1: false,
      updataReally: false,
      token1: '',
      labelPosition: 'right',
      formLabelAlign: {
        name: '',
        birthday: '',
        age: '',
        school: ''
      },
      formLabelAlignUpdata: {
        name: '',
        birthday: '',
        age: '',
        school: ''
      }
    }
  },
  methods: {
    getTable() {
      TestDao.token = localStorage.getItem(TestDao.header)
      this.token1 = TestDao.token
      // alert(this.token1)
      // alert(TestDao.header)
      this.$axios.get("http://127.0.0.1:8080/test", {
        headers: {
          [TestDao.header]: this.token1
        }
      }).then(
        (myResult) => {
          this.tableData = myResult.data.data;
        }
      ).catch(error)
      {
        
      }
    },
    handleEnter() {
      this.sumbitFind();
    }
    ,
    testForm() {//向后端传递参数方法二
      let formData = new FormData()
      formData.append("form", JSON.stringify(this.formLabelAlign))
      this.$axios.post("http://127.0.0.1:8080/insert", formData, {
        headers: {
          [TestDao.header]: this.token1
        }
      }).then(
        result => {
          //alert(result.data.age);
          alert("数据库已更新")
        }
      )
      this.closeDialog();
    },
    updataForm() {
      let eupdata = new FormData()
      eupdata.append("form", JSON.stringify(this.formLabelAlignUpdata))
      this.$axios.post("http://127.0.0.1:8080/updata", eupdata, {
        headers: {
          [TestDao.header]: this.token1
        }
      }).then(
        (success) => {
          alert("数据传输成功");
        },
        (error) => {
          alert("数据传输错误")
        }
      ).catch((error) => { console.log(error) })
      this.closeDialog();
    }
    ,
    showFormMy() {
      this.showFormMy1 = true;
    }
    ,
    setEyes() {
      this.visableReally = true;
    },
    setFind() {
      this.visible = true;
    }
    ,
    sumbitFind() {
      let f = new FormData();
      f.append("name", this.inputValue);
      this.$axios.post("http://127.0.0.1:8080/list", f, {
        headers: {
          [TestDao.header]: this.token1
        }
      }).then(
        (success) => {
          if (!success.data.corect) {
            alert("查无此人" + success.data.corect)
          } else {
            var words = JSON.stringify(success.data.data)//想要将data数据弹出，就要转换成为字符串的形式
            alert("请求成功" + words)
          }
        },
        (error) => {
           alert("情求失败")
        }
      ).catch((error) => {
         console.log(error) 
        })
      this.inputValue = '';
      this.closeDialog();
    }
    ,

    closeDialog() {
      this.visableReally = false;
      this.visible = false;
      this.showFormMy1 = false;
      setTimeout(function () {
        location.reload()
      }, 1000);
    },
    sumbit() {
      let f = new FormData();
      f.append("name", this.inputValue)
      this.$axios.post("http://127.0.0.1:8080/delete", f, {
        headers: {
          [TestDao.header]: this.token1
        }
      }).then(
        (success) => {
          alert("请求成功" + this.inputValue)
        },
        (error) => {
          alert("请求失败")
        }
      ).catch((error) => { console.log(error) })

    },
    updataTest: function () {
      this.updataReally = true;
    }
  },
  mounted: function () {
    this.getTable();
  },
  components: {//组件的注册
    'comment-box': comment
  },

}
</script>

<style>

.testSumbit{
   display: flex;
}
.el-header {
  background-color: #B3C0D1;
  color: #333;
  line-height: 60px;
}

.el-aside {
  color: #333;
}
</style>