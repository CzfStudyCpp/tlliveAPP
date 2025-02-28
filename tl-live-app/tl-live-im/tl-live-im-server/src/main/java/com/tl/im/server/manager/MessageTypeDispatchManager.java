package com.tl.im.server.manager;

import com.tl.im.config.IMConstants;
import com.tl.im.protocol.GenericMessage;
import com.tl.im.server.service.MessageHandlerService;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;

@Component
public class MessageTypeDispatchManager {

    private Logger logger = LoggerFactory.getLogger(MessageTypeDispatchManager.class);

    @Resource
    private MessageHandlerService messageHandlerService;

    @Resource(name = "asyncExecutor")
    private Executor executor;

    /**
     * 根据UserID，对消息进行路由。
     * @param userId
     * @param message
     */
    public void messageTypeDispatch(String userId, GenericMessage message) {
        if (message.getType() == null) {
            logger.warn("消息格式异常，直接丢弃");
            return;
        }
        String roomId = message.getRoomId().toString();
        switch (message.getType()) {
            case IMConstants.MESSAGE_TYPE_JOIN_ROOM://加入房间
                ConnectionManager.joinRoom(roomId, userId);
                logger.info("用户=>{},加入房间=>{}", userId, roomId);
                executor.execute(() -> messageHandlerService.sendIndexMessage(userId, roomId));
                break;
            case IMConstants.MESSAGE_TYPE_EXIT_ROOM://退出房间
                ConnectionManager.exitRoom(roomId, userId);
                logger.info("用户=>{},退出房间=>{}", userId, roomId);
                break;
            case IMConstants.MESSAGE_TYPE_GIFT://礼物消息(只考虑送礼物消息，和聊天消息一样转发)
            case IMConstants.MESSAGE_TYPE_CHAT://聊天
                executor.execute(() -> messageHandlerService.sendRoomChatMessage(userId, roomId, message));
                logger.info("用户=>{},房间=>{}，发送消息=>{}", userId, roomId,message);
                break;
            default:
                logger.warn("消息类型异常,message =>{}",message);
        }
    }
}
