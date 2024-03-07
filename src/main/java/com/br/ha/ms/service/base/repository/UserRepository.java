package com.br.ha.ms.service.base.repository;

import com.br.ha.ms.service.base.model.UserEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository implements JpaRepository<UserEntity, Long>{
        }