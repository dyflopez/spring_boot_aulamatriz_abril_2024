package com.ms.user.controller.v2.doc;

import com.ms.user.dto.UserDto;
import com.ms.user.model.UserEntity;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "User" , description = "API exposed for management all user")
@RequestMapping("/api/v2/user")
public interface UserDoc {

    @PostMapping
    ResponseEntity<UserEntity> create(@RequestBody UserDto userDto);
    @PatchMapping("/{id}")
    ResponseEntity<UserEntity> update(@RequestBody UserEntity userDto,@PathVariable("id") String id);
}
