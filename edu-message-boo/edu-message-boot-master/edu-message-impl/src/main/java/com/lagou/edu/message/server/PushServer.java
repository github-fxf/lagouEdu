package com.lagou.edu.message.server;

import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketConfig;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.corundumstudio.socketio.listener.ExceptionListener;
import com.corundumstudio.socketio.namespace.Namespace;
import com.corundumstudio.socketio.protocol.Packet;
import com.corundumstudio.socketio.protocol.PacketType;
import com.corundumstudio.socketio.store.pubsub.DispatchMessage;
import com.lagou.edu.common.jwt.JwtUtil.JwtResult;
import com.lagou.edu.common.string.GsonUtil;
import com.lagou.edu.message.api.dto.Message;
import com.lagou.edu.message.consts.Constants;
import com.lagou.edu.message.server.dicpacth.Dispatcher;
import com.lagou.edu.message.server.store.StoreFacotryProvider;
import com.lagou.edu.message.util.PushUtils;
import com.lagou.edu.message.util.ServerConfigUtils;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PushServer {

    public static final PushServer pushServer = new PushServer();
    private Namespace pushNamespace;
    private SocketIOServer server;

    private PushServer() {

        
    }

    /**
     * 推送消息
     *
     * @param message
     */
    public void push(Message message) {
    	
    }

    /**
     * 同步启动服务；
     */
    public void start() {
        try {
            server.start();
        } catch (Exception e) {
            log.error("Push server start failed!", e);
            System.exit(-1);
        }
    }

    /**
     * 停止服务
     */
    public void stop() {
        server.stop();
    }

    public Map<String, Object> getStatus() {
        HashMap<String, Object> status = new HashMap<>();
        status.put("namespace", pushNamespace.getName());   // namespace
        status.put("rooms", pushNamespace.getRooms());
        status.put("clients", pushNamespace.getAllClients().size());
        return status;
    }

}
