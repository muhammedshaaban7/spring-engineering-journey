package com.mohammed.demo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Category name is required")
    @Column(nullable = false)
    private String name;

    // One Category has Many Products
    // زي ICollection<Product> في .NET
    // FetchType.LAZY - مش هيجيب الـ Products إلا لما تطلبها
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> products;

    public Category() {}

    public Category(String name) {
        this.name = name;
    }

    // Getters & Setters
    public int getId() { return id; }
    public String getName() { return name; }
    public List<Product> getProducts() { return products; }
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setProducts(List<Product> products) { this.products = products; }
}