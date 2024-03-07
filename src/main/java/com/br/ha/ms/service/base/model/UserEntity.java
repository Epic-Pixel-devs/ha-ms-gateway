package com.br.ha.ms.service.base.model;

import lombok.Getter;
import lombok.Setter;

@Table('TB_USERS')
@Getter
@Setter
public class UserEntity {

    @Id
    @Generate()
    private Long Id;

    @Column(name = 'name')
    private String name;
}
