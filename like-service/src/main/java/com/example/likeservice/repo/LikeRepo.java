package com.example.likeservice.repo;


import com.example.likeservice.model.Like;
import com.example.likeservice.model.LikeDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;
import java.util.List;

public interface LikeRepo extends MongoRepository<Like, String> {

}
