package com.example.datetimeforsubscriber.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class USSDrequestDto {

    private String msisdn;
    private String message;


}
