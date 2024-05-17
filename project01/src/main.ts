import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import axios from 'axios'
import * as echarts from 'echarts';

Vue.prototype.$echarts = echarts


Vue.config.productionTip = false


Vue.config.errorHandler=(err,vm,info)=>{
  // if(err instanceof Error){
  //   console.error("异常触发",err.message);
  //   console.error("异常信息",info);
  //   localStorage.setItem("token",'');
  // }
  // if(err instanceof MyCustomError){MyCustomError为自定义异常类
  //   console.error("自定义异常触发",err.message);
  //   console.error("自定义异常信息",info);
  //   localStorage.setItem("token",'');
  // }
}

Vue.use(ElementUI);
axios.defaults.baseURL = 'http://10.11.225.116:7000/'
Vue.prototype.$axios = axios
new Vue({
  router,
  render: h => h(App)
}).$mount('#app')



