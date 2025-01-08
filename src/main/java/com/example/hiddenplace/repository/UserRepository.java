package com.example.hiddenplace.repository;

import com.example.hiddenplace.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

//User, Long 을 해주면서 ddl설정에 따라 자동으로 테이블 생성
public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsByUsername(String username);
}
