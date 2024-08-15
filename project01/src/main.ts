import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui'
import PieChart from './elementMy/PieChart.vue' //引入饼状图组件
import Fence from './components/Fence.vue'
import 'element-ui/lib/theme-chalk/index.css'
import axios from 'axios'
import * as echarts from 'echarts';
import VueAnimateNumber from 'vue-animate-number'
import store from './store/index';
import moment from 'moment';
import LineChart from './elementMy/LineChart.vue'
import VueParticles from 'vue-particles'  

import VWave from 'v-wave'
import lottie from 'vue-lottie'
Vue.component('lottie',lottie)

Vue.use(VWave)
Vue.use(VueParticles) 

Vue.use(VueAnimateNumber)
Vue.prototype.$echarts = echarts
Vue.prototype.$moment = moment
Vue.component('PieChart',PieChart); //注册全局组件
Vue.component('Fence',Fence);
Vue.component('LineChart',LineChart)

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
  store,
  render: h => h(App)
}).$mount('#app')


//自定义指令
Vue.directive('showNum',{
  inserted(el,binding){
      let finalNum=Number.parseInt(el.innerText);
      let count=0;
      let time=2000;
      let rate =30;
      let step= finalNum/(time/rate);
      let timer=setInterval(()=>{
        count=count+step;
        el.innerText=String(count.toFixed(2));//设置要保留的小数位
        if(count> finalNum)
        {
           count=finalNum;
           el.innerText=String(count.toFixed(2));
           //清空定时器
           clearInterval(timer);
        }

      },rate)
  }
})
