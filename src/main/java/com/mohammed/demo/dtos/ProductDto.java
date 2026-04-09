package com.mohammed.demo.dtos;

import java.io.Serializable;
import java.util.Set;

public class ProductDto implements Serializable {

    private int id;
    private String name;
    private double price;
    private String categoryName;
    private Set<String> tags; // أسماء الـ Tags بس مش الـ Objects كلها

    public ProductDto() {}

    public ProductDto(int id, String name, double price, String categoryName, Set<String> tags) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.categoryName = categoryName;
        this.tags = tags;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getCategoryName() { return categoryName; }
    public Set<String> getTags() { return tags; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
    public void setTags(Set<String> tags) { this.tags = tags; }
}