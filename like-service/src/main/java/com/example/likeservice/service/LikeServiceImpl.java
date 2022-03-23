package com.example.likeservice.service;

import com.example.likeservice.model.Like;
import com.example.likeservice.repo.LikeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    LikeRepo likeRepo;



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
        return "successfully Deleted likeId "+likeId;
    }

}



