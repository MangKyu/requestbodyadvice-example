package com.mangkyu.requestbody.app;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {

    @PostMapping("/users")
    public ResponseEntity<User> users(@RequestBody @Valid User user) {
        return ResponseEntity.ok(user);
    }

}
