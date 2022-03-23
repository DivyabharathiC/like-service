package com.example.likeservice.repo;


import com.example.likeservice.model.Like;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LikeRepo extends MongoRepository<Like, String> {

   // Like findByPostOrCommentId(String postOrCommentId);
}
