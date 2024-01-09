package com.vuvandinh.backend_ecommerce.entity;

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
@Table(name = "Orders")
public class Order {

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
    private String note;
    @Column(name = "created_at",nullable = true)
    private Date createdAt;
    @Column(nullable =false)
    private String phone;
    @Column(nullable = false)
    private Integer status;
    @Column(nullable = false)
    private Double totalMoney;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "order")
    private List<Order_details> orderDetails;


}
