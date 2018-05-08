package com.lxd.daily.netty.custom.handler;

import com.lxd.daily.constants.Constants;
import com.lxd.daily.netty.custom.pojo.Body;
import com.lxd.daily.netty.custom.pojo.Header;
import com.lxd.daily.netty.custom.pojo.MessageType;
import com.lxd.daily.netty.custom.pojo.NettyMessage;
import com.lxd.daily.util.DateTimeUtils;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 服务端握手认证处理
 * 只允许白名单内的ip建立连接
 * Created by liaoxudong on 2017/8/1.
 */
public class ShakeHandAuthRespHandler extends ChannelHandlerAdapter{
    private static final Logger logger = LoggerFactory.getLogger(ShakeHandAuthRespHandler.class);

    private Map<String, Boolean> checkedLists = new ConcurrentHashMap<>();

    // 白名单
    private String[] whiteList = {"127.0.0.1","192.168.168.168"};

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NettyMessage message = (NettyMessage) msg;
        // 如果是握手消息 处理，否则透传
        if (message.getHeader() != null && message.getHeader().getType() == MessageType.SHAKE_HAND_REQ.getCode()) {
            String clientAddress = ctx.channel().remoteAddress().toString();
            // 重复验证 拒绝
            NettyMessage resp = null;
            if (checkedLists.containsKey(clientAddress)) {
                logger.error("重复认证，拒绝");
                resp = buildResp(Constants.FAILURE);
            } else{
                InetSocketAddress inetSocketAddress = (InetSocketAddress) ctx.channel().remoteAddress();
                String clientIp = inetSocketAddress.getAddress().getHostAddress();
                boolean isOK = false;
                for(String WIP:whiteList){
                    if (WIP.equals(clientIp)) {
                        isOK = true;
                        break;
                    }
                }
                resp = isOK ? buildResp(Constants.SUCCESS) : buildResp(Constants.FAILURE);
                if(isOK){
                    checkedLists.put(clientIp, true);
                }
                logger.info("握手应答消息：{}",resp);
                ctx.writeAndFlush(resp);
            }
        }else{
            ctx.fireChannelRead(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 删除缓存
        checkedLists.remove(ctx.channel().remoteAddress().toString());
        ctx.close();
        ctx.fireExceptionCaught(cause);
    }

    /**
     * 构建握手应答消息
     * @param respCode
     * @return
     */
    private NettyMessage buildResp(String respCode) {
        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setType(MessageType.SHAKE_HAND_RESP.getCode());
        message.setHeader(header);

        Body body = new Body();
        body.setMessage(respCode);
        body.setDateTime(DateTimeUtils.formatDatetime1(new Date()));
        message.setBody(body);
        return message;
    }
}
