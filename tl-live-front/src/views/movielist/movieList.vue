<template>
    <div class="movie-list">
        <div class="movie-item" v-for="movie in movies" :key="movie.id" @mouseover="showDescription(movie)"
            @mouseout="hideDescription" @click="showMovie(movie)">
            <img :src="movie.image" :alt="movie.title" @mouseover="showDescription(movie)"
                @mouseout="hideDescription" />
            <p class="movie-title">{{ movie.title }}</p>
            <div class="movie-description-wrapper" v-if="currentMovie === movie">
                <p class="movie-description">{{ movie.description }}</p>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router'; // 引入 useRouter

// 测试电影数据  
const movies = ref([
    { id: 1, title: '智取威虎山', image: '/img/movie1.jpeg', description: '[内容简介]《智取威虎山》是由博纳影业、八一电影制片厂出品，徐克执导，李杨、徐克、吴兵编剧，张涵予、梁家辉、林更新、余男、陈晓、杜奕衡等主演的战争片。 该片改编自曲波小说《林海雪原》，讲述了解放军特派员杨子荣（张涵予饰）扮演土匪角色，深入敌后，秘密潜入土匪藏身的威虎山搜集关键情报，与背后的203小分队紧密配合' },
    { id: 2, title: '西虹市首富', image: '/img/movie2.jpeg', description: '[内容简介]《西虹市首富》是由林炳宝编剧，闫非、彭大魔编剧兼执导，沈腾、宋芸桦、张一鸣、常远、张晨光、魏翔等主演的喜剧片，于2018年7月27日在中国大陆上映' },
    { id: 3, title: '夏洛特烦恼', image: '/img/movie3.jpeg', description: '[内容简介]这是国内最火爆的电影，非常非常好看。图乐直播，记录美好生活' },
    { id: 4, title: '人在囧途', image: '/img/movie4.png', description: '[内容简介]这是国内最火爆的电影，非常非常好看。图乐直播，记录美好生活' },
    { id: 5, title: '龙门飞甲', image: '/img/movie5.jpeg', description: '[内容简介]这是国内最火爆的电影，非常非常好看。图乐直播，记录美好生活' },
    { id: 6, title: '四大天王', image: '/img/movie6.jpeg', description: '[内容简介]这是国内最火爆的电影，非常非常好看。图乐直播，记录美好生活' },
    { id: 7, title: '武状元苏乞儿', image: '/img/movie7.png', description: '[内容简介]这是国内最火爆的电影，非常非常好看。图乐直播，记录美好生活' }
    // ... 更多电影数据  
]);

// 当前悬停的电影  
const currentMovie = ref(null);

const router = useRouter();
// 显示简介  
const showDescription = (movie) => {
    currentMovie.value = movie;
};

// 隐藏简介  
const hideDescription = () => {
    currentMovie.value = null;
};

const showMovie = (movie) => {
  router.push({ path: `/movie/${movie.id}` });
}
</script>

<style scoped>
.movie-list {
    display: flex;
    flex-wrap: wrap;
    justify-content: flex-start;
}

.movie-item {
    position: relative;
    width: calc(16.66% - 20px);
    /* 假设每行显示3个电影 */
    margin: 10px;
    background-color: #fff;
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
    border-radius: 5px;
    overflow: hidden;
    /* 确保简介不会溢出电影项 */
}

.movie-item img {
    width: 100%;
    height: auto;
    display: block;
    transition: opacity 0.3s ease;
    /* 平滑过渡效果 */
}

.movie-item:hover img {
    opacity: 0.7;
    /* 鼠标悬停时图片变暗 */
}

.movie-title {
    padding: 10px;
    text-align: center;
}

.movie-description-wrapper {
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    background: rgba(0, 0, 0, 0.5);
    /* 半透明背景 */
    color: #fff;
    display: flex;
    align-items: center;
    /* 垂直居中 */
    justify-content: center;
    /* 水平居中 */
    opacity: 0;
    transition: opacity 0.3s ease;
    /* 平滑过渡效果 */
    pointer-events: none;
    /* 确保点击事件可以穿透到下面的图片 */
}

.movie-item:hover .movie-description-wrapper {
    opacity: 1;
    /* 鼠标悬停时显示简介 */
}

.movie-description {
    padding: 10px;
    text-align: center;
    max-width: 80%;
    /* 可选，限制简介宽度 */
}
</style>