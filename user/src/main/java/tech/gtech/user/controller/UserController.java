package tech.gtech.user.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.gtech.user.domain.UserModel;
import tech.gtech.user.dto.UserDto;
import tech.gtech.user.service.UserService;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserModel> createUser(@RequestBody UserDto user) {
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(user, userModel); // quebra com testes
        userModel = userService.createUser(userModel);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(userModel.getUserId()).toUri();
        return ResponseEntity.created(uri).body(userModel);

    }

}
