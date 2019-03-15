package com.brangelov.lunchy.controller;

import com.brangelov.lunchy.entity.Restaurant;
import com.brangelov.lunchy.entity.RestaurantLocation;
import com.brangelov.lunchy.model.RestaurantLocationEditModel;
import com.brangelov.lunchy.model.RestaurantLocationGetModel;
import com.brangelov.lunchy.service.RestaurantLocationService;
import com.brangelov.lunchy.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/restaurants/{restaurantId}/locations")
public class RestaurantLocationController extends BaseController {

    private RestaurantService restaurantService;
    private RestaurantLocationService locationService;

    @GetMapping
    public HttpEntity getPage(@PathVariable long restaurantId) {

        Optional<Restaurant> optional = restaurantService.get(restaurantId);

        if (!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        List<RestaurantLocationGetModel> models = optional.get().getLocations()
                .stream()
                .map(l -> map(l, RestaurantLocationGetModel.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(models);
    }

    @GetMapping("/{id}")
    public HttpEntity getById(@PathVariable long restaurantId, @PathVariable long id) {

        Optional<Restaurant> optional = restaurantService.get(restaurantId);

        if (!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Optional<RestaurantLocation> location = optional.get().getLocations()
                .stream()
                .filter(l -> l.getId() == id).findFirst();

        if (!location.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(map(location.get(), RestaurantLocationGetModel.class));
    }

    @PostMapping
    public HttpEntity create(@PathVariable long restaurantId, @Valid @RequestBody RestaurantLocationEditModel model) {

        Optional<Restaurant> optional = restaurantService.get(restaurantId);

        if (!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Restaurant restaurant = optional.get();
        RestaurantLocation location = map(model, RestaurantLocation.class);
        location.setRestaurant(restaurant);
        location = locationService.save(location);

        return ResponseEntity.created(
                linkTo(methodOn(RestaurantLocationController.class).getById(restaurantId, location.getId())).toUri()
        ).body(map(location, RestaurantLocationGetModel.class));
    }

    @PutMapping("/{id}")
    public HttpEntity update(@PathVariable long restaurantId, @PathVariable long id,
                             @Valid @RequestBody RestaurantLocationEditModel model) {

        Optional<Restaurant> optional = restaurantService.get(restaurantId);

        if (!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Restaurant restaurant = optional.get();

        Optional<RestaurantLocation> first = restaurant.getLocations().stream()
                .filter(l -> l.getId() == id)
                .findFirst();

        if (!first.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        RestaurantLocation location = first.get();
        map(model, location);
        location = locationService.save(location);

        return ResponseEntity.ok(map(location, RestaurantLocationGetModel.class));
    }

    @DeleteMapping("/{id}")
    public HttpEntity delete(@PathVariable long restaurantId, @PathVariable long id) {

        Optional<RestaurantLocation> optional = locationService.remove(restaurantId, id);

        if (!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    @Autowired
    public void setLocationService(RestaurantLocationService locationService) {
        this.locationService = locationService;
    }

    @Autowired
    public void setRestaurantService(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }
}
