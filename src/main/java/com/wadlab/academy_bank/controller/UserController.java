package com.wadlab.academy_bank.controller;

import com.wadlab.academy_bank.dto.*;
import com.wadlab.academy_bank.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.wadlab.academy_bank.services.impl.UserService;

import java.util.List;


@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public BankResponse<AccountInfo> createAccount(@RequestBody UserRequest userRequest) {
        return userService.createAccount(userRequest);
    }
    @RequestMapping("/users")
    @GetMapping
    public BankResponse<List<UserDTO>> getAccount() {
        return userService.getAllAccount();
    }
    @PutMapping
    public BankResponse<User> updateAccount(@RequestBody updateUserDTO userRequest) {
        return userService.updateAccount(userRequest.getId(), userRequest.getUserRequest());
    }
    @DeleteMapping("/user/{id}")
    public BankResponse<AccountInfo> deleteAccount(@PathVariable long id) {
        return userService.deleteAccount(id);
    }
}