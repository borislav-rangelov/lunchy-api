package com.brangelov.lunchy.repository;

import com.brangelov.lunchy.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    Page<User> findByName(@Param("name") String name, Pageable pageable);
    User findByEmail(@Param("email") String email);
}
