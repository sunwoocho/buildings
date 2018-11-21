package com.emse.spring.faircorp.model;

import java.util.List;


public class RoomDto {

    private Long id;
    private Integer floor;
    private String name;
    private List<Light> lights;
    private long buildingID;

    public RoomDto() {
    }

    public RoomDto(Room room) {
        this.id = room.getId();
        this.floor = room.getFloor();
        this.name = room.getName();
        this.lights = room.getLights();
        this.buildingID = room.getBuilding().getId();
    }

    public Long getId() {
        return id;
    }

    public Integer getFloor() {
        return floor;
    }

    public String getName() {
        return name;
    }

    public List<Light> getLights() {
        return lights;
    }

    public long getBuildingID() {
        return buildingID;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLights(List<Light> lights) {
        this.lights = lights;
    }

    public void setBuildingID(long buildingID) {
        this.buildingID = buildingID;
    }
}
