package com.example.likeservice.service;

import com.example.likeservice.model.Like;
import org.springframework.stereotype.Service;


@Service
public interface LikeService {

    Like createLike(String postOrCommentId, Like like);

    String deleteLike(String likeId);

    Integer getCount(String postOrCommentId);
}
