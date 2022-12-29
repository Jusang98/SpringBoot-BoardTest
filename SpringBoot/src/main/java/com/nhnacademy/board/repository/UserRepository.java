package com.nhnacademy.board.repository;


import com.nhnacademy.board.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByNameAndPwd(String name, String pwd);

    User findByName(String name);
}
