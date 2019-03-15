package com.brangelov.lunchy.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class RestaurantLocation implements Identity {

    public RestaurantLocation() {
    }

    public RestaurantLocation(@NotEmpty String name, String locationString) {
        this.name = name;
        this.locationString = locationString;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty
    private String name;

    private String locationString;

    @ManyToOne(optional = false)
    private Restaurant restaurant;

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocationString() {
        return locationString;
    }

    public void setLocationString(String locationString) {
        this.locationString = locationString;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
