<template>
  <div id="build">
    <div>
      <el-button type="primary" @click="get()">新增</el-button>
      <el-dialog :visible.sync="showDialog">
        <el-input v-model="inputValue"></el-input>
        <el-button type="button" @click="testSelect()" id="ha">sumbit</el-button>
      </el-dialog>
      <!-- 父子组件引入 -->
      <comment-box>
        <span slot="header"></span>
        <span slot="footer"></span>
      </comment-box>


      <el-button type="primary" @click="showFormMy()">test</el-button>
      <el-dialog :visible.sync="showFormMy1">
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
          <el-form-item label="活动日期">
            <el-input v-model="formLabelAlign.birthday"></el-input>
          </el-form-item>
          <el-form-item label="年龄">
            <el-input v-model="formLabelAlign.age"></el-input>
          </el-form-item>
          <el-form-item label="活动地点">
            <el-input v-model="formLabelAlign.school"></el-input>
          </el-form-item>
          <!-- <el-form-item label="头像"   enctype="multipart/form-data">
                  <input type="file" name="image"><br>
            </el-form-item> -->
        </el-form>
        <el-button type="button" @click="testForm()" id="ha">sumbit</el-button>
      </el-dialog>

      <form action="http://127.0.0.1:8080/upload" method="post" enctype="multipart/form-data">
        姓名:<input type="text" name="username"><br>
        年龄：<input type="text" name="age"><br>
        头像：<input type="file" name="image"><br>
        <!-- 不显示文件名 -->
        <input type="submit" value="提交">
      </form>

      <el-upload class="upload-demo" action="http://127.0.0.1:8080/controlFile" :on-change="handleChange"
        :file-list="fileList" :headers="headers">
        <el-button size="small" @click="confirmUpload()">点击上传</el-button>
        <div slot="tip" class="el-upload__tip">上传文件类型不限，但不超过200MB</div>
      </el-upload>

    </div>

    <div class="block">
      <el-date-picker v-model="value1" type="date" placeholder="选择日期" format="yyyy 年 MM 月 dd 日"
        value-format="yyyy-MM-dd" @change="testTime">
      </el-date-picker>
      <p>{{ this.value1 }}</p>
      <!-- format表示在表单中显示的格式
    value-format表示数据的基本格式 -->
      <!-- <button @click="testTime()">test</button> -->
    </div>

    <!-- 消费表单测试中 -->
    <el-button type="success" @click="testMethod()">成功按钮</el-button>
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
          <el-checkbox-group v-model="form.classify">
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


    <el-button plain @click="showUser">默认按钮</el-button>
    <el-dialog :visible.sync="showUserForm">
      <!-- 通过ref绑定ruleForm属性 -->
      <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="用戶名" prop="userName">
          <el-input v-model.number="ruleForm.userName"></el-input>
        </el-form-item>

        <el-form-item label="出生日期" >
          <el-col class="moneyform">
            <el-date-picker type="date" placeholder="选择日期" v-model="ruleForm.birthday"
              value-format="yyyy-MM-dd"></el-date-picker>
          </el-col>
        </el-form-item>

        <el-form-item label="年龄" prop="age">
          <el-input v-model.number="ruleForm.age"></el-input>
        </el-form-item>

        <el-form-item label="家庭地址" prop="address">
          <el-input v-model.number="ruleForm.address"></el-input>
        </el-form-item>

        <el-form-item label="密码" prop="pass">
          <el-input type="password" v-model="ruleForm.pass" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="checkPass">
          <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
          <el-button @click="resetForm('ruleForm')">重置</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

    <el-button @click.native="test12" type="primary">test12</el-button>

      <!-- 注册表达测试中 -->

  </div>
</template>

<script>
import comment from '../elementMy/TestNewMy.vue'
import TestDao from '../static/DaoTest.vue'
export default {
  components: {
    "comment-box": comment
  },
 
  data() {

    var checkAge = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('年龄不能为空'));
        }
        setTimeout(() => {
          if (!Number.isInteger(value)) {
            callback(new Error('请输入数字值'));
          } else {
            if (value < 18) {
              callback(new Error('必须年满18岁'));
            } else {
              callback();
            }
          }
        }, 1000);
      };
      var validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'));
        } else {
          if (this.ruleForm.checkPass !== '') {
            this.$refs.ruleForm.validateField('checkPass');
          }
          callback();
        }
      };
      var validatePass2 = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'));
        } else if (value !== this.ruleForm.pass) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      };
    return {
      sum:0,
      inputValue: '',
      showUserForm: false,
      showDialog: false,
      showFormMy1: false,
      showMoeny: false,
      token: '',
      tableData: [],
      token: '',
      value1: '',
      labelPosition: 'right',
      formLabelAlign: {
        name: '',
        birthday: '',
        age: '',
        school: ''
      },
      fileList: [],
      headers: {
        'Content_Type': 'multipart/form-data'
      },
      form: {
        cost: '',
        dayTime: '',
        classify: [],
      },
      ruleForm: {
        userName: '',
        birthday: '', 
        age:'',
        address:'',
        pass: '',
        checkPass: ''
      },
      rules: {
        pass: [
          { validator: validatePass, trigger: 'blur' }
        ],
        checkPass: [
          { validator: validatePass2, trigger: 'blur' }
        ],
        age: [
            { validator: checkAge, trigger: 'blur' }
          ]
      }
    }
  },
  watch:{
    sum(item1,item2)
    {
      alert(item1+" "+item2);
    }
  }
  ,
  methods: {
    test12:function(){
       this.sum++;
      console.log("test");
    },
    closeForm: function () {
      this.showMoeny = false;
    },
    testMethod: function () {
      this.showMoeny = true;
    },
    get: function () {
      this.showDialog = true;
    },
    showFormMy: function () {
      this.showFormMy1 = true;
    },
    showUser: function () {
      this.showUserForm = true;
    },
    resetForm(formName) {
      // 将传递过来的formName表单清空
      this.$refs[formName].resetFields();
    },
    //传递注册表单数据  向后端传递数据 第三种方式
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          let registerForm=new FormData();
          registerForm.append("registerForm",JSON.stringify(this.ruleForm));
          this.$axios.post("http://127.0.0.1:8080/register",{      
            age: this.ruleForm.age,
            birthday: this.ruleForm.birthday,
            name: this.ruleForm.userName,
            school: this.ruleForm.address,
            password: this.ruleForm.pass,
          },{
            headers:{
              [TestDao.header]:localStorage.getItem(TestDao.header)
            }
          }).then(
            (success)=>{
              alert(JSON.stringify(success.data))
            }
          ).catch(error)
          {
              alert("注册失败请联系后端人员")
          };
          alert('submit!');
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },


    submitMoney() {
      let form1 = new FormData();
      form1.append("form1", JSON.stringify(this.form));
      this.$axios.post("http://127.0.0.1:8080/user/moneyRember", form1, {
        headers: {
          [TestDao.header]: localStorage.getItem(TestDao.header)
        }
      }).
        then(
          (success) => {
            alert(this.form.dayTime);
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
    }
    ,
    handleChange(file, fileList) {
      this.fileList = fileList;
      //限制上传文件大小
      const isJPG = file.type === '*/*';//指定上传文件内容格式。*/*不限文件类型和文件格式
      const isLt2M = file.size / 1024 / 1024 <= 200;

      if (isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!');
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 200MB!');
      }
      // setTimeout(function () { location.reload() }, 2000)
      return isJPG && isLt2M;
    },
    testTime: function () {
      // let timeMy=new FormData();
      // timeMy.append("time",this.value1);
      alert(this.value1)
      let timeMy = new FormData();
      timeMy.append("value1", this.value1);
      this.$axios.post("http://127.0.0.1:8080/timeMy", timeMy, {
        headers: {
          [TestDao.header]: this.token
        }
      }).then(
        (success) => {
          alert("日期添加成功")
        },
        (error) => {
          alert("日期添加失败")
        }
      )
    },

    test: function () {
      this.token = localStorage.getItem(TestDao.header)
      let data = new FormData();
      data.append("name", this.inputValue);
      this.$axios.post("http://127.0.0.1:8080/delete", data, {
        headers: {
          [TestDao.header]: this.token
        }
      }).then(
        (success) => {
          alert("请求成功" + this.inputValue)
        },
        (error) => {
          alert("请求失败")
        }
      ).catch((error) => {
        console.log(error);
      })
    },

    confirmUpload() {
      this.token = localStorage.getItem(TestDao.header)
      let parm = new FormData();
      this.fileList.forEach((val, index) => {
        parm.append("file", val.raw);
        console.log(val.raw)
        alert(val.raw)
      });
      this.$axios.post("http://127.0.0.1:8080/controlFile",parm,{
        headers: {
          [TestDao.header]: this.token
        }
      }).then(
        (success) => {
          console.log("test success")
        },
        (erroe) => {
          console.log("test error")
        }
      )
    },


    testSelect: function () { //传递参数给后端
      let data1 = {
        age: '12',
        birthday: '2004-07-24',
        name: '影',
        school: '车市'
      }
      this.$axios.post("http://127.0.0.1:8080/selectSome", data1).then(
        (success) => {
          alert("请求成功" + age)
        },
        (error) => {
          alert("请求失败" + age)
        }
      ).catch((error) => {
        console.log(error);
      })
    },

    testForm: function () { //传递参数给后端2
      let formData = new FormData()
      formData.append("form", JSON.stringify(this.formLabelAlign))
      this.$axios.post("http://127.0.0.1:8080/insert", formData).then(
        result => {
          alert(result.data.age);
        }
      )
    },

  

    // beforeAvatarUpload(file) { 
    //   //限制上传文件大小
    //     const isJPG = file.type === '*/*';//指定上传文件内容格式。*/*不限文件类型和文件格式
    //     const isLt2M = file.size / 1024 / 1024 <= 200;

    //     if (isJPG) {
    //       this.$message.error('上传头像图片只能是 JPG 格式!');
    //     }
    //     if (!isLt2M) {
    //       this.$message.error('上传头像图片大小不能超过 200MB!');
    //     }
    //     return isJPG && isLt2M;
    //   }
  },
  mounted() {
    this.token = localStorage.getItem(TestDao.header)
  }
};
</script>
<style>
/* @import url("../static/1.css"); */
.d {
  display: flex;
  flex-wrap: wrap;
  flex-direction: column;
  align-items: flex-start;
}

.dright {
  align-self: flex-end;
}

.moneyform{
  display: flex;
  flex-wrap:wrap;
  flex-direction: column;
  align-items: flex-start;
}
</style>
