package com.brangelov.lunchy.service;

import com.brangelov.lunchy.entity.Restaurant;
import com.brangelov.lunchy.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private RestaurantRepository repository;

    @Override
    public Page<Restaurant> get(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Optional<Restaurant> get(long id) {
        return Optional.ofNullable(repository.getOne(id));
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        return repository.save(restaurant);
    }

    @Override
    public Optional<Restaurant> remove(long id) {
        Optional<Restaurant> optional = get(id);
        optional.ifPresent(r -> repository.delete(r));
        return optional;
    }

    @Autowired
    public void setRepository(RestaurantRepository repository) {
        this.repository = repository;
    }
}
