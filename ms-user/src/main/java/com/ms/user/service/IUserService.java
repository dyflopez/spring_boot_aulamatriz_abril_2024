package com.ms.user.service;

import com.ms.user.dto.UserDto;
import com.ms.user.model.UserEntity;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUserService {

    ResponseEntity<UserEntity> create(UserDto userDto);

    ResponseEntity<List<UserEntity>> getAll();

    ResponseEntity<UserEntity> getById(String id);

    ResponseEntity<?> deleteById(String id);

    ResponseEntity<UserEntity> updateById(UserEntity userEntity , String id);


    ResponseEntity<UserEntity> getByDocumentTypeDocument(String document, String typeDocument);
}
