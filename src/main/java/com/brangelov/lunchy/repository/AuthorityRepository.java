package com.brangelov.lunchy.repository;

import com.brangelov.lunchy.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
