package com.auth.jwt.controller;

import com.auth.jwt.dto.AuthUserDto;
import com.auth.jwt.dto.NewUserDto;
import com.auth.jwt.dto.RequestDto;
import com.auth.jwt.dto.TokenDto;
import com.auth.jwt.model.AuthUser;
import com.auth.jwt.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;


    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody AuthUserDto authUserDto){

        TokenDto token = this.authService.login(authUserDto);
        if(token == null){
            return  ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(token);

    }

    @PostMapping("/validate")
    public ResponseEntity<TokenDto> validate(@RequestParam String token, @RequestBody RequestDto authUserDto){
        TokenDto tokenDto = this.authService.validate(token,authUserDto);
        if(token == null){
            return  ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(tokenDto);
    }

    @PostMapping("/create")
    public ResponseEntity<AuthUser> create(@RequestBody NewUserDto newUserDto){
        AuthUser authUser = this.authService.save(newUserDto);
        if(authUser == null){
            return  ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(authUser);
    }

}
