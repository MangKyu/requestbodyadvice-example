package com.mangkyu.requestbody.app;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @PostMapping("/users")
    public ResponseEntity<User> users(User user) {
        return ResponseEntity.ok(user);
    }

}
