package com.ms.hotel.mocks;

import com.ms.hotel.dto.HotelDTO;

public class MockDTOs {

    public static HotelDTO createHotelDTO( String name , String location,String informations){
        return HotelDTO
                .builder()
                .name(name)
                .location(location)
                .information(informations)
                .build();
    }
}
