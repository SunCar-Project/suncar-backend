package com.yangsunkue.suncar.repository.user;

import com.yangsunkue.suncar.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {


    Optional<User> findByUserId(String userId);
}
