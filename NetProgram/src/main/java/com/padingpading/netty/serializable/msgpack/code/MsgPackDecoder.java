package com.padingpading.netty.serializable.msgpack.code;

import com.padingpading.netty.serializable.msgpack.entity.User;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.msgpack.MessagePack;

import java.util.List;

/*基于MessagePack的解码器，反序列化*/
public class MsgPackDecoder extends MessageToMessageDecoder<ByteBuf> {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out)
            throws Exception {
        final int length = msg.readableBytes();
        final byte[] array = new byte[length];
        //写入数据
        msg.getBytes(msg.readerIndex(),array,0,length);
        MessagePack messagePack = new MessagePack();
        //反序列化成User
        out.add(messagePack.read(array, User.class));
    }
}
