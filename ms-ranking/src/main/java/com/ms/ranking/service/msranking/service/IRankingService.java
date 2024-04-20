package com.ms.ranking.service.msranking.service;

import com.ms.ranking.service.msranking.dto.RankingDTO;
import com.ms.ranking.service.msranking.model.RankingDocument;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IRankingService {

    ResponseEntity<RankingDocument> create(RankingDTO rankingDTO);

    ResponseEntity<List<RankingDocument>> getAll();

    ResponseEntity<List<RankingDocument>> getByUserId(String userId);

    ResponseEntity<List<RankingDocument>> getByHotelId(String hotelId);

}
