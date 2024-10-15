package github.tanishqtrivedi27.userService.controllers;

import github.tanishqtrivedi27.userService.models.UserInfoDTO;
import github.tanishqtrivedi27.userService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("user/v1/user")
    public ResponseEntity<UserInfoDTO> createUpdateUser(@RequestBody UserInfoDTO userInfoDTO) {
        try {
            UserInfoDTO user = userService.createOrUpdateUser(userInfoDTO);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("user/v1/user")
    public ResponseEntity<UserInfoDTO> getUser(@RequestBody UserInfoDTO userInfoDTO) {
        try {
            UserInfoDTO user = userService.getUser(userInfoDTO);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
