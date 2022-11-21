package com.TugasAkhir.spring.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class LoginResponse {
    String name;
    String email;
    String jwtToken;
}
