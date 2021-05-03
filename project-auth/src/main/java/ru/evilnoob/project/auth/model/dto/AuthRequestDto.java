package ru.evilnoob.project.auth.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthRequestDto {

    private String login;
    private String password;
}
