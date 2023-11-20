package com.springboot.bookstore.repository;

import com.springboot.bookstore.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<Users,Integer> {
    Optional<Users> findByName(String username);
}
