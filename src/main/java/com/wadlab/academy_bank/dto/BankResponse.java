package com.wadlab.academy_bank.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankResponse {
    private String responeCode;
    private String responeMessage;
    private AccountInfo AccountInfo;
}
