package com.example.likeservice.service;

import com.example.likeservice.model.Like;
import com.example.likeservice.model.LikeDTO;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface LikeService {

    LikeDTO createLike(String postOrCommentId, Like like);

    String deleteLike(String likeId);

    Integer getCount(String postOrCommentId);

   

    LikeDTO getLikePage(String likeId, String postOrCommentId);

    List<LikeDTO> getLikesPage(String postOrCommentId, Integer page, Integer size);
}
