package com.example.ss13_demo.model.entity;

public class Product {
    private Integer productId;
    private String productName;
    private Float price;
    private Category category;
    private String image;

    public Product() {
    }

    public Product(Integer productId, String productName, Float price, Category category, String image) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.category = category;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
