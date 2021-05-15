package com.asterixcode.asterixfoodapi.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class State {

    @Id
    private String name;
}
