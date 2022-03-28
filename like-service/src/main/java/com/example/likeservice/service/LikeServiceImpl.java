package com.example.likeservice.service;

import com.example.likeservice.Feign.UserFeignClient;
import com.example.likeservice.model.Like;
import com.example.likeservice.model.LikeDTO;
import com.example.likeservice.repo.LikeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


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
    }

    @Override
    public LikeDTO getLikePage(String likeId, String postOrCommentId) {
        Like like = likeRepo.findById(likeId).get();

        LikeDTO likeDTO=new LikeDTO(like.getLikeId(),like.getPostOrCommentId(),
                userFeignClient.getUser(like.getLikedBy()),
                like.getCreatedAt());

        return likeDTO;
    }

    @Override
    public List<LikeDTO> getLikesPage(String postOrCommentId) {

        List<Like> likes=likeRepo.findAll();
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



