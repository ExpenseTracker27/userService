package github.tanishqtrivedi27.userService.services;

import github.tanishqtrivedi27.userService.entities.UserInfo;
import github.tanishqtrivedi27.userService.models.UserInfoDTO;
import github.tanishqtrivedi27.userService.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    private UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserInfoDTO createOrUpdateUser(UserInfoDTO userInfoDTO) {
        UnaryOperator<UserInfo> updateUser = user -> userRepository.save(userInfoDTO.transform());

        Supplier<UserInfo> createUser = () -> userRepository.save(userInfoDTO.transform());

        UserInfo userInfo = userRepository.findByUserId(userInfoDTO.getUserId())
                .map(updateUser)
                .orElseGet(createUser);

        return new UserInfoDTO(userInfo.getUserId(), userInfo.getFirstName(), userInfo.getLastName(), userInfo.getPhoneNumber(), userInfo.getEmail());
    }

    public UserInfoDTO getUser(UserInfoDTO userInfoDTO) throws Exception {
        Optional<UserInfo> userInfoOptional = userRepository.findByUserId(userInfoDTO.getUserId());
        if (userInfoOptional.isEmpty()) {
            throw new Exception("User not found");
        }

        UserInfo userInfo = userInfoOptional.get();
        return new UserInfoDTO(userInfo.getUserId(), userInfo.getFirstName(), userInfo.getLastName(), userInfo.getPhoneNumber(), userInfo.getEmail());
    }
}
