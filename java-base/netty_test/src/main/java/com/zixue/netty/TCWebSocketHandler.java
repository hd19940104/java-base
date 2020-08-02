package com.zixue.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.multipart.DefaultHttpDataFactory;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;
import io.netty.handler.codec.http.multipart.MemoryAttribute;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import io.netty.util.CharsetUtil;
 
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
/**
 * ����/����/��Ӧ�ͻ���websocket����ĺ���ҵ������
 * ͨ�����hanlder�����ǿ��Լ���Channel�ĸ��ֶ����Լ�״̬�ĸı䣬�������ӣ��󶨣�������Ϣ�ȡ�
 *
 *  Timely communication
 *  �������Ҫ����ͨѶʱ��Ѱ�Ҷ�Ӧ��ͨ��
 */
public class TCWebSocketHandler extends SimpleChannelInboundHandler<Object> {
 
    // ���ڷ�������web�׽��ִ򿪺͹ر�����
    private WebSocketServerHandshaker handshaker;
 
    private static final String WEB_SOCKET_URL = "/websocket";
 
 
    //�ͻ��������˴������ӵ�ʱ�����
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
    	TCChannelManage.group.add(ctx.channel());
        System.out.println("�ͻ������������ӿ������ͻ���remoteAddress��" + ctx.channel().remoteAddress());
    }
 
    //�ͻ��������˶Ͽ����ӵ�ʱ�����
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    	TCChannelManage.group.remove(ctx.channel());
        System.out.println("�ͻ������������ӹر�...");
    }
 
    //����˽��տͻ��˷��͹��������ݽ���֮�����
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
 
    //���̳����쳣��ʱ�����
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
 
    //����˴���ͻ���websocket����ĺ��ķ���
    protected void messageReceived(ChannelHandlerContext context, Object msg) throws Exception {
 
        /* ��ͳ��HTTP���루����http����ʽ��
         * ��һ������������Ϣ��HTTPЭ����أ���������һ��HTTP��Ϣ��
         * ���ֳɹ������ݾ�ֱ�Ӵ� TCP ͨ�����䣬�� HTTP �޹��ˡ�
         * ִ��handleHttpRequest����������WebSocket��������
         */
 
        // FullHttpRequest�������� HTTP����Э��ͷ��Form��������һ��ģ����÷ֿ���
        if (msg instanceof FullHttpRequest) {
            handHttpRequest(context, (FullHttpRequest) msg);
        }
        /**
         *  WebSocket���루����socket����ʽ��
         *  �ύ������Ϣ������ˣ�
         *  WebSocketServerHandler���յ������Ѿ�������WebSocketFrame��Ϣ��
         */
        else if (msg instanceof WebSocketFrame) {
            handWebsocketFrame(context, (WebSocketFrame) msg);
        }
        /**
         * Websocket�����ݴ�����frame��ʽ����ģ�����Ὣһ����Ϣ��Ϊ����frame�������Ⱥ�˳�����ȥ�����������м����ô���
         *
         * 1�������ݵĴ�����Է�Ƭ���䣬���ÿ��ǵ����ݴ�С���µĳ��ȱ�־λ���㹻�������
         *
         * 2����http��chunkһ�������Ա��������ݱߴ�����Ϣ������ߴ���Ч�ʡ�
         */
    }
 
    /**
     * ����ͻ���������֮ǰ��websocketҵ��
     *
     * @param ctx
     * @param frame
     */
    private void handWebsocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
 
        //�ж��Ƿ��ǹر�websocket��ָ��
        if (frame instanceof CloseWebSocketFrame) {
            handshaker.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
        }
 
        //�ж��Ƿ���ping��Ϣ
        if (frame instanceof PingWebSocketFrame) {
            ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
            return;
        }
 
        if (frame instanceof TextWebSocketFrame) {
 
            // ����Ӧ����Ϣ
            String requestMsg = ((TextWebSocketFrame) frame).text();
            System.out.println("�յ��ͻ���" + ctx.channel().remoteAddress() + "����Ϣ==��" + requestMsg);
            String[] array = requestMsg.split(",");
            // ���ж�ͨ�����������Ƿ���ڸ�ͨ����û������ӽ�ȥ
            if (!TCChannelManage.hasChannel(array[0])) {
            	TCChannelManage.userIdAndChannelMap.put(array[0], ctx.channel());
            }
 
            if (array[0].length() != 0 && array[1].length() != 0) {
            	TCChannelManage.send(array[0], array[1], array[2], ctx.channel());
            } else if (array[0].length() != 0 && array[1].length() == 0) {
                //���û��ָ�������߱�ʾȺ��array.length() = 2
                System.out.println("�û�" + array[0] + "Ⱥ����һ����Ϣ��" + array[2]);
                TCChannelManage.group.writeAndFlush(new TextWebSocketFrame("�û�" + array[0] + "Ⱥ����һ����Ϣ��" + array[2]));
            } else {
                //���û��ָ��������������߱�ʾ�����˷���array.length() = 1
                System.out.println("����˽����û�" + ctx.channel().remoteAddress() + "��Ϣ�����ٷ��ͳ�ȥ");
                ctx.writeAndFlush(new TextWebSocketFrame("�������˷�������Ϣ==��" + array[2]));
            }
        }
    }
 
 
    /**
     * ����ͻ��������˷���http���������ҵ��
     *
     * @param ctx
     * @param req
     */
    private void handHttpRequest(ChannelHandlerContext ctx, FullHttpRequest req) {
        System.out.println("����http����http����==>>" + req.getMethod() + ",http��ַ==>>" + req.getUri());
        Map<String, String> parmMap = new HashMap<>();
        try {
            parmMap = parse(req);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // �������WebSocket����������Ϣ����ô�ͷ��� HTTP 400 BAD REQUEST ��Ӧ���ͻ��ˡ�
        if (!req.getDecoderResult().isSuccess()
                || !("websocket".equals(req.headers().get("Upgrade")))) {
            sendHttpResponse(ctx, req,
                    new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
            return;
        }
        //���������������ô�ͽ�������
        WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(
                WEB_SOCKET_URL, null, false);
        handshaker = wsFactory.newHandshaker(req);
        if (handshaker == null) {
            WebSocketServerHandshakerFactory.sendUnsupportedWebSocketVersionResponse(ctx.channel());
        } else {
            // ͨ��������������Ӧ��Ϣ���ظ��ͻ��ˣ�
            // ͬʱ��WebSocket��صı���ͽ����ද̬��ӵ�ChannelPipeline�У�����WebSocket��Ϣ�ı���룬
            // ���WebSocketEncoder��WebSocketDecoder֮�󣬷���˾Ϳ����Զ���WebSocket��Ϣ���б������
            handshaker.handshake(ctx.channel(), req);
        }
    }
 
    /**
     * �������ͻ�����Ӧ��Ϣ
     *
     * @param ctx
     * @param req
     * @param res
     */
    private void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req,
                                  DefaultFullHttpResponse res) {
        // ����Ӧ����ͻ���
        if (res.getStatus().code() != 200) {
            ByteBuf buf = Unpooled.copiedBuffer(res.getStatus().toString(), CharsetUtil.UTF_8);
            res.content().writeBytes(buf);
            buf.release();
        }
        // ����Ƿ�Keep-Alive���ر�����
        ChannelFuture f = ctx.channel().writeAndFlush(res);
        if (res.getStatus().code() != 200) {
            f.addListener(ChannelFutureListener.CLOSE);
        }
    }
 
    /**
     * ����GET��POST�������
     * @return ����������������ļ�ֵ��, ���û�в���, �򷵻ؿ�Map
     *
     * @throws IOException
     */
    public Map<String, String> parse(FullHttpRequest fullReq) throws IOException {
 
        HttpMethod method = fullReq.getMethod();
 
        Map<String, String> parmMap = new HashMap<>();
 
        if (HttpMethod.GET == method) {
            // ��GET����
            QueryStringDecoder decoder = new QueryStringDecoder(fullReq.getUri());
            decoder.parameters().entrySet().forEach( entry -> {
                // entry.getValue()��һ��List, ֻȡ��һ��Ԫ��
                parmMap.put(entry.getKey(), entry.getValue().get(0));
            });
        } else if (HttpMethod.POST == method) {
            HttpPostRequestDecoder decoder = new HttpPostRequestDecoder(
                    new DefaultHttpDataFactory(false), fullReq);
            List<InterfaceHttpData> postData = decoder.getBodyHttpDatas();
            for(InterfaceHttpData data:postData){
                if (data.getHttpDataType() == InterfaceHttpData.HttpDataType.Attribute) {
                    MemoryAttribute attribute = (MemoryAttribute) data;
                    parmMap.put(attribute.getName(), attribute.getValue());
                }
            }
        } else {
            // ��֧����������
            System.out.println("��֧�����������ύ�Ĳ���");
        }
 
        return parmMap;
    }
 
	@Override
	protected void channelRead0(ChannelHandlerContext arg0, Object arg1)
			throws Exception {
		messageReceived(arg0,arg1);		
	}
}