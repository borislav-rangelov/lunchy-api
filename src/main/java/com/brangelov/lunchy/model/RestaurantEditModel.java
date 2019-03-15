package com.brangelov.lunchy.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class RestaurantEditModel {

    @NotEmpty
    @Size(min = 3)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
