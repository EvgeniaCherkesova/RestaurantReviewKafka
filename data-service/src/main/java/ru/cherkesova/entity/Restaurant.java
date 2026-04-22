package ru.cherkesova.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Entity
@Getter
@Setter
@Table(name = "restaurants")
public class Restaurant {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(unique = true)
    private String name;
}
