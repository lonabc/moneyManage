<template>
 <div >
    <el-radio-group v-model="labelPosition" size="small">
        <el-radio-button label="left">左对齐</el-radio-button>
        <el-radio-button label="right">右对齐</el-radio-button>
        <el-radio-button label="top">顶部对齐</el-radio-button>
    </el-radio-group>
    <div style="margin: 20px;"></div>
    <div class="chl_box">
        <el-form :label-position="labelPosition" label-width="80px" :model="formLabelAlign" >
        <el-form-item label="名称">
            <el-input v-model="formLabelAlign.name"></el-input>
        </el-form-item>
        <el-form-item label="密码" >
            <el-input v-model="formLabelAlign.password"></el-input>
        </el-form-item>
        <el-button style="text-align:center" @click="loginMy()">登录</el-button>
        </el-form>
    </div> 
 </div>
</template>

<script>
import TestDao from '../static/DaoTest.vue'

    export default {
    data() {
      return {
        labelPosition: 'right',
        formLabelAlign: {
          name: '',
          password: ''
        },
        
      };
    },
    methods:{
      loginMy:function()//测试中此处增加了async
      {
        let formMy=new FormData()
        var jwtMy
        var headers=TestDao.header
        formMy.append("loginForm",JSON.stringify(this.formLabelAlign))
        this.$axios.post("http://127.0.0.1:8080/login",formMy).then(
          (success)=>{
              jwtMy=success.data;

             alert(JSON.stringify(jwtMy));
              TestDao.token=jwtMy;
              localStorage.setItem('token',TestDao.token);
           //   document.cookie=`auth_token=${token};Secure; HttpOnly; SameSite=Strict`;//注意使用反引号``
           DaoLogin.login=true;
          },
          (error)=>{
               window.alert("登录失败")
               throw new MyCustomError("登录失败")
          }
        ).catch((error)=>{
           console.log(error);
        })
      }
    }
  }
</script>

<style>
 .testSize{
    width: 200px
 }
 
.chl_box {
	width: 600px;
	height: 600px;
	position: absolute;
	top: 20px;
	left: 0;
	right: 0;
	bottom: 0;
	margin: auto;
	/* background: #00FFFF; */
}
</style>
<meta name="viewport" content="width=device-width, initial-scale=1">