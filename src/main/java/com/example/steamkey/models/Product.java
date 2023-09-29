package com.example.steamkey.models;

import com.example.steamkey.common.KeyGenerator;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
import javax.validation.constraints.*;

@Entity
@Table(name = "products")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(min = 3, max = 20)
    private String title;
    @NotNull
    @Size(min = 3, max = 300)
    private String description;
    @NotNull(message = "Price is required")
    @DecimalMin(value = "0", inclusive = true, message = "Price must not be less than zero")
    private Integer price;
    private String area;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn
    private User user;
    private LocalDateTime dateOfCreated;
    private String steamKey = KeyGenerator.generateKey();

    @PrePersist
    private void onCreate() { dateOfCreated = LocalDateTime.now(); }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

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