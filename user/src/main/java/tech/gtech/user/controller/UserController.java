package tech.gtech.user.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.gtech.user.domain.UserModel;
import tech.gtech.user.dto.UserDto;
import tech.gtech.user.service.UserService;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController implements UserControllerInterface{
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

    @GetMapping("/{id}")
    public ResponseEntity<UserModel> getUserById(@PathVariable UUID id) {
        UserModel userModel = userService.getUserById(id);
        if (userModel == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userModel);
    }

    @GetMapping
    public ResponseEntity<Iterable<UserModel>> getAllUsers() {
        Iterable<UserModel> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping
    public ResponseEntity<UserModel> updateUser(@RequestBody UserDto user) {
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(user, userModel); // quebra com testes
        userModel = userService.updateUser(userModel);
        if (userModel == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userModel);
    }
}