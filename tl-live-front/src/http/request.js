import axios from 'axios'

const service = axios.create({
    //基础路径
//   baseURL: process.env.VUE_APP_BASE_API,
  timeout: 5000
})

function getCookie(name) {
  // 将document.cookie字符串按照; 分割成数组
  let cookies = document.cookie.split(';');
  // 修剪字符串两侧的空格
  name = name.trim();
  // 遍历cookie数组
  for(let i = 0; i < cookies.length; i++) {
    let cookie = cookies[i].split('=');
    // 修剪cookie名称的两侧空格
    let cookieName = cookie[0].trim();
    if(cookieName === name) {
      // 返回cookie值
      return cookie[1];
    }
  }
  // 如果没有找到，返回null
  return null;
}


// 请求拦截器
service.interceptors.request.use(
  config => {
    // 添加请求头等前置处理
    // config.headers['Authorization'] = 'Bearer' + ' ' + localStorage.getItem('token')
    // 添加请求头。这个cookie会在登录完成后返回。对应Redis中记录的用户信息。
    config.headers['token'] = getCookie('tltk');
    return config
  },
  error => {
    // 请求错误处理
    console.log('Request Error:', error)
    return Promise.reject(error)
  }
)

// 响应拦截器
service.interceptors.response.use(
  response => {
    // 响应后处理 服务处理正常，并且API服务返回的状态码也是200
    if(response.status === 200 && response.data){
        if(response.data.code && response.data.code ===200){
            return Promise.resolve(response.data.data)
        }else{
            console.error('服务处理错误，返回消息为：',response.data.data)
            // return Promise.reject(response.data)
            return {'error':response.data.data}
        }
    }else{
        console.error('请求错误，状态码为：',response.status)
        // return Promise.reject(response.data)
        return {'error':response.data.data}
    }
  },
  error => {
    console.log('Response Error:', error)
    return Promise.reject(error)
  }
)

export default service
