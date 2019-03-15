package com.brangelov.lunchy.repository;

import com.brangelov.lunchy.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmailIgnoreCase(@Param("email") String email);
}
