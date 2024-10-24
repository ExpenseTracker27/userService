package github.tanishqtrivedi27.userService.consumer;

import github.tanishqtrivedi27.userService.models.UserInfoDTO;
import github.tanishqtrivedi27.userService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceConsumer {
    private final UserService userService;

    @Autowired
    public AuthServiceConsumer(UserService userService) {
        this.userService = userService;
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
