package com.wadlab.academy_bank.dto;

import com.wadlab.academy_bank.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class updateUserDTO {
    int id;
    UserRequest userRequest;
}