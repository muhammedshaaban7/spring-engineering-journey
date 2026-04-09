package com.mohammed.demo.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.util.Set;

public class ProductRequest {

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must be less than 100 characters")
    private String name;

    @Positive(message = "Price must be positive")
    private double price;

    private int categoryId;
    private Set<Integer> tagIds; // IDs بتاعت الـ Tags

    public ProductRequest() {}

    // Getters
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getCategoryId() { return categoryId; }
    public Set<Integer> getTagIds() { return tagIds; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }
    public void setTagIds(Set<Integer> tagIds) { this.tagIds = tagIds; }
}