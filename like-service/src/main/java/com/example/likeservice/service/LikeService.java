package com.example.likeservice.service;

import com.example.likeservice.model.Like;
import com.example.likeservice.model.LikeDTO;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface LikeService {

    Like createLike(String postOrCommentId, Like like);

    String deleteLike(String likeId);

    Integer getCount(String postOrCommentId);

    List<Like> getLikesPage(String postOrCommentId);
}
