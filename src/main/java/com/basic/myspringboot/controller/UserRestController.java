package com.basic.myspringboot.controller;

import com.basic.myspringboot.entity.User;
import com.basic.myspringboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor //final 변수 자동 초기화 -> 생성자 자동 생성
@RequestMapping("/api/users")
public class UserRestController {
    private final UserRepository userRepository;


    //constructor Injection
//    public UserRestController(UserRepository userRepository) {
//        System.out.println("userRestController : " + userRepository.getClass().getName());
//        this.userRepository = userRepository;
//    }

    @PostMapping
    public User create(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> optionalUser = userRepository.findById(id);

        ResponseEntity<User> responseEntity = optionalUser
                .map(user -> ResponseEntity.ok(user))
                .orElse(ResponseEntity.notFound().build());

        return responseEntity;

//        return optionalUser.map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());

    }





}
