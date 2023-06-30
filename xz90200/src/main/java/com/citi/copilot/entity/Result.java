package com.citi.copilot.entity;

import lombok.Data;

@Data
public class Result {
    private RegistrationEntity token;
    private String status;
}
