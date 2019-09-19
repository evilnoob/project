package ru.evilnoob.project.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.evilnob.project.common.dto.BusinessDto;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto extends BusinessDto {

    private Long userId;
    private Long login;
    private Long password;
    private Boolean enabled;
}
