package com.ms.user.external.serice;

import com.ms.user.dto.HotelDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "MS-HOTEL")
public interface IHotelServiceFeign {

    @GetMapping("/api/v1/hotel/{id}")
    HotelDto getHotel(@PathVariable String id);

}
