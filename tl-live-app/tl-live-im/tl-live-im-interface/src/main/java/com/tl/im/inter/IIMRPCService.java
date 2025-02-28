package com.tl.im.inter;

import com.tl.im.protocol.GenericMessage;


public interface IIMRPCService {

    /**
     * 向直播间发送公告
     * @param roomId
     * @param message
     * @return
     */
    boolean sendMesasgeToRoom(Long roomId,String message);

    /**
     * 向直播间推送聊天消息
     * @param roomId
     * @param genericMessage
     * @return
     */
    boolean pushChatMessage(Long roomId, GenericMessage genericMessage);
}
