package com.ms.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRankingsDTO {

    private String id;

    private String document;

    private String typeDocument;

    private String name;

    private String lastname;

    private String phoneNumber;

    private List<RankingDto> rankings;
}
