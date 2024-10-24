package github.tanishqtrivedi27.userService.consumer;

import github.tanishqtrivedi27.userService.models.UserInfoDTO;
import github.tanishqtrivedi27.userService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import github.tanishqtrivedi27.userService.services.DistributedLockService;

@Service
public class AuthServiceConsumer {
    private final UserService userService;
    private final DistributedLockService lockService;

    @Autowired
    public AuthServiceConsumer(UserService userService, DistributedLockService lockService) {
        this.userService = userService;
        this.lockService = lockService;
    }

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(UserInfoDTO userInfoDTO) {
        String lockKey = "user-processing:" + userInfoDTO.getUserId();
        try {
            if (lockService.acquireLock(lockKey)) {
                try {
                    userService.createOrUpdateUser(userInfoDTO);
                } finally {
                    lockService.releaseLock(lockKey);
                }
            } else {
                System.out.println("Failed to acquire lock for user " +  userInfoDTO.getUserId());
            }
        } catch (Exception e) {
            System.out.println("Error processing user " +  userInfoDTO.getUserId() + e);
            throw new RuntimeException(e);
        }
    }
}
