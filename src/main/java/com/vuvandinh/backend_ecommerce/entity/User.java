package com.vuvandinh.backend_ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String address;
    @Column(nullable =false)
    private String email;
    @Column(nullable =false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @Column(name = "created_at",nullable = true)
    private Date createdAt;
    @Column(name = "updated_at",nullable = true)
    private Date updatedAt;
    @Column(nullable =false)
    private String phone;
    @Column(nullable = false)
    private Integer status;
    @Column(nullable = true, columnDefinition = "DOUBLE DEFAULT 0.0")
    private Double totalMoney;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Order> order;
    @ManyToOne
    private Role role;
}
