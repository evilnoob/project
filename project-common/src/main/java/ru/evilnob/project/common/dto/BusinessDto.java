package ru.evilnob.project.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BusinessDto {

    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
}
