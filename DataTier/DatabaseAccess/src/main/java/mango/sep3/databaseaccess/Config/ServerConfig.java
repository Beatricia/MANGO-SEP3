package mango.sep3.databaseaccess.Config;

import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder;
import mango.sep3.databaseaccess.FileData.FileContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import service.FarmServiceImpl;
import service.UserServiceImpl;

@Configuration
public class ServerConfig {
    @Bean public FileContext getContext(){
        return new FileContext();
    }

    @Bean public FarmServiceImpl getFarmServiceImpl(){
        return new FarmServiceImpl();
    }

    @Bean public UserServiceImpl getUserServiceImpl(){
        return new UserServiceImpl();
    }
}
