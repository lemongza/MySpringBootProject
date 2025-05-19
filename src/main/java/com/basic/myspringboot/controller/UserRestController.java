package com.basic.myspringboot.controller;

import com.basic.myspringboot.entity.User;
import com.basic.myspringboot.exception.BusinessException;
import com.basic.myspringboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    //목록조회
    @GetMapping
    public List<User> getUsers() {
        return userRepository.findAll();
    }
    //id로 조회
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> optionalUser = userRepository.findById(id);

        ResponseEntity<User> responseEntity = optionalUser
                .map(user -> ResponseEntity.ok(user))
                .orElse(new ResponseEntity("User Not Found", HttpStatus.NOT_FOUND));

        return responseEntity;

//        return optionalUser.map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());

    }

    //email로 조회
    @GetMapping("/email/{email}/")
    public User getUserByEmail(@PathVariable String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        User existUser =
                optionalUser.orElseThrow(()-> new BusinessException("User Not Found", HttpStatus.NOT_FOUND));
        return existUser;
    }


    //수정 *부분수정 (PATCH)
    @PatchMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetail) {
        User existUser = getExistUser(userRepository.findById(id));
        //setter method 호출
        existUser.setName(userDetail.getName());
        User updatedUser = userRepository.save(existUser);
        return ResponseEntity.ok(updatedUser);
//        return ResponseEntity.ok(userRepository.save(existUser));
    }

    private User getExistUser(Optional<User> optionalUser) {
        User existUser = optionalUser
                .orElseThrow(() -> new BusinessException("User Not Found", HttpStatus.NOT_FOUND));
        return existUser;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        User user = getExistUser(userRepository.findById(id));
        userRepository.delete(user);
        return ResponseEntity.ok("User가 삭제 되었습니다!"); //status code 200
        //return ResponseEntity.noContent().build();  //status code 204
    }





}
