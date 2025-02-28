<template>  
    <div class="live-stream-container">  
        <div class="live-stream-header">  
            <h2>直播间列表</h2>  
            <button @click="addRoom">添加直播间</button>  
        </div>  
        <div class="live-stream-scroll-container">  
            <div class="live-stream-grid">  
                <div v-for="(room, index) in rooms" :key="room.id" class="live-stream-card-wrapper">  
                    <el-card class="live-stream-card" @click="gotoRome(room.id)">  
                        <img class="live-stream-image" :src="room.imageUrl" alt="直播间图片" />  
                        <div class="live-stream-content">  
                        <h3 class="live-stream-title">{{ room.title }}</h3>  
                        <p class="live-stream-description">{{ room.description }}</p>  
                        </div>  
                    </el-card>  
                </div>  
            </div>  
        </div>  
    </div>  
</template>  
    
<script setup>  
import { ref } from 'vue';  
import { ElCard } from 'element-plus';  
import { useRouter } from 'vue-router';

let router = useRouter()
  // 直播间数据  
  const rooms = ref([  
    {  
      id: 1,  
      title: '直播间1',  
      description: '这是直播间1的描述',  
      imageUrl: '/img/1.jpeg',  ==



    },  
    {  
      id: 2,  
      title: '直播间2',  
      description: '这是直播间2的描述',  
      imageUrl: '/img/2.jpeg',  
    }, 
    {  
      id: 3,  
      title: '直播间3',  
      description: '这是直播间3的描述',  
      imageUrl: '/img/3.png',  
    }, 
    {  
      id: 4,  
      title: '直播间4',  
      description: '这是直播间4的描述',  
      imageUrl: '/img/4.jpeg',  
    }, 
    {  
      id: 5,  
      title: '直播间5',  
      description: '这是直播间5的描述',  
      imageUrl: '/img/5.jpeg',  
    }
    // ...更多直播间数据  
  ]);  
    
const addRoom = () => {  
  const newRoomId = Math.max(...rooms.value.map(room => room.id), 0) + 1;  
  const newRoom = {  
    id: newRoomId,  
    title: `直播间${newRoomId}`,  
    description: `这是直播间${newRoomId}的描述`,  
    imageUrl: `/img/5.jpeg`, // 假设图片URL有规律可循  
  };  
  rooms.value.push(newRoom);  
}; 
const gotoRome = (roomid) => {
    console.log("跳转到直播间：",roomid)
    let targetURL = router.resolve({path : '/room/'+roomid}).href
    window.open(targetURL,'_black')
    // router.push("/room/roomid")
}
</script>  
    
<style scoped>  
.live-stream-container {  
  display: flex;  
  flex-direction: column;  
  height: 100vh; /* 设置容器高度为视口高度 */  
  overflow: hidden; /* 隐藏超出容器的内容 */  
}  
  
.live-stream-header {  
  display: flex;  
  justify-content: space-between;  
  align-items: center;  
  margin-bottom: 20px;  
}  
.live-stream-scroll-container {  
  flex: 1; /* 占据剩余空间 */  
  overflow-y: auto; /* 允许在垂直方向上滚动 */  
  padding: 20px; /* 为滚动内容添加内边距 */  
} 
  
.live-stream-grid {  
  display: grid;  
  grid-template-columns: repeat(4, 1fr); /* 四列布局 */  
  grid-gap: 20px; /* 列间距 */  
}
  
.live-stream-card-wrapper {  
  width: 100%;  
}  
  
.live-stream-card {  
  width: 100%;  
  border-radius: 10px;  
  overflow: hidden;  
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);  
}  
  
.live-stream-image {  
  width: 100%;  
  aspect-ratio: 16/9; /* 设置图片宽高比 */  
  object-fit: cover;  
}  
  
.live-stream-content {  
  padding: 15px;  
}  
  
.live-stream-title {  
  margin: 0 0 10px;  
  font-size: 18px;  
  font-weight: bold;  
}  
  
.live-stream-description {  
  margin: 0;  
  font-size: 14px;  
  color: #666;  
}   
</style>