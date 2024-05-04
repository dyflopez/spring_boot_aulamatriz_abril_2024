package com.ms.hotel.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HotelDTO {

    @NotBlank
    @NotEmpty
    @Size(min = 3, max = 10)
    private  String name;

    @NotBlank
    @NotEmpty
    @Size(min = 8, max = 100)
    private  String location ;
    @NotBlank
    @NotEmpty
    @Size(min = 10)
    private String information;
}
