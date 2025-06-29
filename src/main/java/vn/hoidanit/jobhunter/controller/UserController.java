package vn.hoidanit.jobhunter.controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import vn.hoidanit.jobhunter.domain.User;
import vn.hoidanit.jobhunter.service.*;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<User> createNewUser(@RequestBody User PostmanUser) {
        User user = this.userService.createUser(PostmanUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
        this.userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body("Delete successful with id :" + id);

    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.UserGetUserById(id));
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUser() {
        List<User> listUser = this.userService.getAllUser();
        return ResponseEntity.status(HttpStatus.OK).body(listUser);
    }

    @PutMapping("/users")
    public ResponseEntity<User> putUserById(@RequestBody User user) {
        User userUpdate = this.userService.updateUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(userUpdate);
    }

}
