package com.brangelov.lunchy.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class MenuItemEditModel {

    @NotEmpty
    @Size(min = 3, max = 255)
    private String name;

    @Size(max = 6000)
    private String description;

    @Min(value = 0)
    private int price;

    @Min(value = 0)
    private int grams;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getGrams() {
        return grams;
    }

    public void setGrams(int grams) {
        this.grams = grams;
    }
}
