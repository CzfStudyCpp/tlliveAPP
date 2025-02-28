package com.tl.common.redis;


public class IMCacheKeyBuilder {

    private static final String IM_TOKEN_PREFIX = "IM_TOKEN_";
    public String buildIMTokenCacheKey(String token){
        return IM_TOKEN_PREFIX+token;
    }

    public String buildRoomUserCacheKey(String roomId){
        return "ROOM_USER_"+roomId;
    }

    public String buildRoomMessageCacheKey(Long roomId){
        return "ROOM_MESSAGE"+roomId;
    }
}
