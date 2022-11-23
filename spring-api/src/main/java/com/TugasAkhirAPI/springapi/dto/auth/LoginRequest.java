package com.TugasAkhirAPI.springapi.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class LoginRequest {
    String username;
    String password;
}
