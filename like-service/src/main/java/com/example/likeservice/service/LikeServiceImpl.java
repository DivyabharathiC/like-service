package com.example.likeservice.service;

import com.example.likeservice.Feign.UserFeignClient;
import com.example.likeservice.exception.LikeNotFoundException;
import com.example.likeservice.model.Like;
import com.example.likeservice.model.LikeDTO;
import com.example.likeservice.repo.LikeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.likeservice.constant.Constant.LikeNotFound;


@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    LikeRepo likeRepo;

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    UserFeignClient userFeignClient;


    @Override
    public LikeDTO createLike(String postOrCommentId, Like like) {
        like.setPostOrCommentId(postOrCommentId);
        like.setCreatedAt(LocalDateTime.now());
        likeRepo.save(like);

        LikeDTO likeDTO =new LikeDTO(like.getLikeId(),like.getPostOrCommentId(),
                userFeignClient.getUser(like.getLikedBy()),
                like.getCreatedAt());

        return likeDTO;
    }

    @Override
    public String deleteLike(String likeId) {
        likeRepo.deleteById(likeId);
        return "successfully Deleted likeId " + likeId;
    }

    @Override
    public Integer getCount(String postOrCommentId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("postOrCommentId").is(postOrCommentId));
        List<Like> listOfLikes = mongoTemplate.find(query, Like.class);
        return listOfLikes.size();
       // return likeRepo.findByPostOrCommentId(postOrCommentId).size();

    }

    @Override
    public LikeDTO getLikePage(String likeId, String postOrCommentId) {
        try {
            Like like = likeRepo.findById(likeId).get();

            LikeDTO likeDTO = new LikeDTO(like.getLikeId(), like.getPostOrCommentId(),
                    userFeignClient.getUser(like.getLikedBy()),
                    like.getCreatedAt());

            return likeDTO;
        }
        catch (Exception e)
        {
            throw new LikeNotFoundException( LikeNotFound);
        }
    }

    @Override
    public List<LikeDTO> getLikesPage(String postOrCommentId,Integer page, Integer size) {
        page = Optional.ofNullable(page).orElse(0);
        size = Optional.ofNullable(size).orElse(10);

        Pageable pageable = PageRequest.of(page, size);
        Query query = new Query().with(pageable);
        query.addCriteria(Criteria.where("postOrCommentId").is(postOrCommentId));
        List<Like> likes = mongoTemplate.find(query, Like.class);
     //   List<Like> likes = likeRepo.findByPostOrCommentId(postOrCommentId,paging);

        List<LikeDTO> likeDTOS = new ArrayList<>();
        for(Like like:likes){
            LikeDTO likeDTO=new LikeDTO(like.getLikeId(),like.getPostOrCommentId(),
                    userFeignClient.getUser(like.getLikedBy()),
                    like.getCreatedAt());

            likeDTOS.add(likeDTO);
        }
        return  likeDTOS;
    }



}



