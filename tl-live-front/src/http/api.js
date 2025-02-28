import request from './request'
import qs from 'qs'

//发送短信验证码
export function sendSMS(data){
    //将参数转成字符串，SpringBoot后端可以直接接受参数
    return request.post('/user/sendSMS',qs.stringify(data))
}
//根据ID查询用户信息
export function queryUserById(data){
    //将参数转成字符串，SpringBoot后端可以直接接受参数
    return request.post('/user/queryUser',qs.stringify(data))
}
//用户登录请求
export function mobileLogin(data){
    //这种传参，SpringBoot后端要用@RequestBody接受参数
    return  request({
        url:'/user/mobileLogin',
        method:'post',
        data
    })
}
//获取IM服务器地址
export function getIMServerUrl(userId){
    return request.post('/im/getIMServer',qs.stringify({'userId':userId}))
}
