package com.wadlab.academy_bank.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankResponse<T> {
    private String responeCode;
    private String responeMessage;
    private T data;
}