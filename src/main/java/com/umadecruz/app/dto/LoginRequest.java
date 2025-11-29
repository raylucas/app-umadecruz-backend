package com.umadecruz.app.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private String email;
    private String password;


}
