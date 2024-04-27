package com.ms.ranking.service.msranking.controller.v1;

import com.ms.ranking.service.msranking.controller.v1.doc.RankingDoc;
import com.ms.ranking.service.msranking.dto.RankingDTO;
import com.ms.ranking.service.msranking.model.RankingDocument;
import com.ms.ranking.service.msranking.service.IRankingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class RankingController implements RankingDoc {

    private  final IRankingService iRankingService;
    @Override
    public ResponseEntity<RankingDocument> create(RankingDTO rankingDTO) {
        return this.iRankingService.create(rankingDTO);
    }

    @Override
    public ResponseEntity<List<RankingDocument>> listAll() {
        return this.iRankingService.getAll();
    }

    @Override
    public ResponseEntity<List<RankingDocument>> listByHotelId(String hotelId) {
        return this.iRankingService.getByHotelId(hotelId);
    }

    @Override
    public ResponseEntity<List<RankingDocument>> listByUserID(String userId) {
        return this.iRankingService.getByUserId(userId);
    }
}
