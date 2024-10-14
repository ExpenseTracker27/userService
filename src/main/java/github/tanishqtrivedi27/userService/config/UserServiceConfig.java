package github.tanishqtrivedi27.userService.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import github.tanishqtrivedi27.userService.deserializer.UserInfoDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
}
