package com.TugasAkhir.spring.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class RegisterRequest {
    String name;
    String username;
    String password;
    String email;
    Long age;
}
