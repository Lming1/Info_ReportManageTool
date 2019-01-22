package com.example.demo.Model;


import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private BigDecimal price;
    private Integer quantity;
    @Column(name = "category_name")
    private String categoryName;
    @Column(name = "category_code")
    private Integer categoryCode;
}
