package com.brangelov.lunchy.repository;

import com.brangelov.lunchy.entity.RestaurantLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantLocationRepository extends JpaRepository<RestaurantLocation, Long> {
}
