//此处用来show数据的函数方法
import request from '@/utils/request'

export const getShow=()=>{
    return request.get('/test/shhow')
}