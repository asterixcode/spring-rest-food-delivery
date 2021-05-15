package com.asterixcode.asterixfoodapi.domain.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Entity
public class City {

    @Id
    private String name;

    @ManyToOne
    @JoinColumn(nullable = false)
    private State state;
}
