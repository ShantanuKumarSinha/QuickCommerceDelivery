package com.shann.quickcommerce.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class Product extends BaseModel {
    private String name;
    private String description;
    private double price;
    private String category;
    private String imageUrl;

}
