package com.asterixcode.asterixfoodapi.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.*;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Restaurant {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) // --> set column NOT NULL (when using ddl-auto)
    private String name;

    //@Column(name = "delivery_fee") --> to change column name
    @Column(nullable = false)
    private BigDecimal deliveryFee;

    //@JoinColumn(name = "kitchen_code")  -> to change column name for Foreign Key
    @ManyToOne //many restaurants have one kitchen
    @JoinColumn(nullable = false)
    private Kitchen kitchen;
}
