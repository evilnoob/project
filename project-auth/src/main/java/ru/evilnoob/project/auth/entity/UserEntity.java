package ru.evilnoob.project.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.evilnob.project.common.entity.BusinessEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = "project", name = "user")
public class UserEntity extends BusinessEntity {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "login")
    private Long login;

    @Column(name = "password")
    private Long password;

    @Column(name = "enabled")
    private Boolean enabled;
}
