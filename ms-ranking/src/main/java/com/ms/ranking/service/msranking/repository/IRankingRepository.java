package com.ms.ranking.service.msranking.repository;

import com.ms.ranking.service.msranking.model.RankingDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IRankingRepository  extends MongoRepository<RankingDocument,String> {

    //rankingdb.RankingDocument.find({"userId":"?"})
    List<RankingDocument> findByUserId(String userID);

    //rankingdb.RankingDocument.find({"hotelId":"?"})
    List<RankingDocument> findByHotelId(String hotelId);



}
