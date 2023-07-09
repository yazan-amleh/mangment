package com.example.myOrderManagement.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="customer_id", nullable=false)
    private Customer customer;

    @Column(name = "ordered_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderedAt;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    Set<ProductOrder> productOrders;

}

