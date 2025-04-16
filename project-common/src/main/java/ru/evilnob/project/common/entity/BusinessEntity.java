package ru.evilnob.project.common.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@Entity
public class BusinessEntity {

    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

}
