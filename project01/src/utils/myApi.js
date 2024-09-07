import request from '@/utils/request.js'
// import TestDao from '@/static/DaoTest.vue'
import TestDao from '../static/DaoTest.vue'
import { helper } from 'echarts';
import DaoTest from '../static/DaoTest.vue';

export function getPicCode (dayNow,jwt) { //从后端获取一周的消费数据
    return request.get(`http://127.0.0.1:8081/show/selectWeek/${dayNow}`,{
        headers:{
            [TestDao.header]: jwt
        }
    });
}

export function getMoneyDay(jwt){
    return request.get(`http://127.0.0.1:8081/show/selectDay`,{
        headers:{
            [TestDao.header]:jwt
        }
    });
}

export function getCostAll(jwt){
    return request.get(`http://127.0.0.1:8081/user/CostAll`,{
        headers:{
            [DaoTest.header]:jwt
        }
    });
}

export function getInputAll(jwt){
     return request.get(`http://127.0.0.1:8081/show/selectInput`,{
         headers:{
            [DaoTest.header]:jwt
         }
     })
}

export function getLineData(jwt){
    return request.get(`http://127.0.0.1:8081/show/selectLineOut`,{
        headers:{
            [DaoTest.header]:jwt
        }
    })
}