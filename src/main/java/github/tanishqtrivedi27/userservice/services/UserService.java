package github.tanishqtrivedi27.userservice.services;

import github.tanishqtrivedi27.userservice.entities.UserInfoDTO;
import github.tanishqtrivedi27.userservice.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    private UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserInfoDTO createOrUpdateUser(UserInfoDTO userInfoDTO) {}
}
