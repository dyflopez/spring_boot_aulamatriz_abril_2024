package com.ms.hotel.mocks;

import com.ms.hotel.model.HotelEntity;

import java.util.Random;
import java.util.UUID;

public class MockEntity {

    public static HotelEntity getMockHotelentity(UUID id ,String name , String location,String informations){

        return  HotelEntity
                .builder()
                .id(id==null? UUID.randomUUID():id)
                .name(name)
                .location(location)
                .information(informations)
                .build();
    }
}
