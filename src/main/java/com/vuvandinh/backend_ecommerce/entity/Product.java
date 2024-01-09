package com.vuvandinh.backend_ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Products")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Product {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String slug;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String detail;
    @Column(nullable = false)
    private Integer status;
    @Column(nullable = false)
    private Integer feature;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at",nullable = true)
    private Date createdAt;
    @Column(name = "updated_at",nullable = true)
    private Date updatedAt;
    @Column(nullable = false)
    private Double price;
    @ManyToOne
    @JsonIgnoreProperties({"category"})
    @JoinColumn(name = "category_id",nullable = false)
    private Category category;
    @ManyToOne
    @JsonIgnoreProperties({"brand"})
    @JoinColumn(name = "brand_id",nullable = false)
    private Brand brand;
    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }
//    @OneToMany(mappedBy = "product")
//    @JsonIgnoreProperties({"order_details"})
//    private List<Order_details> order_details;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductImage> images;

}
