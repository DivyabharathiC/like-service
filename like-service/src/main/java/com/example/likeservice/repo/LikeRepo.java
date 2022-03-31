package com.example.likeservice.repo;


import com.example.likeservice.model.Like;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface LikeRepo extends MongoRepository<Like, String> {

    List<Like> findByPostOrCommentId(String postOrCommentId, Pageable paging);
    List<Like> findByPostOrCommentId(String postOrCommentId);
}
