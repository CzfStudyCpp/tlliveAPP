package com.tl.im.server.manager;

import io.micrometer.common.util.StringUtils;
import jakarta.websocket.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;


public class ConnectionManagerBAK {

    private static final Logger logger = LoggerFactory.getLogger(ConnectionManagerBAK.class);
    //存放Session缓存
    private static final Map<String, Session> CHANNEL_CONTAINER = new ConcurrentHashMap<>();

    //存放房间与用户关系 key为RoomId, value为Set<UserId>
    private static final Map<String, Set<String>> ROOM_CONTAINER = new ConcurrentHashMap<>();


    public static boolean register(String sessionId,Session session){
        Session addedSession = CHANNEL_CONTAINER.putIfAbsent(sessionId, session);
        if(null != addedSession){
            logger.warn("sessionId:{} 已存在，不允许重复创建连接",sessionId);
            return false;
        }
        return true;
    }

    public static Optional<Session> getSession(String sessionId){
        if(StringUtils.isBlank(sessionId)){
            return Optional.empty();
        }
        return Optional.ofNullable(CHANNEL_CONTAINER.get(sessionId));
    }

    public static void cancel(String userId, Session session) {
        Optional<Session> optConn = getSession(userId);
        if (optConn.isPresent()) {
            if (optConn.get().getId().equals(session.getId())) {
                CHANNEL_CONTAINER.remove(userId);
                logger.debug("清理用户路由成功,userId=>{}", userId);
            }
        }
    }

    public static boolean cancel(Session session){
        for (Map.Entry<String, Session> entry : CHANNEL_CONTAINER.entrySet()) {
            if (entry.getValue().getId().equals(session.getId())) {
                CHANNEL_CONTAINER.remove(entry.getKey());
                logger.debug("清理路由成功,sessionId=>{}", entry.getKey());
                return true;
            }
        }
        return false;
    }

    public static boolean cancel(String sessionId){
        if(CHANNEL_CONTAINER.containsKey(sessionId)){
            try {
                CHANNEL_CONTAINER.get(sessionId).close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            CHANNEL_CONTAINER.remove(sessionId);
            return true;
        }
        return false;
    }

    public static List<Session> getAllSession(){
        return CHANNEL_CONTAINER.values().stream().toList();
    }

    /**
     * 进入房间
     *
     * @param roomId 房间ID
     * @param userId 用户ID
     */
    public static void joinRoom(String roomId,String userId){
        ROOM_CONTAINER.computeIfAbsent(roomId, v -> new ConcurrentSkipListSet<>()).add(userId);
    }

    /**
     * 退出房间
     *
     * @param roomId 房间ID
     * @param userId 用户ID
     */
    public static void exitRoom(String roomId,String userId){
        Set<String> roomUsers = ROOM_CONTAINER.get(roomId);
        if (!CollectionUtils.isEmpty(roomUsers)) {
            roomUsers.remove(userId);
        }
    }

    /**
     * 获取房间内所有用户连接 自己必须在这个房间里
     *
     * @param roomId 房间
     * @param userId 消息发送者ID
     * @return List<Session>
     */
    public static List<Session> getRoomAllUserConnect(String roomId, String userId) {
        Set<String> userSet = ROOM_CONTAINER.get(roomId);
        //房间没人，返回空
        if (userSet == null || userSet.isEmpty()) {
            return Collections.emptyList();
        }
        //自己不在房间里，不能向房间里的人发消息，返回空。
        if(!userSet.contains(userId)){
            return Collections.emptyList();
        }

        LinkedList<Session> resultList = new LinkedList<>();
        userSet.forEach(u -> {
//            if (!u.equals(userId)) {
                Optional<Session> optSession = getSession(u);
                optSession.ifPresent(resultList::add);
//            }
        });
        return resultList;
    }

    /**
     * 获取房间内所有用户连接 对接Dubbo接口
     *
     * @param roomId 房间
     * @param userId 消息发送者ID
     * @return List<Session>
     */
    public static List<Session> getRoomAllUserConnect(String roomId) {
        Set<String> userSet = ROOM_CONTAINER.get(roomId);
        //房间没人，返回空
        if (userSet == null || userSet.isEmpty()) {
            return Collections.emptyList();
        }

        LinkedList<Session> resultList = new LinkedList<>();
        userSet.forEach(u -> {
            Optional<Session> optSession = getSession(u);
            optSession.ifPresent(resultList::add);
        });
        return resultList;
    }
}
