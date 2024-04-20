package com.ms.ranking.service.msranking.service.impl;

import com.ms.ranking.service.msranking.dto.RankingDTO;
import com.ms.ranking.service.msranking.model.RankingDocument;
import com.ms.ranking.service.msranking.repository.IRankingRepository;
import com.ms.ranking.service.msranking.service.IRankingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class RankingServiceImpl implements IRankingService {

    private final IRankingRepository iRankingRepository;

    @Override
    public ResponseEntity<RankingDocument> create(RankingDTO rankingDTO) {

        RankingDocument rankingDocument = new RankingDocument();

        rankingDocument.setReview(rankingDTO.getReview());
        rankingDocument.setStars(rankingDTO.getStars());
        rankingDocument.setHotelId(rankingDTO.getHotelId());
        rankingDocument.setUserId(rankingDTO.getUserId());

        RankingDocument newRankingDocument = this.iRankingRepository.save(rankingDocument);


        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(newRankingDocument);
    }

    @Override
    public ResponseEntity<List<RankingDocument>> getAll() {
        var ranking = this.iRankingRepository.findAll();
        return ResponseEntity.ok(ranking);
    }

    @Override
    public ResponseEntity< List<RankingDocument>> getByUserId(String userId) {
        var ranking = this.iRankingRepository.findByUserId(userId);
        return ResponseEntity.ok(ranking);
    }

    @Override
    public ResponseEntity<List<RankingDocument>> getByHotelId(String hotelId) {
        List<RankingDocument> ranking = this.iRankingRepository.findByUserId(hotelId);
        return ResponseEntity.ok(ranking);
    }
}
