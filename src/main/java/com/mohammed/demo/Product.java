package com.mohammed.demo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must be less than 100 characters")
    @Column(nullable = false)
    private String name;

    @Positive(message = "Price must be positive")
    @Column(nullable = false)
    private double price;

    // Many Products belong to One Category
    // زي public Category Category في .NET
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id") // اسم الـ Foreign Key في الـ Database
    private Category category;

    public Product() {}

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public Category getCategory() { return category; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
    public void setCategory(Category category) { this.category = category; }
}