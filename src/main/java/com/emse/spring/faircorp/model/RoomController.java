package com.emse.spring.faircorp.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController  // (1)
@CrossOrigin
@RequestMapping("/api/rooms") // (2)
@Transactional // (3)

public class RoomController {

    @Autowired
    private  RoomDao roomDao; // (4)
    @Autowired
    private BuildingDao buildingDao;


    @GetMapping // (5)
    public List<RoomDto> findAll() {
        return roomDao.findAll()
                .stream()
                .map(RoomDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}")
    public RoomDto findById(@PathVariable Long id) {
        return roomDao.findById(id).map(room -> new RoomDto(room)).orElse(null);
    }

    @PutMapping(path = "/{id}/switchLight")
    public RoomDto switchStatus(@PathVariable Long id) {
        Room room = roomDao.findById(id).orElseThrow(IllegalArgumentException::new);
        for(int i=0; i<room.getLights().size(); i++) {
            room.getLights().get(i).setStatus(room.getLights().get(i).getStatus() == Status.ON ? Status.OFF : Status.ON);
        }
        return new RoomDto(room);
    }

    @PostMapping
    public RoomDto create(@RequestBody RoomDto dto) {
        Room room = null;
        if (dto.getId() != null) {
            room = roomDao.findById(dto.getId()).orElse(null);
        }

        if (room == null) {
            room = roomDao.save(new Room(dto.getFloor(),dto.getName(),dto.getLights(), buildingDao.getOne(dto.getBuildingID())));
        } else {
            room.setFloor(dto.getFloor());
            room.setName(dto.getName());
            room.setLights(dto.getLights());
            room.setBuilding(buildingDao.getOne(dto.getBuildingID()));
            roomDao.save(room);
        }

        return new RoomDto(room);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        roomDao.deleteById(id);
    }


}
