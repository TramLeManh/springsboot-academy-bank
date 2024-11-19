package com.wadlab.academy_bank.utils;

import java.time.Year;


public class AccountUtilis {

    public static final String ACCOUNT_EXISTS_CODE = "001";
    public static final String ACCOUNT_EXISTS_MESSAGE = "This user already exists!";
    public static final String ACCOUNT_CREATION_SUCCESS_CODE = "002";
    public static final String ACCOUNT_CREATION_SUCCESS_MASSAGE = "Account has been successfully created";
    public static final String ACCOUNT_NOT_FOUND_CODE = "002";
    public static final String ACCOUNT_NOT_FOUND_MESSAGE = "Account not found";
    public static final String ACCOUNT_FIND_SUCCESS_CODE ="003";
    public static final String ACCOUNT_DELETE_SUCCESS_CODE = "004"; ;
    public static final String ACCOUNT_DELETE_SUCCESS_MASSAGE = "Account has been successfully deleted";
    public static final String ACCOUNT_UPDATE_SUCCESS_CODE = "005";
    public static final String ACCOUNT_UPDATE_SUCCESS_MASSAGE = "Account has been successfully updated";
    ;
    public static String ACCOUNT_FIND_SUCCESS_Message = "Account found successfully";

    public static String generateAccountNumber() {


        // 2023 + randonSixDigits
        Year currentYear = Year.now();

        int min = 100000;
        int max = 999999;

        int randNumber = (int) Math.floor(Math.random() * (max - min + 1));

        String year = String.valueOf(currentYear);
        String randomNumber = String.valueOf(randNumber);
        StringBuilder accountNumber = new StringBuilder();

        return accountNumber
                .append(year)
                .append(randomNumber)
                .toString();
    }

}