package com.adslagalo.dto;

import com.adslagalo.entity.Role;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterResponse {

    private Long id;
    private String name;
    private String email;
    private Role role;
}