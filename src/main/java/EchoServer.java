import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by Wenjian on 2016/4/20, 0020.
 */
public class EchoServer {
    private static final int port = 8080;

    public static void main(String[] args){
        //configure the server;
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            //create bootstrap
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            //add event loop group and channel, and then is the child handler(for managing acceptted connections)
            serverBootstrap.group(bossGroup, workerGroup);
            serverBootstrap.channel(NioServerSocketChannel.class);
            serverBootstrap.childHandler(new HelloServerInitializer());

            //bind to correspoding port, Future is an async framework
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            channelFuture.channel().closeFuture().sync();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
