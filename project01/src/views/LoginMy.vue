<template>
  <div>
    <el-radio-group v-model="labelPosition" size="small">
      <el-radio-button label="left">左对齐</el-radio-button>
      <el-radio-button label="right">右对齐</el-radio-button>
      <el-radio-button label="top">顶部对齐</el-radio-button>
    </el-radio-group>
    <div style="margin: 20px;"></div>
    <div class="chl_box">
      <el-form :label-position="labelPosition" label-width="80px" :model="formLabelAlign">
        <el-form-item label="名称" style="width: 100%">
          <el-input v-model="formLabelAlign.name"></el-input>
        </el-form-item>
        <div class="leftandright">
          <el-form-item label="密码" style="width: 80%">
            <!-- <el-input v-model="formLabelAlign.password"></el-input> -->
            <el-input placeholder="请输入密码" v-model="formLabelAlign.password" show-password></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="showEmailimp()">邮箱登录</el-button>
          </el-form-item>
        </div>
        <el-button style="text-align:center" @click="loginMy()">登录</el-button>
        <el-button @click="showUser" type="primary" plain>注册</el-button>
      </el-form>
    </div>

    <el-dialog :visible.sync="showUserForm" >
      <!-- 通过ref绑定ruleForm属性 -->
      <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="用戶名" prop="userName">
          <el-input v-model.number="ruleForm.userName"></el-input>
        </el-form-item>

        <el-form-item label="出生日期">
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

    <el-dialog :visible.sync="showEmail">
      <el-form :model="dynamicValidateForm" ref="dynamicValidateForm" label-width="100px" class="demo-dynamic">
        <el-form-item prop="email" label="邮箱" :rules="[
          { required: true, message: '请输入邮箱地址', trigger: 'blur' },
          { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }
        ]">
          <el-input v-model="dynamicValidateForm.email"></el-input>
        </el-form-item>
        <div class="leftandright">
          <el-form-item  label="验证码" style="width: 80%;">
              <el-input v-model="dynamicValidateForm.code"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button @click="testRester();getCode()">获取验证码</el-button>
          </el-form-item>
        </div>
        <el-form-item >
          <el-statistic :value="deadline2" time-indices title="꒰ᐢ⸝⸝•༝•⸝⸝ᐢ꒱ ​​验证码已发送" v-if="refresh"  ref="statistic">
              <template slot="suffix">
                距离验证码到期还剩
              </template>
          </el-statistic>
        </el-form-item label="邮箱验证码">
        <el-form-item>
          <el-button type="primary" @click="clickFn(); sumbitEmail()" >登录</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>


  </div>
</template>

<script>
import TestDao from '../static/DaoTest.vue'

export default {
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
      stop: true,
      deadline2: Date.now() + 1000 * 60,
      refresh:false,
      dynamicValidateForm: {
        email: '',
        code: ''
      },
      labelPosition: 'right',
      showUserForm: false,
      showEmail: false,
      formLabelAlign: {
        name: '',
        password: ''
      },
      ruleForm: {
        userName: '',
        birthday: '',
        age: '',
        address: '',
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
    };
  },
  methods: {
    testRester()
    {
       this.refresh=false;
       this.deadline2= Date.now() + 1000 * 60;
       this.$nextTick(()=>
      {
        this.refresh=true;
      })
      
    },
    hilarity() {
      this.$notify({
        title: "提示",
        message: "时间已到",
        duration: 0,
      });
    },
    clickFn() {
      this.$refs.statistic.suspend(this.stop);
      this.stop = !this.stop;
      this.showEmail=false;
    },
    showEmailimp: function () {
      this.showEmail = true;
    },
    showUser: function () {
      this.showUserForm = true;
    },
    loginMy: function ()//测试中此处增加了async
    {
      let formMy = new FormData()
      var jwtMy
      var headers = TestDao.header
      formMy.append("loginForm", JSON.stringify(this.formLabelAlign))
      this.$axios.post("http://127.0.0.1:8080/loginV2", formMy).then(
        (success) => {
          jwtMy = success.data;
          TestDao.token = jwtMy;
          localStorage.setItem('token', jwtMy);
          window.location.replace("http://127.0.0.1:8000/#/emp") //页面重定向
          //   document.cookie=`auth_token=${token};Secure; HttpOnly; SameSite=Strict`;//注意使用反引号``
          DaoLogin.login = true;

        },
        (error) => {
          window.alert("登录失败")
          throw new MyCustomError("登录失败")
        }
      ).catch((error) => {
        console.log(error);
      })
    },
    resetForm(formName) {
      // 将传递过来的formName表单清空
      this.$refs[formName].resetFields();
    },
    //传递注册表单数据  向后端传递数据 第三种方式
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          let registerForm = new FormData();
          registerForm.append("registerForm", JSON.stringify(this.ruleForm));
          this.$axios.post("http://127.0.0.1:8080/register", {
            age: this.ruleForm.age,
            birthday: this.ruleForm.birthday,
            name: this.ruleForm.userName,
            school: this.ruleForm.address,
            password: this.ruleForm.pass,
          }, {
            headers: {
              [TestDao.header]: localStorage.getItem(TestDao.header)
            }
          }).then(
            (success) => {
              alert("注册成功，请重新登录");
              this.showUserForm = false;
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
    sumbitEmail: function () {
      let formdata = new FormData();
      formdata.append("loginEmail",JSON.stringify(this.dynamicValidateForm));

      this.$axios.post("http://127.0.0.1:8080/loginCode",formdata).then(
        (success)=>{
          let jwtMy=success.data;
          TestDao.token=jwtMy;
          localStorage.setItem('token',TestDao.token)
          console.log(JSON.stringify(jwtMy))
        },
        (error)=>{
          alert("登录失败")
        }
      ).catch((error)=>{
        console.log(error);
      })
    },
  
    getCode:function()
    {
  
       const emails= this.dynamicValidateForm.email
       this.$axios.get(`http://127.0.0.1:8080/testCode/${emails}`).then(
        (success)=>{
          alert(this.dynamicValidateForm.email);
        },
        (error)=>{
          alert(this.dynamicValidateForm.email);
          alert("验证失败")
        }
       ).catch(
        (error)=>{
          console.log(error)
        }
       )
    }
  }
}
</script>

<style>
.testSize {
  width: 200px
}

.chl_box {
  width: 800px;
  height: 600px;
  position: absolute;
  top: 20px;
  left: 0;
  right: 0;
  bottom: 0;
  margin: auto;
  /* background: #00FFFF; */
}

.moneyform {
  display: flex;
  flex-wrap: wrap;
  flex-direction: column;
  align-items: flex-start;
}

.leftandright {
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  flex-wrap: nowrap;
}
</style>
<meta name="viewport" content="width=device-width, initial-scale=1">