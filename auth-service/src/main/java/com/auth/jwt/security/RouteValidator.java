package com.auth.jwt.security;

import com.auth.jwt.dto.RequestDto;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Pattern;

@Component
@Data
@ConfigurationProperties(prefix = "admin-paths")
public class RouteValidator {

    private List<RequestDto> paths;
    private String casa;

    public boolean isAdmin(RequestDto requestDto){
        return paths
                .stream()
                .anyMatch(
                        p ->
                                Pattern
                                        .matches(
                                                p.getUri(),requestDto.getUri()
                                        ) &&
                                        p.getMethod().equals(requestDto.getMethod()));
    }
}
