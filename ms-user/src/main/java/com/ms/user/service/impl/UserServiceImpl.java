package com.ms.user.service.impl;

import com.ms.user.dto.UserDto;
import com.ms.user.model.UserEntity;
import com.ms.user.repository.UserRepository;
import com.ms.user.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;

    @Override
    public ResponseEntity<UserEntity> create(UserDto userDto) {

        //TODO validar si el usuario existe
        UserEntity userEntity = new UserEntity();
        userEntity.setPhoneNumber(userDto.getPhoneNumber());
        userEntity.setName(userDto.getName());
        userEntity.setLastname(userDto.getLastname());
        userEntity.setDocument(userDto.getDocument());
        userEntity.setTypeDocument(userDto.getTypeDocument());
        userEntity.setId(UUID.randomUUID().toString());
        userEntity.setCreatAt(LocalDateTime.now());

        var newUser = this.userRepository.save(userEntity);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(newUser);
    }

    @Override
    public ResponseEntity<List<UserEntity>> getAll() {
        var userList = this.userRepository.findAll();
        return ResponseEntity.ok(userList);
    }

    @Override
    public ResponseEntity<UserEntity> getById(String id) {
        var user = this.userRepository.findById(id);

        return user
                .map(ResponseEntity::ok)
                .orElseGet(
                        () -> ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .build()
                );
    }

    @Override
    public ResponseEntity<?> deleteById(String id) {
        this.userRepository.deleteById(id);
        return ResponseEntity
                .ok()
                .build();
    }

    @Override
    public ResponseEntity<UserEntity> updateById(UserEntity userEntity, String id) {
        var oldUser = this.userRepository.findById(id);
        if(oldUser.isEmpty()){
            return  ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        var newUser =oldUser.get();

        newUser.setName(userEntity.getName());
        newUser.setLastname(userEntity.getLastname());
        newUser.setDocument(userEntity.getDocument());
        newUser.setPhoneNumber(userEntity.getPhoneNumber());
        var updatedUser =this.userRepository.save(newUser);

        return ResponseEntity.ok(updatedUser);
    }

    @Override
    public ResponseEntity<UserEntity> getByDocumentTypeDocument(String document,
                                                                String typeDocument)
    {
        var user = this.userRepository
                .findByDocumentAndTypeDocumentOrderByCreatAtDesc
                        (document,typeDocument);

        return user
                .map(ResponseEntity::ok)
                .orElseGet(
                        () -> ResponseEntity.status(HttpStatus.NOT_FOUND
                        ).build());
    }
}
