<template>
    <div class="root">
        <el-container class="main-container">
            <el-aside width="150px">
                <navBar />
            </el-aside>
            <el-container>
                <el-header><pageHeader /></el-header>
                <el-main><RouterView></RouterView></el-main>
                <el-footer>Copyright © 712团队 All Rights Reserved.</el-footer>
            </el-container>
        </el-container>
    </div>
</template>
<script setup lang="ts">
import pageHeader from '@/views/layout/pageHeader.vue'
import navBar from '@/views/layout/navBar.vue'
</script>
<style scoped>
.root{
    height: 100%;
    width: 100%;
    position: absolute;
    left: 0;
    bottom: 0;
    /* background-color: gray; */
}
.main-container{
    height: 100%;
    /* background-color: gray; */
}
</style>