package com.yangsunkue.suncar.repository.user;

import com.yangsunkue.suncar.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {
}
