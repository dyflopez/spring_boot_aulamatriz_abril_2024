package com.ms.hotel.service.impl;

import com.ms.hotel.dto.HotelDTO;
import com.ms.hotel.mocks.MockDTOs;
import com.ms.hotel.mocks.MockEntity;
import com.ms.hotel.model.HotelEntity;
import com.ms.hotel.repository.IHotelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HotelServiceImplTest {

    @Autowired
    private HotelServiceImpl hotelService;

    @MockBean
    private IHotelRepository iHotelRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAll() {

        // CREAR la data de prueba
        List<HotelEntity> hotelEntities = new ArrayList<>();
        hotelEntities.add(new HotelEntity(UUID.randomUUID(),"AMARTE","Usaquen","Liberar estres"));
        hotelEntities.add(new HotelEntity(UUID.randomUUID(),"HILTON","Usaquen","5 estrellas"));
        //Crear los mock
        when(iHotelRepository.findAll()).thenReturn(hotelEntities);
        //llamado a metodo
        ResponseEntity<List<HotelEntity>>  response = this.hotelService.getAll();
        //extraer la informacion (opcional)
        var responseEntity = response.getBody();
        //Validaciones
        assertEquals(responseEntity.size(),2);
        assertEquals(responseEntity.get(0).getName(),"AMARTE");
        assertNotNull(responseEntity);
        //verificaciones
        verify(iHotelRepository , times(1)).findAll();
    }

    @Test
    void create() {

        //Preparando data
        HotelDTO hotelDTO = MockDTOs.createHotelDTO("Randys","Kennedy","super economico");
        HotelEntity hotelEntity = MockEntity.getMockHotelentity(null,"Randys","Kennedy","super economico");
        //Crear el mock
        when(iHotelRepository.save(any())).thenReturn(hotelEntity);
        //Realizar llamado
        ResponseEntity<HotelEntity> newHotel = this.hotelService.create(hotelDTO);
        var responseEntity = newHotel.getBody();
        //Verificar
        assertNotNull(newHotel);
        assertNotNull(responseEntity);
        assertEquals(hotelEntity,responseEntity);
        verify(iHotelRepository , times(0)).findAll();
        verify(iHotelRepository , times(1)).save(any());

    }

    @Test
    void getById() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void updateById() {
    }
}