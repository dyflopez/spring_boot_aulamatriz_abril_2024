package com.ms.user.controller.v2;

import com.ms.user.controller.v2.doc.UserDoc;
import com.ms.user.dto.UserDto;
import com.ms.user.model.UserEntity;
import com.ms.user.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


@AllArgsConstructor
@RestController
public class UserControllerV2 implements UserDoc {

    private final IUserService iUserService;

    @Override
    public ResponseEntity<UserEntity> create(UserDto userDto) {
        return  this.iUserService.create(userDto);
    }

    @Override
    public ResponseEntity<UserEntity> getById(String id) {
        return this.iUserService.getById(id);
    }
}
