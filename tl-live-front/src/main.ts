import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'

import { closeWebSocket } from './components/websocket'

import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import 'video.js/dist/video-js.min.css'

import piniaPluginStorage from "pinia-plugin-storage"

const app = createApp(App)
//浏览器关闭时，触发一次关闭链接动作
window.addEventListener('beforeunload',closeWebSocket)
const store = createPinia()
store.use(piniaPluginStorage)
app.use(store)
app.use(router)
app.use(ElementPlus)

app.mount('#app')
