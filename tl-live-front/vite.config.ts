import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    vue(),
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  },
  //解决跨域问题。
  server:{
    proxy:{
      '/user/':{
        // target:'http://localhost:8081',
        // target:'http://service.tllive.com:8081',
        //添加gateway后，换成80端口
        target:'http://localhost:4080',
        changeOrigin: true,
        pathRewrite: {
          '^/user': ''
        }
        // rewrite:
      },
      '/im/':{
        //添加gateway后，换成80端口
        target:'http://service.tllive.com:4080',
        changeOrigin: true
      },
      '/hls/':{
        target:'http://192.168.233.132',
        ws:false,
        changeOrigin: true
      }
    }
  }
})
