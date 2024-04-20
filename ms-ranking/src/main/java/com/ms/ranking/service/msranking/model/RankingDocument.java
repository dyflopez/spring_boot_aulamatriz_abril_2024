package com.ms.ranking.service.msranking.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("rankings")
public class RankingDocument {

    @Id
    private String id;

    private String userId;

    private String hotelId;

    private int stars;

    private String review;
}
