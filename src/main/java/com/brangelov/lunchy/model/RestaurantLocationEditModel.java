package com.brangelov.lunchy.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class RestaurantLocationEditModel {

    @NotEmpty
    @Size(min = 3, max = 250)
    private String name;

    @Size(max = 2500)
    private String locationString;

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
}
