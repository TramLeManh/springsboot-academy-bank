package com.wadlab.academy_bank.services.impl;

import com.wadlab.academy_bank.dto.AccountInfo;
import com.wadlab.academy_bank.dto.BankResponse;
import com.wadlab.academy_bank.dto.UserDTO;
import com.wadlab.academy_bank.dto.UserRequest;
import com.wadlab.academy_bank.entity.User;

import java.util.AbstractCollection;
import java.util.List;

public interface UserService {
    public BankResponse<AccountInfo>  createAccount(UserRequest userRequest);
    public BankResponse<List<UserDTO>> getAllAccount();
    public BankResponse<User> getAccountById(long id);
    public BankResponse<User> updateAccount(long id, UserRequest userRequest);
    public BankResponse<AccountInfo>   deleteAccount(long id);



}