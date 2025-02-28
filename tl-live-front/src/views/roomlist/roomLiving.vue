<template>
    <div class="root">
        <el-container class="main-container">
            <el-aside width="150px">
                <navBar />
            </el-aside>
            <el-container>
                <el-header>
                    <pageHeader />
                </el-header>
                <el-main style="height:100vh">
                    <el-row :gutter="20">
                        <el-col :span="4">&nbsp;
                        </el-col>
                        <el-col :span="11" style="height:800px">
                            <div class="shop_info">
                                <div class="shop_img_head">
                                    <b>秒杀商品</b>
                                </div>
                                <div v-for="item in shopInfoList" :key="item.skuId">
                                    <img :src="item.iconUrl" class="shop_img" alt="蓝牙耳机"
                                        @click="toShowShopTab(item.skuId)">
                                </div>
                            </div>

                            <!-- <div class="shop_tab" v-show="showShopTab == true" id="shop_tab">
                                <div style="text-align: center;">
                                    <img :src="shopDetailInfo.originalIconUrl" class="shop_detail_img">
                                </div>
                                <div style="text-align: center;">
                                    <div style="color: orange;"><b>{{ shopDetailInfo.name }}</b></div>
                                    <div style="color: grey;">{{ shopDetailInfo.remark }}</div>
                                    <div><span style="color: red;"><b>{{ shopDetailInfo.skuPrice }}元</b></span>
                                    </div>
                                    <div style="margin-top: 20px;">
                                        <el-button type="success"
                                            @click="addShopCar(shopDetailInfo.skuId)">加入购物车</el-button>
                                    </div>
                                </div>
                            </div> -->

                            <!-- <div class="car_tab" id="car_detail" v-show="showCarTab == true">
                                <div v-show="showOrderTab == false">
                                    <div class="car_item" v-for="item in shopCarInfo" :key="item.skuInfoDTO">
                                        <img :src="item.skuInfoDTO.iconUrl" class="car_item_img" alt="">
                                        <span class="car_item_desc">{{ item.skuInfoDTO.name }} 数量：{{ item.count
                                            }}</span>
                                        <el-button class="clear-shop-info-btn"
                                            @click="removeShopCarItem(item.skuInfoDTO.skuId)"
                                            type="danger">删除</el-button>
                                    </div>
                                    <div style="text-align: center;margin-top: 20px;">
                                        <el-input v-model="address" style="width: 60%;"
                                            placeholder="请填写下单收货地址"></el-input>
                                        <br>
                                        <br>
                                        <el-button type="success" @click="prepareOrder()">下单支付</el-button>
                                    </div>
                                </div>
                                <div v-show="showOrderTab == true">
                                    <div class="car_item" v-for="item in shopCarInfo" :key="item.skuInfoDTO">
                                        <img :src="item.skuInfoDTO.iconUrl" class="car_item_img" alt="">
                                        <span class="car_item_desc">{{ item.skuInfoDTO.name }} 数量：{{ item.count
                                            }}</span>
                                    </div>
                                    <div class="pre_order_total_price">
                                        <b>总金额：{{ shopCarTotalPrice }}币</b>
                                    </div>
                                    <div style="text-align: center;margin-top: 20px;">
                                        <el-button type="success" @click="payNow()">立马支付</el-button>
                                    </div>
                                    <div style="text-align: center;margin-top: 20px;">
                                        <el-button type="info" @click="turnBackShopCar()">返回购物车</el-button>
                                    </div>
                                </div>
                            </div> -->


                            <div class="liveroom_top">
                                <div style="position: relative;top: -30px;">
                                    <div class="anchor_name">
                                        <div style="display: inline-block;">当前主播：{{ anchorInfo.nickName }}</div>
                                        <img src="@/asserts/living.jpg" class="anchor_label" alt="">
                                        <el-button type="danger" round
                                            style="position: relative;left: 10px;">关注</el-button>
                                        <el-button type="info" :icon="Share" circle alt="分享"></el-button>
                                    </div>
                                    <img :src="anchorInfo.avatar" class="anchor_img" alt="">
                                </div>
                            </div>

                            <!-- <div id="svga-wrap" class="svga-wrap">
                            </div> -->
                          <video class="video-js" ref="videoplayer" width="100%"
                                 style="background-color: rgb(18, 9, 37); width: 100%; height: 400px"
                                 muted autoplay></video>

                          <div class="gift_content">
                                <div class="gift_content_title">礼物面板</div>
                                <div class="bank_tab">
                                    <span style="position: relative;left: -50px;" @click="toShowCarTab()">
                                        查看购物车
                                    </span>
                                    <span @click="showBankInfoTab()">
                                        钱包余额:
                                    </span>
                                    <span style="color: white">{{ currentBalance }}</span>
                                </div>
                                <div style="white-space: nowrap;overflow-x: scroll;overflow-y: hidden;">
                                    <div class="gift_item" v-for="item in giftList" :key="item.giftId">
                                        <img @click="sendGift(item)" :src="item.coverImgUrl" class="gift_img" alt="">
                                        <div class="gift_item_name">{{ item.giftName }}</div>
                                        <div class="gift_item_price">{{ item.price }}金币</div>
                                    </div>
                                </div>
                            </div>
                        </el-col>
                        <!-- <div class="grey_bg" v-show="showBankInfo == true" @click="hiddenBankInfoTabNow()">
                        </div>
                        <div class="recharge_tab" v-show="showBankInfo == true">
                            <div style="height: 60px;">
                                <span><img src="@/asserts/logo.jpg" alt=""
                                        style="width: 40px;height: 40px;position:relative; top: 15px;left: 10%;">
                                    <span style="position: relative;left: 12%;top:5px;">在线充值</span>
                                    <span style="position: relative;left: 50%;top:5px;">当前余额：
                                        <span style="color: #f37d08;"><b>{{ currentBalance }}</b></span>
                                    </span>
                                </span>
                            </div>
                            <div style="text-align: center;">
                                <span v-for="item in payProducts" :key="item.id">
                                    <button class="recharge_btn" :id="item.id" @click="payProduct(item.id)">
                                        {{ item.name }}<br>
                                        <span class="coin_um">{{ item.coinNum }}金币</span>
                                    </button>
                                </span>
                            </div>
                            <div style="height: 50px;" v-show="qrCode == ''"></div>
                            <div style="height: 250px;" v-show="qrCode != ''">
                                <div style="text-align: center;">
                                    <img src="@/asserts/qrcode.png" style="height: 180px;width: 180px;" alt="">
                                    <div style="font-size: 13px;color: #a4a4a4">
                                        请使用 <img
                                            style="display: inline-block;width: 13px;height: 13px;position:relative;top: 1px;"
                                            src="@/asserts/zfb.jpg"> 支付宝/
                                        <img style="display: inline-block;width: 13px;height: 13px;position:relative;top: 1px;"
                                            src="@/asserts/wx.jpg"> 微信 扫码支付
                                    </div>
                                </div>
                            </div>
                        </div> -->
                        <el-col :span="5">
                            <div class="talk-content-box" id="chatContentBox">
                                <div class="chat-content" v-for='chatItem in chatList' :key="chatItem">
                                    <div v-show="chatItem.msgType == 5">
                                        <div class="gift_msg">
                                            {{ chatItem.content }}
                                        </div>
                                    </div>
                                    <div v-show="chatItem.msgType == 2">
                                        <!-- <span class="user-name">说：</span> -->
                                        <!-- <img :src="chatItem.msg.senderImg" class="chat-avatar"> -->
                                        <!-- <span class="chat-content"> -->
                                        <span class="user-name">{{ chatItem.senderName }} :</span>
                                        <span>{{ chatItem.content }}</span>
                                        <!-- </span> -->
                                        <!-- {{ chatItem }} -->
                                    </div>
                                    <div class="chat-content" v-show="chatItem.msgType == 0">
                                        {{ chatItem.content }}
                                    </div>
                                </div>
                            </div>
                            <div>
                                <br>
                                <el-form v-if="userId">
                                    <el-form-item>
                                        <el-input v-model="comment" placeholder="发送直播评论"></el-input>
                                    </el-form-item>
                                    <el-form-item style="text-align:right;">
                                        <el-button type="primary" @click="sendComment()">发送消息</el-button>
                                    </el-form-item>
                                </el-form>
                                <button style="width: 90%;" v-if="!userId">请先登录，才能开始聊天</button>
                            </div>
                        </el-col>
                    </el-row>
                </el-main>
                <el-footer>Copyright © 712 All Rights Reserved.</el-footer>
            </el-container>
        </el-container>
    </div>

</template>
<script setup>
import '@/styles/common.css'
import '@/styles/living_room.css'
// import '@/styles/red_packet.css'

import { Share } from '@element-plus/icons-vue'

import pageHeader from '@/views/layout/pageHeader.vue'
import navBar from '@/views/layout/navBar.vue'
import { ElMessageBox, ElMessage } from 'element-plus'

import { nextTick, onMounted, onUnmounted, ref } from 'vue'
import { useRoute } from 'vue-router'
import videojs from 'video.js'

let route = useRoute()
var roomId = route.params.id
//房间ID
console.info('房间ID', roomId)
//视频直播
// http-flv协议拉流
const url = 'http://192.168.233.132/hls/712.m3u8'

const videoplayer = ref(null)
const myPlayer = ref(null)

onMounted(() => {
    myPlayer.value = videojs(videoplayer.value,
        {
            autoplay:'muted',
            poster: "/img/2.jpeg", //默认封面
            controls: true, //显示控制栏
            controlBar: true,
            bigPlayButton: true,
            sources: [{
                src: url
            }]
        },
        () => {
            console.info("播放器加载完成")
        }
    )
    myPlayer.value.on('error', (error) => {
        console.info('播放器加载错误',error)
    })
})

onUnmounted(()=>{
    clearInterval(heartbeatInterval)
})
// 主播信息
let anchorInfo = ref({
    nickName: 'tl-java',
    avatar: '/img/avatar.png'
})
let showBankInfo = ref(false)

function showBankInfoTab() {
    showBankInfo.value = true
}
// function hiddenBankInfoTabNow() {
//     showBankInfo.value = false
// }
//IM部分
import { getWebSocket } from "@/components/websocket"
import { getEmitter } from '@/components/messageEmitter'
import { queryUserById } from '@/http/api'
import { userLoginStore } from '@/store/userLoginStore'
let userStore = userLoginStore()

var comment = ref('')
//websocket请求地址
// var wsUrl = 'ws://localhost:8989/chat'
//用户ID
var userId = ref('')
var userName = ref('')
userId.value = userStore.getLoginUserId
if(userId.value){
    userName.value = userStore.getLoginedUser.nickName
}

// 1、获取Websocket连接
// var socket = getWebSocket(wsUrl, userId.value);
// function async initWebsocket(){
//     return await getWebSocket(userId.value) 
// }
var socket ;
const initWebsocket = async ()=>{
    socket = await getWebSocket(userId.value);
    if(!socket){
        ElMessage({
                showClose: true,
                message: 'IM服务暂未启动，请联系管理员',
                type: 'error',
            })
    }
    console.info('socket', socket)
    // 2、发送一条进入房间的消息
    //前端建立Socket到Socket建立成功，是需要时间的，所以不能直接发送。
    var joinRoomMsg = {}
    joinRoomMsg.type = 0
    joinRoomMsg.roomId = roomId
    if (socket && socket.readyState==WebSocket.OPEN) {
// console.info('发送进入房间的消息', joinRoomMsg)
        socket.send(JSON.stringify(joinRoomMsg))
    } else {
        setTimeout(function () {
console.info('延迟发送进入房间的消息', joinRoomMsg)
            socket.send(JSON.stringify(joinRoomMsg))
        }, 1000)
    }
}
initWebsocket();

// 3、定时往后端发送心跳请求，保证不超时。
var isConfirming = false
var heartbeatInterval = setInterval(function () {
    try{
        socket.send("Heartbeat");
    }catch(e){
        if (!isConfirming) {
            isConfirming = true;
            //提示用户重连
            ElMessageBox.confirm(
                '长时间无操作，已退出IM聊天',
                'ahoo',
                {
                    confirmButtonText: '重新恢复',
                    showCancelButton: false,
                    type: 'warning'
                }
            ).then(() => {
// console.info("重连websocket")
                // socket = getWebSocket(wsUrl, userId.value);
                socket = getWebSocket(userId.value);
                isConfirming = false;
            })
        }
    }
}, 200000000)


// 4、处理收到的聊天消息
var emitter = getEmitter()

var chatList = ref([])
emitter.on("messageReceived", (genericMessage) => {
// console.info("收到消息", genericMessage)
    //消息格式 {typoe:2,roomId:111,fromUserId:111,fromUsername:'',body:[{content:'',msgId:''}]}
    if (!genericMessage || !genericMessage.type) {
        return
        //聊天消息
    } else if (genericMessage.type == 2 && genericMessage.roomId == roomId && genericMessage.body) {
        for (var bodyIndex in genericMessage.body) {
            var messagebody = genericMessage.body[bodyIndex]
            var chatmsg = {}
            chatmsg.msgType = 2
            // chatmsg.senderName = genericMessage.fromUserName+": "
            chatmsg.senderName = messagebody.userName
            chatmsg.content = messagebody.content
            chatList.value.push(chatmsg)
            scrollToBottom();
        }
        //进入房间消息
    } else if (genericMessage.type == 0 && genericMessage.roomId == roomId) {
        var vipInRoomMsg = {}
        vipInRoomMsg.msgType = 0
        vipInRoomMsg.content =  genericMessage.fromUserName + " 进入房间"
        chatList.value.push(vipInRoomMsg)
        //送礼物消息
    } else if (genericMessage.type == 5 && genericMessage.roomId == roomId) {
        for (var index2 in genericMessage.body) {
            var giftMessages = genericMessage.body[index2]
            var giftMsg = {}
            giftMsg.msgType = 5
            giftMsg.content = giftMessages.content
    // console.info("giftMsg:", giftMsg)
            chatList.value.push(giftMsg)
            scrollToBottom();
        }

    }
})

const scrollToBottom = () => {
    nextTick(() => {
        document.getElementById("chatContentBox").scrollTop = document.getElementById("chatContentBox").scrollHeight
    })
}
//5、发送评论
function sendComment() {
    if (comment.value) {
        var commentMsg = {}
        commentMsg.type = 2 // 聊天消息
        commentMsg.roomId = roomId //房间ID
        commentMsg.fromUserId = userId.value//发送者
        commentMsg.fromUserName = userName.value //发送者名字
        commentMsg.body = []
        commentMsg.body.push({ 'content': comment.value })
// console.info("发送聊天消息", commentMsg)
        socket.send(JSON.stringify(commentMsg));
    }
}
//6、送礼物
// 礼物列表
let giftList = ref([
    { giftId: '1', coverImgUrl: '/img/gift1.png', giftName: '大天使', price: '5' },
    { giftId: '2', coverImgUrl: '/img/gift2.png', giftName: '大皇冠', price: '10' },
    { giftId: '3', coverImgUrl: '/img/gift3.png', giftName: '爱心', price: '10' },
    { giftId: '4', coverImgUrl: '/img/gift4.png', giftName: '跑车', price: '20' },
    { giftId: '5', coverImgUrl: '/img/gift5.png', giftName: '彩虹', price: '20' },
    { giftId: '6', coverImgUrl: '/img/gift6.png', giftName: '大红心', price: '30' },
    { giftId: '7', coverImgUrl: '/img/gift7.png', giftName: '棒棒糖', price: '30' },
    { giftId: '8', coverImgUrl: '/img/gift8.png', giftName: '大黄鸭', price: '40' },
    { giftId: '9', coverImgUrl: '/img/gift9.png', giftName: '小灰兔', price: '40' },
    { giftId: '10', coverImgUrl: '/img/gift10.png', giftName: '飞机', price: '50' }
])

function sendGift(gift) {
// console.info("发送礼物消息", gift)
    //未登录用户不能送礼
    if (!userId.value) {
        ElMessage({
            showClose: true,
            message: '未登录用户不能送礼。',
            type: 'error',
        })
        return;
    } else {
        var giftMsg = {}
        giftMsg.type = 5
        giftMsg.roomId = roomId
        giftMsg.fromUserId = userId.value
        giftMsg.fromUserName = userName.value
        giftMsg.body = []
        giftMsg.body.push({ 'content': userName.value + '送给主播一个 ' + gift.giftName })
        socket.send(JSON.stringify(giftMsg));
    }
}

// let payProducts = ref([])
let currentBalance = ref(0)
// let qrCode = ref('true')
// let showShopTab = ref(false)
// let showCarTab = ref(false)
let shopInfoList = ref([])
// let shopDetailInfo = ref({})
// let shopCarInfo = ref([])
// let showOrderTab = ref(false)
// let address = ref('')
// let shopCarTotalPrice = ref(0)
</script>