package com.vuvandinh.backend_ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@Table(name = "Categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String slug;
    @Column(nullable = false)
    private String description;
    @Column(nullable =false)
    private String image;
    @Column(nullable = false)
    private Integer status;
    @Column(name = "created_at",nullable = true)
    private Date createdAt;
    @Column(name = "updated_at",nullable = true)
    private Date updatedAt;

//    @OneToMany(mappedBy = "category")
//    @JsonIgnoreProperties({"category"})
//    private List<Product>products;
}
