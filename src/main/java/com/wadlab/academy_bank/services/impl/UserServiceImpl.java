package com.wadlab.academy_bank.services.impl;

import com.wadlab.academy_bank.dto.AccountInfo;
import com.wadlab.academy_bank.dto.BankResponse;
import com.wadlab.academy_bank.dto.UserRequest;
import com.wadlab.academy_bank.entity.User;
import com.wadlab.academy_bank.repository.UserRepository;
import com.wadlab.academy_bank.utils.AccountUtilis;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public BankResponse createAccount(UserRequest userRequest) {

        if(userRepository.existsByEmail(userRequest.getEmail())) {
            return  BankResponse.builder()
                    .responeCode(AccountUtilis.ACCOUNT_EXISTS_CODE)
                    .responeCode(AccountUtilis.ACCOUNT_EXISTS_MESSAGE)
                    .AccountInfo(null)
                    .build();
        }

        User newUser = User.builder()
                        .firstName(userRequest.getFirstName())
                        .lastName(userRequest.getLastName())
                        .otherName(userRequest.getOtherName())
                        .address(userRequest.getAddress())
                        .sateOfOrigin(userRequest.getSateOfOrigin())
                        .accountNumber(AccountUtilis.generateAccountNumber())
                        .accountBalance(BigDecimal.ZERO)
                        .phoneNumber(userRequest.getPhoneNumber())
                        .alternativePhoneNumber(userRequest.getAlternativePhoneNumber())
                        .email(userRequest.getEmail())
                        .status("ACTIVE")
                        .build();

        User savedUser = userRepository.save(newUser);
        AccountInfo accountInfo = AccountInfo.builder()
                                .accountBalance(savedUser.getAccountBalance())
                                .accountNumber(savedUser.getAccountNumber())
                                .accountName(savedUser.getFirstName() + " " + savedUser.getLastName() + " " + savedUser.getOtherName())
                                .build();

        return BankResponse.builder()
                .responeCode(AccountUtilis.ACCOUNT_CREATION_SUCCESS_CODE)
                .responeMessage(AccountUtilis.ACCOUNT_CREATION_SUCCESS_MASSAGE)
                .AccountInfo(accountInfo)
                .build();
    }


}
