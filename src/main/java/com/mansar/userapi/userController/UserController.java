package com.mansar.userapi.userController;


import com.mansar.userapi.model.user;
import com.mansar.userapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1")

public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/admin")
    public List<user> createStarter()
    {
        user us = new user();
        us.setCreatedAt(new Date());
        us.setCreatedBy("admin");
        us.setEmailId("mansar@gmail.com");
        us.setFirstName("abdeddaim");
        us.setLastName("mansar");
        userRepository.save(us);
        return getAllUsers();

    }
    @GetMapping("/users")
    public List<user> getAllUsers(){return userRepository.findAll();}

    @GetMapping("/users/{id}")
    public ResponseEntity<Optional<user>> getUserById(@PathVariable(value = "id") Long userId){
        Optional<user> user = userRepository.findById(userId);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/users")
    public user createUser(@Validated @RequestBody user user) {
        return userRepository.save(user);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<user> updateUser(
            @PathVariable(value = "id") Long idUser,
            @Validated @RequestBody user userDetail
    ){

        user user =  userRepository.findById(idUser).orElseThrow(()-> new RuntimeException());
        user.setEmailId(userDetail.getEmailId());
        user.setFirstName(userDetail.getFirstName());
        user.setLastName(userDetail.getLastName());
        user.setCreatedAt(userDetail.getCreatedAt());
        final  user upadteduser = userRepository.save(user);
        return ResponseEntity.ok(upadteduser);
    }

    @DeleteMapping("user/{id}")
    public Map<String, Boolean> deleteUser(
            @PathVariable(value = "id") long userId){
        user user =  userRepository.findById(userId).orElseThrow(()-> new RuntimeException());
        userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;

    }

}
