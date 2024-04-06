package com.ms.user.controller.v2.doc;

import com.ms.user.dto.UserDto;
import com.ms.user.model.UserEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "User" , description = "API exposed for management all user")
@RequestMapping("/api/v2/user")
public interface UserDoc {

    @Operation(summary = "create user",description = "this operation is for creating user")
    @ApiResponse(responseCode = "201", description = "user created", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    @PostMapping
    ResponseEntity<UserEntity> create(@RequestBody UserDto userDto);

    @Operation(summary = "find by id user", description = "this operation is for find by id ")
    @ApiResponse(responseCode = "200", description = "the user with id user found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    ResponseEntity<UserEntity> getById(@PathVariable String id);
}
