package com.brangelov.lunchy.repository;

import com.brangelov.lunchy.entity.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Page<Restaurant> findByNameContainsIgnoreCase(@Param("name") String name, Pageable pageable);
    <T> T findProjectedById(long id, Class<T> tClass);
}
