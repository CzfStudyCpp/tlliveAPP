<template>
  <el-header class="douyin-header">
    <div class="douyin-logo">
      <el-menu :ellipsis="false" mode="horizontal">
        <el-menu-item index="1" @click="goto('download')">下载客户端</el-menu-item> <!-- router指定路由，@click点击事件 -->
        <el-menu-item index="2" @click="goto('recharge')">充值金币</el-menu-item>
        <el-menu-item index="3" @click="goto('notify')"><el-badge :value="12">消息通知</el-badge></el-menu-item>
      </el-menu>
    </div>
    <div class="douyin-nav">
      <div class="user-info" v-if="isLoggedIn">
        <el-avatar :src="userAvatar" :size="40" class="user-avatar" alt="用户头像"></el-avatar>
        <span class="username">{{ userName }}</span>
        <el-button type="info" class="logout-button" @click="handleLogout">退出登录</el-button>
      </div>
      <el-button type="danger" class="login-button" @click="handleLogin" v-if="!isLoggedIn">登录</el-button>
    </div>
    <div class="douyin-search">
      <el-input placeholder="搜索" class="search-input"></el-input>
    </div>
  </el-header>
</template>

<script setup>
import { ref } from 'vue';
import {queryUserById } from '@/http/api'
import { userLoginStore } from '@/store/userLoginStore'
let userStore = userLoginStore()

import {useRouter} from 'vue-router';
let router = useRouter()

const isLoggedIn = ref(false); // 假设这是从某处（如Vuex或API）获取的登录状态  
let userName = ref('用户名'); // 用户名  
let userAvatar = ref('/img/avatar.png'); // 用户头像路径  

// 处理登录逻辑（这里只是模拟，实际中可能是导航到登录页或弹出登录框）  
const handleLogin = () => {
  // 添加实际的登录逻辑  
  alert('跳转到登录页面');

  router.push("/login")
};

// 处理退出登录逻辑  
const handleLogout = () => {
  isLoggedIn.value = false;
  // userName.value = '';  
  // userAvatar.value = '';  
  // 这里可以添加实际的退出登录逻辑，比如清除本地存储的用户信息等  
  userStore.storeUser('','','')
  router.push("/home")
};
const goto = (target) => {
  console.log("跳转到", target)
}
const getUserInfo = async () => {
  //获取登录时存入Pinia的当前登录用户ID。
  let currentUserId = userStore.getLoginUserId; 
  if (currentUserId) {
    let userInfo = await queryUserById({'userId':currentUserId})
    if(userInfo.error){
      console.error(error)
    }else{
      isLoggedIn.value = true;
      userName.value = userInfo.nickName
      userAvatar.value = userInfo.avatar
      userStore.storeUser(currentUserId,userInfo.nickName,userInfo.avatar)
    }
  } else {
    isLoggedIn.value = false;
  }
}
//进页面时获取用户信息
getUserInfo();
</script>

<style scoped>
.douyin-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 20px;
  height: 60px;
  /* 根据需要调整头部高度 */
  /* background-color: #262626; 抖音背景色，根据需要调整   */
  /* color: #ffffff; 文本颜色，根据需要调整   */
}

.douyin-logo {
  /* Logo样式 */
  width: 60px;
  /* Logo宽度 */
  height: 60px;
  /* Logo高度 */
}

.douyin-nav {
  /* 导航菜单样式 */
  display: flex;
  align-items: center;
}

.user-info {
  /* 用户信息样式 */
  display: flex;
  align-items: center;
}

.user-avatar {
  /* 用户头像样式 */
  width: 30px;
  height: 30px;
  margin-right: 10px;
}

.username {
  /* 用户名样式 */
  font-weight: bold;
}

.logout-button {
  /* 退出登录按钮样式 */
  margin-left: 8px;
  padding: 4px 8px;
  font-size: 12px;
}

.login-button {
  /* 登录按钮样式 */
  padding: 4px 8px;
  font-size: 12px;
}

.douyin-search {
  /* 搜索框样式 */
  margin-left: 20px;
}

.search-input {
  /* 输入框样式 */
  width: 200px;
  /* 根据需要调整搜索框宽度 */
}
</style>