package com.example.likeservice.service;

import com.example.likeservice.model.Like;
import com.example.likeservice.model.LikeDTO;
import com.example.likeservice.repo.LikeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    LikeRepo likeRepo;

    @Autowired
    MongoTemplate mongoTemplate;


    @Override
    public Like createLike(String postOrCommentId, Like like) {
        like.setPostOrCommentId(postOrCommentId);
        like.setLikeId(like.getLikeId());
        like.setLikedBy(like.getLikedBy());
        like.setCreatedAt(LocalDateTime.now());
        return likeRepo.save(like);
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
    public List<Like> getLikesPage(String postOrCommentId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("postOrCommentId").is(postOrCommentId));
        List<Like> listOfLikes = mongoTemplate.find(query, Like.class);
        return listOfLikes;
    }

}



