package com.ms.ranking.service.msranking.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RankingDTO {

    private String userId;

    private String hotelId;

    private int stars;

    private String review;

}
