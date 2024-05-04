package com.auth.jwt.service;

import com.auth.jwt.dto.AuthUserDto;
import com.auth.jwt.dto.NewUserDto;
import com.auth.jwt.dto.RequestDto;
import com.auth.jwt.dto.TokenDto;
import com.auth.jwt.model.AuthUser;
import com.auth.jwt.repository.AuthUserRepository;
import com.auth.jwt.security.JwtProvider;
import org.antlr.v4.runtime.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private AuthUserRepository authUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider jwtProvider;

    public AuthUser save(NewUserDto newUserDto){
        Optional<AuthUser> user = this
                .authUserRepository
                .findByUserName(newUserDto.getUserName());
        if(user.isPresent()){
            return null;
        }

        String password =  this.passwordEncoder.encode(newUserDto.getPassword());

        AuthUser authUser = AuthUser
                .builder()
                .userName(newUserDto.getUserName())
                .password(password)
                .role(newUserDto.getRole())
                .build();

        return  this.authUserRepository.save(authUser);

    }

    public TokenDto login(AuthUserDto authUserDto){
        Optional<AuthUser> user = this
                .authUserRepository
                .findByUserName(authUserDto.getUserName());
        if(user.isEmpty()){
            return null;
        }
        if(this.passwordEncoder.matches(authUserDto.getPassword(),user.get().getPassword())){
            return  new TokenDto(this.jwtProvider.createToken(user.get()));
        }
        return null;
    }

    public TokenDto validate(String token , RequestDto requestDto){
        if(!this.jwtProvider.validate(token,requestDto)){
            return null;
        }
        String userName= this.jwtProvider.getUserNameFromToken(token);
        if(!this.authUserRepository.findByUserName(userName).isPresent()){
            return null;
        }
        return  new TokenDto(token);
    }

}
