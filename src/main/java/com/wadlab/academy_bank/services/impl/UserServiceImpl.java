package com.wadlab.academy_bank.services.impl;

import com.wadlab.academy_bank.dto.AccountInfo;
import com.wadlab.academy_bank.dto.BankResponse;
import com.wadlab.academy_bank.dto.UserDTO;
import com.wadlab.academy_bank.dto.UserRequest;
import com.wadlab.academy_bank.entity.User;
import com.wadlab.academy_bank.repository.UserRepository;
import com.wadlab.academy_bank.utils.AccountUtilis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public BankResponse<AccountInfo> createAccount(UserRequest userRequest) {

        if (userRepository.existsByEmail(userRequest.getEmail())) {
            return BankResponse.<AccountInfo>builder()
                    .responeCode(AccountUtilis.ACCOUNT_EXISTS_CODE)
                    .responeCode(AccountUtilis.ACCOUNT_EXISTS_MESSAGE)
                    .data(null)
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

        return BankResponse.<AccountInfo>builder()
                .responeCode(AccountUtilis.ACCOUNT_CREATION_SUCCESS_CODE)
                .responeMessage(AccountUtilis.ACCOUNT_CREATION_SUCCESS_MASSAGE)
                .data(accountInfo)  // Corrected field name from AccountInfo to data
                .build();

    }

    @Override
    public BankResponse<List<UserDTO>> getAllAccount() {
        List<UserDTO> users=  userRepository.findAll().stream()
                .map(user -> UserDTO.builder()
                        .fullName(user.getFirstName() + " " + user.getLastName())
                        .user_id(user.getId())
                        .build())
                .collect(Collectors.toList());
        return BankResponse.<List<UserDTO>>builder()
                .responeCode(AccountUtilis.ACCOUNT_CREATION_SUCCESS_CODE)
                .responeMessage(AccountUtilis.ACCOUNT_CREATION_SUCCESS_MASSAGE)
                .data(users)  // Corrected field name from AccountInfo to data
                .build();
    }

    @Override
    public BankResponse<User> getAccountById(long id) {
        User user = userRepository.findById(id).orElse(null);
        if(user == null){
            return BankResponse.<User>builder()
                    .responeCode(AccountUtilis.ACCOUNT_NOT_FOUND_CODE)
                    .responeMessage(AccountUtilis.ACCOUNT_NOT_FOUND_MESSAGE)
                    .data(null)
                    .build();
        }
        return BankResponse.<User>builder()
                .responeCode(AccountUtilis.ACCOUNT_FIND_SUCCESS_CODE)
                .responeMessage(AccountUtilis.ACCOUNT_FIND_SUCCESS_Message)
                .data(user)  // Corrected field name from AccountInfo to data
                .build();
    }

    @Override
    public BankResponse<User> updateAccount(long id, UserRequest userRequest) {
        User user = userRepository.findById(id).orElse(null);
        if(user == null){
            return BankResponse.<User>builder()
                    .responeCode(AccountUtilis.ACCOUNT_NOT_FOUND_CODE)
                    .responeMessage(AccountUtilis.ACCOUNT_NOT_FOUND_MESSAGE)
                    .data(null)
                    .build();
        }
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setOtherName(userRequest.getOtherName());
        user.setAddress(userRequest.getAddress());
        user.setSateOfOrigin(userRequest.getSateOfOrigin());
        user.setPhoneNumber(userRequest.getPhoneNumber());
        user.setAlternativePhoneNumber(userRequest.getAlternativePhoneNumber());
        user.setEmail(userRequest.getEmail());
        User updatedUser = userRepository.save(user);
        return BankResponse.<User>builder()
                .responeCode(AccountUtilis.ACCOUNT_UPDATE_SUCCESS_CODE)
                .responeMessage(AccountUtilis.ACCOUNT_UPDATE_SUCCESS_MASSAGE)
                .data(updatedUser)  // Corrected field name from AccountInfo to data
                .build();
    }

    @Override
    public BankResponse<AccountInfo> deleteAccount(long id) {
        User user = userRepository.findById(id).orElse(null);
        if(user == null){
            return BankResponse.<AccountInfo>builder()
                    .responeCode(AccountUtilis.ACCOUNT_NOT_FOUND_CODE)
                    .responeMessage(AccountUtilis.ACCOUNT_NOT_FOUND_MESSAGE)
                    .data(null)
                    .build();
        }
        userRepository.delete(user);
        return BankResponse.<AccountInfo>builder()
                .responeCode(AccountUtilis.ACCOUNT_DELETE_SUCCESS_CODE)
                .responeMessage(AccountUtilis.ACCOUNT_DELETE_SUCCESS_MASSAGE)
                .data(null)  // Corrected field name from AccountInfo to data
                .build();

    }


}