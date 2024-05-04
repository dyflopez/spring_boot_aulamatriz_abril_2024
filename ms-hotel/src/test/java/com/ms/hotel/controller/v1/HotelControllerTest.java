package com.ms.hotel.controller.v1;

import static org.junit.jupiter.api.Assertions.*;
import com.ms.hotel.mocks.MockEntity;
import com.ms.hotel.model.HotelEntity;
import com.ms.hotel.service.IHotelService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class HotelControllerTest {

    @Mock
    private IHotelService iHotelService;

    @InjectMocks
    private HotelController hotelController;

    @Test
    void testGetAll(){

        //Preparar Data
        HotelEntity hotel = MockEntity.getMockHotelentity(null,"Sevilla" ,"Madrid","El mejor hotel de europa");
        List<HotelEntity> hotelEntityList = new ArrayList<>();
        hotelEntityList.add(hotel);
        var responseService = ResponseEntity.ok(hotelEntityList);
        //crear mock
        when(this.iHotelService.getAll()).thenReturn(responseService);
        //realizar el llamado
       var response =  this.hotelController.getAll();

       assertNotNull(response);

    }


}
