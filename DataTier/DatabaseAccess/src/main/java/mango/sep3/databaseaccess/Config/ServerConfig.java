package mango.sep3.databaseaccess.Config;

import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder;
import mango.sep3.databaseaccess.utils.GrpcConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import service.*;

@Configuration
public class ServerConfig {

    @Bean public FarmServiceImpl getFarmServiceImpl(){
        return new FarmServiceImpl();
    }

    @Bean public UserServiceImpl getUserServiceImpl(){
        return new UserServiceImpl();
    }

    @Bean public OrderServiceImpl getOrderServiceImpl(){
        return new OrderServiceImpl();
    }

    @Bean public CartOfferServiceImpl getCartOfferServiceImpl(){
        return new CartOfferServiceImpl();
    }

    @Bean public OfferServiceImpl getOfferServiceImpl(){
        return new OfferServiceImpl();
    }

    @Bean public NotificationServiceImpl getNotificationServiceImpl(){
        return new NotificationServiceImpl();
    }

    @Bean public GrpcConverter getGrpcConverter(){
        return new GrpcConverter();
    }
}
