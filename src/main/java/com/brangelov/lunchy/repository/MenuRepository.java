package com.brangelov.lunchy.repository;

import com.brangelov.lunchy.entity.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    Page<Menu> findByRestaurantId(long restaurantId, Pageable pageable);
    Menu findByRestaurantIdAndId(long restaurantId, long id);
}
