<template>
    <el-card shadow="always">
        <el-image style="width: 100px; height: 100px; cursor: pointer;" :src="logopng" fit="none" @click="gotoHome" />
    </el-card>
    <el-menu :default-active="activeIndex" class="menu-container">

        <el-menu-item class="menu-wrapper" index="1">
            <RouterLink class="menu-item" to="/home">首页</RouterLink>
        </el-menu-item>

        <el-menu-item class="menu-wrapper" index="2">
            <RouterLink class="menu-item" to="/roomlist">直播</RouterLink>
        </el-menu-item>
        <el-menu-item class="menu-wrapper" index="3">
            <RouterLink class="menu-item" to="/movielist">放映厅</RouterLink>
        </el-menu-item>
        <el-menu-item class="menu-wrapper" index="4">
            <RouterLink class="menu-item" to="/about">关于</RouterLink>
        </el-menu-item>
    </el-menu>
</template>
<script setup>
import { ref } from 'vue'
import {useRouter} from 'vue-router'
import img_logo_png from '@/asserts/logo.png'

let router = useRouter()
let activeIndex = ref('1')
let logopng = img_logo_png
function gotoHome(){
    router.push('/home')
}
</script>

<style scoped>
.menu-container{
    height:80%
}

.menu-wrapper {
    height: 40px;
    margin: 2px 16px 0;
    border-radius: 12px;
    top: 30px;
}

.menu-wrapper:hover {
    background-color: orange;
    color: white;
}

.menu-item {
    padding: 8px 16px;
    text-decoration: none;
    font-size: 18px;
    color: black;
}
</style>