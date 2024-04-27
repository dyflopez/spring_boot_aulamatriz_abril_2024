package com.ms.user.service.impl;

import com.ms.user.dto.HotelDto;
import com.ms.user.dto.RankingDto;
import com.ms.user.dto.UserDto;
import com.ms.user.dto.UserRankingsDTO;
import com.ms.user.exception.MyHandleException;
import com.ms.user.external.serice.IHotelServiceFeign;
import com.ms.user.model.UserEntity;
import com.ms.user.repository.UserRepository;
import com.ms.user.service.IUserService;
import com.ms.user.utils.Constant;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;

    private final RestTemplate restTemplate;

    private final IHotelServiceFeign iHotelServiceFeign;

    @Override
    public ResponseEntity<UserEntity> create(UserDto userDto) {

        var user = this.userRepository
                    .findByDocumentAndTypeDocumentOrderByCreatAtDesc(
                            userDto.getDocument(),userDto.getTypeDocument()
                    );
        if(user.isPresent()){
            throw new MyHandleException(Constant.USER_EXIST);
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setPhoneNumber(userDto.getPhoneNumber());
        userEntity.setName(userDto.getName());
        userEntity.setLastname(userDto.getLastname());
        userEntity.setDocument(userDto.getDocument());
        userEntity.setTypeDocument(userDto.getTypeDocument());
        userEntity.setId(UUID.randomUUID().toString());
        userEntity.setCreatAt(LocalDateTime.now());

        var newUser = this.userRepository.save(userEntity);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(newUser);
    }

    @Override
    public ResponseEntity<List<UserEntity>> getAll() {
        var userList = this.userRepository.findAll();
        return ResponseEntity.ok(userList);
    }

    @Override
    public ResponseEntity<UserEntity> getById(String id) {
        var user = this.userRepository.findById(id);

        return user
                .map(ResponseEntity::ok)
                .orElseGet(
                        () -> ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .build()
                );
    }
    @Override
    public ResponseEntity<?> deleteById(String id) {

        this.userRepository.deleteById(id);
        return ResponseEntity
                .ok()
                .build();
    }

    @Override
    public ResponseEntity<UserEntity> updateById(UserEntity userEntity, String id) {

        return userRepository.findById(id)
                .map(user ->userRepository.save(buildUserEntity(user,userEntity)))
                .map(ResponseEntity::ok)
                .orElseGet(()->  ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .build());
    }

    private UserEntity buildUserEntity(UserEntity oldUser,UserEntity user){
        UserEntity newUser= new UserEntity();
        newUser.setId(oldUser.getId());
        newUser.setName(user.getName());
        newUser.setLastname(user.getLastname());
        newUser.setDocument(user.getDocument());
        newUser.setPhoneNumber(user.getPhoneNumber());
        newUser.setTypeDocument(user.getTypeDocument());
        newUser.setCreatAt(user.getCreatAt());
        return newUser;
    }
    @Override
    public ResponseEntity<UserEntity> getByDocumentTypeDocument(String document,
                                                                String typeDocument)
    {
        var user = this.userRepository
                .findByDocumentAndTypeDocumentOrderByCreatAtDesc
                        (document,typeDocument);

        return user
                .map(ResponseEntity::ok)
                .orElseGet(
                        () -> ResponseEntity.status(HttpStatus.NOT_FOUND
                        ).build());
    }

    @Override
    public ResponseEntity getReviewsByUserId(String id) {
        UserRankingsDTO userRankingsDTO = this.userRepository
                .findById(id)
                .map( user -> UserRankingsDTO
                        .builder()
                        .id(user.getId())
                        .name(user.getName())
                        .lastname(user.getLastname())
                        .document(user.getDocument())
                        .typeDocument(user.getTypeDocument())
                        .phoneNumber(user.getPhoneNumber())
                        .build()
                )
                .orElseThrow(() -> new MyHandleException("User does not exits"));
        RankingDto[] rankingDtos = this
                .restTemplate
                .getForObject(
                        "http://MS-RANKING/api/v1/ranking/find-by-user-id/"+userRankingsDTO.getId()
                        , RankingDto[].class
                        );
        if(rankingDtos != null){
            var rankings  = Arrays.stream(rankingDtos).toList();
            var rankingsFull =rankings
                    .stream()
                    .peek(ranking -> {

                       /* ResponseEntity<HotelDto> hotelResponse =
                                this.restTemplate.getForEntity(
                                        "http://MS-HOTEL/api/v1/hotel/"+ranking.getHotelId(),
                                        HotelDto.class
                                );*/

                        var hotel = this.iHotelServiceFeign.getHotel(ranking.getHotelId());
                        ranking.setHotel(hotel);

                    })
                    .collect(Collectors.toList());

            userRankingsDTO.setRankings(rankingsFull);
        }
        return  ResponseEntity.ok(userRankingsDTO);
    }
}
