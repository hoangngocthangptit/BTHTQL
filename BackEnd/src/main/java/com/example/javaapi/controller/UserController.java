package com.example.javaapi.controller;

import com.example.javaapi.dto.UserDto;
import com.example.javaapi.entity.Users;
import com.example.javaapi.repository.UserRepository;
import com.example.javaapi.response.Response;
import com.example.javaapi.response.UsersDetailRes;
import com.example.javaapi.service.UserServices;
import com.example.javaapi.until.JwtGenerator;
import com.example.javaapi.until.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.python.util.PythonInterpreter;

import java.util.List;
import java.util.Optional;
@CrossOrigin("*")
@RestController
public class UserController {
    @Autowired
    private UserRepository repository;
    @Autowired
    private UserServices userServices;
    @Autowired
    private BCryptPasswordEncoder encryption;
    @Autowired
    private JwtGenerator generate;
    @PostMapping("/registration")
    @ResponseBody
    public ResponseEntity<Response> registration(@RequestBody UserDto information) {
        System.out.println("user info" + information.toString());
        boolean result = userServices.register(information);
        if (result) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new Response("registration successfull", 200, information));
        } else {
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED)
                    .body(new Response("User Already Exist", 400, information));
        }
    }


    @PostMapping("/user/login")
    public ResponseEntity<UsersDetailRes> login(@RequestBody UserDto information) {
        Users users = userServices.login(information);
        if (users != null&& users.isVerified()==true) {
            String token = generate.jwtToken(users.getUserId());
            return ResponseEntity.status(HttpStatus.ACCEPTED).header("login successfull", information.getEmail())
                    .body(new UsersDetailRes(token, 200, users));
        } else {
            throw new UserException(" Invalide credentials");
        }
    }
    @GetMapping("DeleteUser/{userId}")
    public ResponseEntity<Response> deleteUser(@PathVariable long userId) {
        Optional<Users> user = repository.findById(userId);
        repository.deleteById(userId);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(new Response("delete", 200, user));
    }
    @GetMapping("isVerified/{userId}")
    public ResponseEntity<Response> activeUser(@PathVariable long userId) {
        Users user = repository.findById(userId).get();
        if(user.isVerified()==false) {
            user.setVerified(true);
        }
        else {
            user.setVerified(false);
        }
        repository.save(user);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(new Response("set isVerified", 200, user));
    }
    @GetMapping("User/getAll")
    public ResponseEntity<Response> getAll() {
        List<Users> users = (List<Users>) repository.findAll();
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(new Response("set isVerified", 200, users));
    }
    @PutMapping("updatePassword/{id}")
    public ResponseEntity<Response> updatePassword(@PathVariable Long id,@RequestBody UserDto info){
        Users users=repository.findById(id).get();
        String epassword = encryption.encode(info.getPassword());
        users.setPassword(epassword);
        repository.save(users);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(new Response("successfull", 200, users));
    }
    @GetMapping("DetailUser/{userId}")
    public ResponseEntity<Response> detailUser(@PathVariable long userId) {
        Users user = repository.findById(userId).get();
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(new Response("detail", 200, user));
    }
    @PutMapping("updateInfo")
    public ResponseEntity<Response> updateInfo(@RequestBody UserDto info){
        //ko đổi đc info email
        Users user = repository.findByEmail(info.getEmail());
        user.setName(info.getName());
        user.setMobileNumber(info.getMobileNumber());
        repository.save(user);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(new Response("update", 200, user));
    }




}
