package com.emse.spring.faircorp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Room {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Integer floor;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "room", cascade=CascadeType.ALL)
    @JsonManagedReference
    private List<Light> lights;

    public Building getBuilding() {
        return building;
    }

    @ManyToOne
    @JsonBackReference
    private Building building;

    public void setBuilding(Building building) {
        this.building = building;
    }

    public Room() {

    }

    public Room(Integer floor, String name, List<Light> lights, Building building) {
        this.floor = floor;
        this.name = name;
        this.lights = lights;
        this.building = building;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Light> getLights() {
        return lights;
    }

    public void setLights(List<Light> lights) {
        this.lights = lights;
    }
}