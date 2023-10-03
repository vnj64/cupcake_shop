package com.example.steamkey.common;

public class ProductDTO {
    private Long id;
    private String area;
    private String description;
    private Integer price;
    private String steamKey;
    private String title;
    private Integer user_id;
    public Object getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setArea(String area) {
        this.area = area;
    }



    public String getArea() {
        return area;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getKey() {
        return steamKey;
    }
    public void setKey(String steamKey) {
        this.steamKey = KeyGenerator.generateKey();
    }



}
