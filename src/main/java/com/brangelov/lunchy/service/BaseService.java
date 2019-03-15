package com.brangelov.lunchy.service;

import com.brangelov.lunchy.entity.Identity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BaseService<T extends Identity> {
    Page<T> get(Pageable pageable);
    Optional<T> get(long id);
    T save(T entity);
    Optional<T> remove(long id);
}
