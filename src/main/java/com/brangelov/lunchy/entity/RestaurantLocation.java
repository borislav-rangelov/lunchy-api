package com.brangelov.lunchy.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "locations")
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

    @Column(nullable = false)
    private String name;

    @Column(length = 2500) // web browsers support around is 2000
    private String locationString;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurantId", nullable = false)
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
