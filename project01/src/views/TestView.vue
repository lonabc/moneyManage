<template>
  <div >
    <el-container style="height: 500px; border: 1px solid #eee ;">
      <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
        <el-menu :default-openeds="['1', '3']" background-color="#545c64" text-color="#fff" active-text-color="#ffd04b">
          <el-submenu index="1">
            <template slot="title"><i class="el-icon-message"></i>管理员页面</template>
            <el-menu-item-group>
              <el-menu-item index="1-1" @click="changeshow3()">用户信息</el-menu-item>
            </el-menu-item-group>
          </el-submenu>
          <el-submenu index="2">
            <template slot="title"><i class="el-icon-menu"></i>用户页面</template>
            <el-menu-item-group>
              <el-menu-item index="2-1" @click="changeshow2()">个人消费情况</el-menu-item>
              <el-menu-item index="2-2">消费统计图(未完成)</el-menu-item>
            </el-menu-item-group>

            <el-menu-item-group>
              <el-menu-item index="2-3" @click="showOutloginChange()">退出登录</el-menu-item>
            </el-menu-item-group>

            <el-menu-item-group>
              <el-menu-item index="2-4" @click="changePage()">车市</el-menu-item>
            </el-menu-item-group>
          </el-submenu>
        </el-menu>


      </el-aside>


      <el-dialog :visible.sync="showOutlogin">
        <el-row class="midsignal">
          <el-col :sm="12" :lg="6">
            <el-result icon="warning" title="警告提示" subTitle="请根据提示进行操作">
              <template slot="extra">
                <div style="display: flex; justify-content: space-around; ">
                  <el-button type="warning" size="medium" @click="loginOut">确定</el-button>
                  <el-button type="primary" size="medium" @click="showOutloginChange">返回</el-button>
                </div>
              </template>
            </el-result>
          </el-col>
        </el-row>
      </el-dialog>


      <el-container>
        <router-view></router-view>
        <el-header style="text-align: right; font-size: 12px">
          <el-dropdown>
            <i class="el-icon-setting" style="margin-right: 15px"></i>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item @click.native="setFind()">查找</el-dropdown-item>

              <el-dialog :visible.sync="visible" :append-to-body="true">
                <el-input v-model="inputValue" placeholder="请输入需要查找的人员姓名" id="yinFirst"
                  @keyup.enter.native="handleEnter">
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
                      <el-date-picker type="date" placeholder="选择日期" value-format="yyyy-MM-dd"
                        v-model="formLabelAlign.birthday" style="width: 100%;"></el-date-picker>
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
                      <el-date-picker type="date" placeholder="选择日期" value-format="yyyy-MM-dd"
                        v-model="formLabelAlignUpdata.birthday" style="width: 100%;"></el-date-picker>
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
              <el-dropdown-item @click.native="setEyes(elRow.name)">删除</el-dropdown-item>
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

        <transition name="el-zoom-in-center">
          <el-main v-show="show3" v-if="refresh">
            <el-table :data="tableData">
              <el-table-column prop="age" label="年龄" width="140">
              </el-table-column>
              <el-table-column prop="birthday" label="日期" width="140">
              </el-table-column>
              <el-table-column prop="name" label="姓名" width="120">
              </el-table-column>
              <el-table-column prop="school" label="地址" width="120">
              </el-table-column>
            </el-table>
          </el-main>

        </transition>

        <transition name="el-zoom-in-center">
          <el-main v-show="show2" class="rightI" v-if="refresh1">
            <div>
              <MoneyMan></MoneyMan>
            </div>
            <div>
              <DateBase1></DateBase1>
            </div>

          </el-main>
        </transition>

        <!-- <transition name="el-zoom-in-center">
        <el-main v-show="show1">
          <DateBase1></DateBase1>
        </el-main>
      </transition> -->


        <transition name="el-zoom-in-top">
          <el-footer v-show="show3">
            <div class="block">
              <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
                :current-page.sync="currentPage3" :page-size="page_size" layout="total, prev, pager, next, jumper"
                :total="sumpage">
              </el-pagination>
            </div>
          </el-footer>
        </transition>
        <!-- 
        <transition>
          <el-main v-show="show1">
            <CountMoney>

            </CountMoney>
           </el-main>
        </transition> -->

        <!-- <el-dialog :visible.sync="show1">
          <div v-if="this.refresh2">
            <CountMoney ref="CompTable" :key="new Date().getTime()">

            </CountMoney>
          </div>
        </el-dialog> -->

        <el-dialog :visible.sync="show_user">
          <el-descriptions title="用户信息">
            <el-descriptions-item label="用户名">{{ formLabelAlignFind.name }}</el-descriptions-item>
            <el-descriptions-item label="居住地">{{ formLabelAlignFind.school }}</el-descriptions-item>
            <el-descriptions-item label="年龄">
              <el-tag size="small">{{ formLabelAlignFind.age }}</el-tag>
            </el-descriptions-item>
            <el-descriptions-item label="出生日期">{{ formLabelAlignFind.birthday }}</el-descriptions-item>
          </el-descriptions>

        </el-dialog>



      </el-container>
    </el-container>

  </div>
</template>



<script>

import comment from '../elementMy/TestYin.vue'
import TestDao from '../static/DaoTest.vue'
import MoneyMan from '../elementMy/ManageMoney.vue'
import DateBase1 from '../elementMy/DateBase1.vue'


// import CountMoney from '../elementMy/CountMoney.vue'

export default {

  data() {
    return {
      inputValue: '',
      sum: 0,
      show_user: false,
      tableData: [],
      visible: false,
      refresh: true,
      refresh1: true,
      refresh2: true,
      showOutlogin: false,
      visableReally: false,
      showFormMy1: false,
      updataReally: false,
      token1: '',
      show3: true,
      show2: false,
      show1: false,
      currentPage3: 1,
      labelPosition: 'right',
      sumpage: 21,
      page_size: 7,
      page_start: 0,
      formLabelAlign: {
        name: '',
        birthday: '',
        age: '',
        school: ''
      },
      formLabelAlignFind: {
        name: 'ddd',
        birthday: '',
        age: '',
        school: ''
      },
      formLabelAlignUpdata: {
        name: '',
        birthday: '',
        age: '',
        school: ''
      },
      formPage: {
        pageStart: 1,
        pageSize: 7
      }
    }
  },
  watch: {
    sum(item1, item2) {
      this.getPage();
    }
  },
  methods: {
    changePage() {
      this.$router.push('/show')
    },
    showUser() {
      this.show_user = !this.show_user;
    },
    testshow1() {
      this.show1 = !this.show1;
    },

    changeshow2() {
      this.show1 = false;
      this.show3 = false;
      this.show2 = !this.show2;
    },
    changeshow3() {
      this.show1 = false;
      this.show2 = false;
      this.show3 = !this.show3;

    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.formPage.pageStart = val;
      this.getPage();
    },

    loginOut() {
      localStorage.setItem(TestDao.header, "");
      TestDao.token = "";
      this.token1 = "";
      this.showOutloginChange();
      this.$store.commit('testLoginStatus', false)
      location.href = "http://localhost:8000/#/login";
    },
    getPage() {
      TestDao.token = localStorage.getItem(TestDao.header);
      this.token1 = TestDao.token;
      let pagemy = new FormData();
      pagemy.append("page", JSON.stringify(this.formPage));
      this.$axios.post("http://127.0.0.1:8080/pageHelperA", pagemy, {
        headers: {
          [TestDao.header]: this.token1
        }
      }).then(
        (result) => {

          this.tableData = result.data.data;
          this.sumpage = result.data.testData;

        }
      ).catch(error)
      {
        alert("传输失败");
      }
    }
    ,
    handleEnter() {
      this.sumbitFind();
    }
    ,
    testForm() {//向后端传递参数方法二 
      let formData = new FormData()
      formData.append("form", JSON.stringify(this.formLabelAlign))
      alert(JSON.stringify(this.formLabelAlign))
      this.$axios.post("http://127.0.0.1:8080/insert", formData, {
        headers: {
          [TestDao.header]: this.token1
        }
      }).then(
        (success) => {
          //alert(result.data.age);
          this.refresh = false;
          this.sum++;
          this.refresh = true;

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
          this.sum++;
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
            // var words = JSON.stringify(success.data.data)//想要将data数据弹出，就要转换成为字符串的形式
            this.formLabelAlignFind = success.data.data//想要将data数据弹出，就要转换成为字符串的形式
            this.showUser();

          }
        },
        (error) => {
          alert("情求失败")
        }
      ).catch((error) => {
        console.log(error)
      })
      this.inputValue = '';
      this.visible = false;
    }
    ,

    closeDialog() {
      this.visableReally = false;
      this.showFormMy1 = false;
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
          this.refresh = false;
          this.$nextTick(() => {
            this.refresh = true;
          })
        },
        (error) => {
          alert("请求失败")
        }
      ).catch((error) => { console.log(error) })

    },
    updataTest: function () {
      this.updataReally = true;
    },
    showOutloginChange: function () {
      this.showOutlogin = !this.showOutlogin
    },


    gettotal: function () {
      this.$axios.get("http://127.0.0.1:8080/gettotal", {
        headers: {
          [TestDao.header]: this.token1
        }
      }).then(
        (success) => {
          this.sumpage = success.data
        }
      ).catch(error)
      {
        console.log("获取数据失败")
      }

    }
  },
  mounted: function () {
  
    this.getPage();
  },
  // updated: function () {
  // //  this.gettotal();

  // },
  components: {//组件的注册
    'comment-box': comment,
    'MoneyMan': MoneyMan,
    'DateBase1': DateBase1,
  }
}
</script>

<style>
.rightI {
  display: flex;
  flex-direction: row;
  justify-content: space-around;
}


.testSumbit {
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

.demo2-bg {
  background: url(https://121138hmleadnews.oss-cn-beijing.aliyuncs.com/test/5754118f-1689-46f7-9927-1c3157f52e6d.png);
  max-width: 100%;
  max-height: 100%;
}

img {
  max-width: 100%;
  max-height: 100%;
}

.midsignal {
  display: flex;
  justify-content: center;
}
</style>