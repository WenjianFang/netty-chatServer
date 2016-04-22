import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;

import java.net.InetAddress;

/**
 * Created by Wenjian on 2016/4/20, 0020.
 */
public class HelloServerHandler extends ChannelInboundHandlerAdapter{
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("RemoteAddress: "+ctx.channel().remoteAddress()+" active");
        //here, we make the message is handled start from this handler
        //you can use channel or pipeline to write.
        ctx.writeAndFlush("welcome to " + InetAddress.getLocalHost().getHostName() + " service");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + " say: " + (String)msg);
        /*you can use channel to write, the message would go through pipeline
        Channel channel = ctx.channel();
        channel.writeAndFlush("Receive your message: "+msg);
        */
        /*you can use pipeline to write, the message would go through pipeline too
        ChannelPipeline pipeline = ctx.pipeline();
        pipeline.writeAndFlush("Received your message, "+msg);
      */
        ctx.writeAndFlush("Received your message: "+msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
