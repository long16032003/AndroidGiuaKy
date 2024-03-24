package com.example.qlbanhoa.Entity;

public class Product {
    private String description;
    private int id;
    private String image;
    private String name;
    private long price;

    public Product() {
    }

    public Product(String description, int id, String image, String name, long price) {
        this.description = description;
        this.id = id;
        this.image = image;
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
