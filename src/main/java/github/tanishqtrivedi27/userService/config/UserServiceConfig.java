package github.tanishqtrivedi27.userService.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import github.tanishqtrivedi27.userService.deserializer.UserInfoDeserializer;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.redisson.api.RedissonClient;
import org.redisson.Redisson;


@Configuration
public class UserServiceConfig {
    @Bean
    public ObjectMapper objectMapperInit() {
        return new ObjectMapper();
    }

    @Bean
    public UserInfoDeserializer userInfoDeserializer() {
        return new UserInfoDeserializer();
    }

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer().setAddress("${spring.data.redis.url}");
        return Redisson.create(config);
    }
}
