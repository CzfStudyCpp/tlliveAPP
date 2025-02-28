<template>
  <div class="login-container">
    <div class="background-overlay"></div>
    <div class="login-card">
      <h1>打开你的精彩世界</h1>
        <div class="form-group">
          <el-input type="tel" maxlength="14" id="usermobile" v-model="mobile" placeholder="请输入手机号" clearable
            required />
        </div>
        <div class="form-group">
          <el-input type="number" maxlength="6" id="code" v-model="code" placeholder="请输入短信验证码" required
            style="width:60%" />
          <el-button class="login_sms_code" type="primary" round @click="sendSmsCode()" style="width: 40%;">{{ loginCodeBtn }}</el-button>
        </div>
        <button type="submit" class="login-btn" @click="handleLogin">登录</button>
        <button class="wechat-login-btn" @click="showWechatLoginModal = true">微信登录</button>
      <div class="wechat-login-modal" v-if="showWechatLoginModal">
        <div class="modal-content">
          <h2>微信登录</h2>
          <p>扫描二维码登录</p>
          <img src="/img/qrcode.png" style="display: block;" alt="微信登录二维码" />
          <button class="wechat-confirm-btn" @click="handleWechatLogin">确认登录</button>
          <button class="wechat-cancel-btn" @click="showWechatLoginModal = false">取消</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { ElMessage } from 'element-plus'
import { sendSMS,mobileLogin } from '@/http/api.js'
import { useRouter } from 'vue-router'
import { userLoginStore } from '@/store/userLoginStore'

let router = useRouter()
let userStore = userLoginStore()

let mobile = ref('');
let code = ref('');
let loginCodeBtn = ref('获取验证码')
let showWechatLoginModal = ref(false);

let hasSendSms = false;
let lastTime = 60;

async function handleLogin() {
  // 登录逻辑处理，这里仅作演示  
  console.log('尝试登录', mobile.value, code.value);
  if(!checkPhone() || !code.value){
    ElMessage({
      message: '请输入正确参数',
      type: 'error'
    })
    return false;
  }
  if(!hasSendSms){
    ElMessage({
      message: '请先获取验证码',
      type: 'error'
    })
    return false;
  }

  const res = await mobileLogin({mobile:mobile.value,code:code.value})
  if(res.error){
    ElMessage({
      message: res.error,
      type: 'error'
    })
  }else{
    //登录状态存入Pinia，供其他页面使用。
    userStore.storeUserId(res.userId);
    //跳转到首页。
    router.push("/home")
  }
}
function handleWechatLogin() {
  // 这里应该是调用微信登录的API，然后处理登录结果  
  // 假设登录成功，我们关闭弹出框  
  showWechatLoginModal.value = false;
  console.log('微信登录成功');
  // 你可以在这里添加登录成功后的逻辑，比如跳转到首页  
}
async function sendSmsCode() {
  if (!checkPhone()) {
    ElMessage({
      message: '请输入手机号',
      type: 'warning'
    })
    return;
  }
  //已发送过消息，就返回
  if (hasSendSms) {
    ElMessage({
      message: '不要重复申请验证码',
      type: 'warning'
    })
    return;
  }
  // console.info(mobile.value)
  //请求后端，发送验证码
  const res = await sendSMS({mobile:mobile.value})
  // const res = await request.post('/user/sendSMS',qs.stringify({mobile:mobile.value}))
  // console.info('发送短信验证码', res)
  if (res) {
    //页面提示
    ElMessage({
      message: '短信验证码发送中',
      type: 'success'
    })
    //按钮失效
    hasSendSms = true
    //显示倒计时
    var interval = setInterval(() => {
      loginCodeBtn.value = '发送中(' + lastTime + ')'
      if (lastTime == 0) {
        lastTime = 60;
        loginCodeBtn.value = '获取验证码'
        hasSendSms = false
console.log('清理定时器')
        clearInterval(interval);
        return;
      } else {
        lastTime = lastTime - 1
      }
    }, 1000)
  }else {
    ElMessage({
      message: '申请验证码失败',
      type: 'error'
    })
    return;
  }
}

function checkPhone() {
  let phoneReg = /(^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\d{8}$)/;
  if (!mobile.value || !phoneReg.test(mobile.value)) {
    return false;
  }
  return true;
}
</script>

<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  position: relative;
  overflow: hidden;
}

.background-overlay {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: linear-gradient(45deg, #ff9a9e, #fad0c4, #84fab0, #8fd3f4, #9a84e8);
  background-size: 400% 400%;
  animation: gradient 15s ease infinite;
  z-index: -1;
}

@keyframes gradient {
  0% {
    background-position: 0% 50%;
  }

  50% {
    background-position: 100% 50%;
  }

  100% {
    background-position: 0% 50%;
  }
}

.login-card {
  background-color: rgba(255, 255, 255, 0.9);
  border-radius: 10px;
  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.1);
  padding: 30px;
  text-align: center;
  position: relative;
  z-index: 1;
  width: 40vh;
}

h1 {
  color: #333;
  margin-bottom: 20px;
}

.form-group {
  margin-bottom: 20px;
}

label {
  display: block;
  color: #666;
  margin-bottom: 5px;
}

input[type="text"],
input[type="password"] {
  width: 100%;
  padding: 10px;
  border: none;
  border-bottom: 1px solid #ccc;
  outline: none;
  transition: border-color 0.3s ease;
}

input[type="text"]:focus,
input[type="password"]:focus {
  border-bottom-color: #4CAF50;
}

.login-btn {
  padding: 10px 20px;
  background-color: #4CAF50;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
  margin-right: 10vh;
}

.login-btn:hover {
  background-color: #45a049;
}

.wechat-login-btn {
  margin-top: 20px;
  padding: 10px 20px;
  background-color: #7BB32E;
  /* 微信绿色 */
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.wechat-login-btn:hover {
  background-color: #699F25;
  /* 更深的微信绿色 */
}

.wechat-login-modal {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 2;
  /* 确保在登录卡片的上方 */
}

.modal-content {
  background-color: #fff;
  padding: 20px;
  border-radius: 10px;
  position: relative;
}

.modal-content img {
  max-width: 200px;
  margin-bottom: 10px;
}

.wechat-confirm-btn,
.wechat-cancel-btn {
  padding: 10px 20px;
  margin-right: 10px;
  background-color: #7BB32E;
  /* 微信绿色 */
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.wechat-cancel-btn {
  background-color: #ccc;
  /* 灰色 */
  margin-right: 0;
}

.wechat-confirm-btn:hover,
.wechat-cancel-btn:hover {
  background-color: #999;
  /* 深灰色 */
}

.close-modal {
  position: absolute;
  top: 10px;
  right: 10px;
  font-size: 20px;
  font-weight: bold;
  cursor: pointer;
}
</style>@/store/userLoginStore