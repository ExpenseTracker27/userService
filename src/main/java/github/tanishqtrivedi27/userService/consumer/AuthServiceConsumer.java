package github.tanishqtrivedi27.userService.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import github.tanishqtrivedi27.userService.models.UserInfoDTO;
import github.tanishqtrivedi27.userService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceConsumer {
    private final UserService userService;
    private final ObjectMapper objectMapper;

    @Autowired
    public AuthServiceConsumer(UserService userService, ObjectMapper objectMapper) {
        this.userService = userService;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(UserInfoDTO userInfoDTO) {
        try {
            userService.createOrUpdateUser(userInfoDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
