<template>
  <div v-if="movie" class="movie-detail">
    <h2>{{ movie.title }}</h2>
    <video controls autoplay :src="movie.videoUrl" class="movie-player"></video>
    <p class="movie-description">{{ movie.description }}</p>
  </div>
  <!-- 当 movie 没有数据时，显示友好提示信息 -->
  <div v-else>
    <p>未找到匹配的电影，请返回选择其他电影。</p>
  </div>
</template>

<script setup>
import {ref, onMounted} from 'vue';
import {useRoute} from 'vue-router';

// 模拟电影数据（可以替换为实际的API请求）
const movies = [
  {
    id: 1,
    title: '智取威虎山',
    videoUrl: '/videos/智取威虎山.mp4',
    description: '[内容简介]《智取威虎山》是由博纳影业、八一电影制片厂出品，徐克执导，李杨、徐克、吴兵编剧，张涵予、梁家辉、林更新、余男、陈晓、杜奕衡等主演的战争片。 该片改编自曲波小说《林海雪原》，讲述了解放军特派员杨子荣（张涵予饰）扮演土匪角色，深入敌后，秘密潜入土匪藏身的威虎山搜集关键情报，与背后的203小分队紧密配合'
  },
   { id: 2, title: '西虹市首富', videoUrl: '/videos/西红柿首付.mp4', description: '[内容简介]《西虹市首富》是由林炳宝编剧，闫非、彭大魔编剧兼执导，沈腾、宋芸桦、张一鸣、常远、张晨光、魏翔等主演的喜剧片，于2018年7月27日在中国大陆上映' },
  // 更多电影数据
];

// 获取当前的路由参数
const route = useRoute();
const movieId = parseInt(String(route.params.id), 10);
const movie = ref(null);

// 根据ID找到对应的电影
onMounted(() => {
  movie.value = movies.find(m => m.id === movieId);
});
</script>

<style scoped>
.movie-detail {
  text-align: center;
  padding: 20px;
}

.movie-player {
  width: 80%;
  max-width: 800px;
  margin: 20px auto;
}

.movie-description {
  font-size: 1.2em;
  color: #666;
}
</style>
