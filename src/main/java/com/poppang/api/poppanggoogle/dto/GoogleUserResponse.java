package com.poppang.api.poppanggoogle.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GoogleUserResponse {
    private String uid;
    private String email;
}