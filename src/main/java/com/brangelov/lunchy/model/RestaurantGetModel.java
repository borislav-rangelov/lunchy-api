package com.brangelov.lunchy.model;

import java.util.List;

public class RestaurantGetModel {
    private long id;
    private String name;
    private List<RestaurantLocationGetModel> locations;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RestaurantLocationGetModel> getLocations() {
        return locations;
    }

    public void setLocations(List<RestaurantLocationGetModel> locations) {
        this.locations = locations;
    }
}
