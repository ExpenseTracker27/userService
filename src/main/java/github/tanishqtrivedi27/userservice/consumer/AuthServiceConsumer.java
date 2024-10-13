package github.tanishqtrivedi27.userservice.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import github.tanishqtrivedi27.userservice.entities.UserInfoDTO;
import github.tanishqtrivedi27.userservice.services.UserService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

public class AuthServiceConsumer {
    private UserService userService;
    private ObjectMapper objectMapper;

    @Autowired
    public AuthServiceConsumer(UserService userRepository) {
        this.userService = userRepository;
        this.objectMapper = new ObjectMapper();
    }

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(UserInfoDTO userInfoDTO) {
        try {
            // Todo: Make it transactional, to handle idempotency and validate email, phoneNumber etc
            userService.createOrUpdateUser(userInfoDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
