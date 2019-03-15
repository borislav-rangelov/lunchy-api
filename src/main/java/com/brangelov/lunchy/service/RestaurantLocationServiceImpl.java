package com.brangelov.lunchy.service;

import com.brangelov.lunchy.entity.RestaurantLocation;
import com.brangelov.lunchy.repository.RestaurantLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RestaurantLocationServiceImpl implements RestaurantLocationService {

    private RestaurantLocationRepository repository;

    @Override
    public Page<RestaurantLocation> get(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Optional<RestaurantLocation> get(long id) {
        return repository.findById(id);
    }

    @Override
    public RestaurantLocation save(RestaurantLocation entity) {
        return repository.save(entity);
    }

    @Override
    public Optional<RestaurantLocation> remove(long id) {
        Optional<RestaurantLocation> optional = get(id);
        optional.ifPresent(l -> repository.delete(l));
        return optional;
    }

    @Override
    public Optional<RestaurantLocation> remove(long restaurantId, long id) {
        Optional<RestaurantLocation> location = Optional.ofNullable(
                repository.findByRestaurantIdAndId(restaurantId, id)
        );
        location.ifPresent(l -> repository.delete(l));
        return location;
    }

    @Autowired
    public void setRepository(RestaurantLocationRepository repository) {
        this.repository = repository;
    }

}
