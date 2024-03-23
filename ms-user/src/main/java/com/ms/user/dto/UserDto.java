package com.ms.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String document;

    private String typeDocument;

    private String name;

    private String lastname;

    private String phoneNumber;

}
