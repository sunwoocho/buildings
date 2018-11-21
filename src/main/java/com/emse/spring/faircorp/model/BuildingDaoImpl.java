package com.emse.spring.faircorp.model;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class BuildingDaoImpl implements BuildingDaoCustom{

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Light> FindallLights(Long id){
        List<Light> lights = null;
        String jpql = "select room from Room room where building.id = :id";
        List<Room> rooms = em.createQuery(jpql, Building.class).setParameter("id",id).getSingleResult().getRooms();
        for(int i=0; i<rooms.size();i++){
            for(int j=0; j<rooms.get(i).getLights().size();j++){
                lights.add(rooms.get(i).getLights().get(j));
            }
        }
        return lights;
    }
}
