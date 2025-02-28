import { defineStore } from "pinia";

export const userLoginStore = defineStore('userLoginStore',{
    
    actions:{
        //登录成功后保存用户信息
        storeUser(userId:string,nickName:string,avatar:string){
            this.userId = userId;
            this.nickName = nickName;
            this.avatar = avatar;
        },
        storeUserId(userId:string){
            this.userId = userId
        },
    },
    getters:{
        //获取登录的用户信息
        getLoginedUser():any{
            if(this.userId){
                return {
                    userId:this.userId,
                    nickName:this.nickName,
                    avatar:this.avatar
                }
            }
        },
        getLoginUserId():any{
            return this.userId
        }
    },
    state:()=>{
        return {
            userId:'',
            nickName:'',
            avatar:''
        }
    },
    storage:{
        enabled: true
    }
})