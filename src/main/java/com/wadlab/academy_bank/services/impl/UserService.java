package com.wadlab.academy_bank.services.impl;

import com.wadlab.academy_bank.dto.BankResponse;
import com.wadlab.academy_bank.dto.UserRequest;

public interface UserService {
    public BankResponse createAccount(UserRequest userRequest);
}
