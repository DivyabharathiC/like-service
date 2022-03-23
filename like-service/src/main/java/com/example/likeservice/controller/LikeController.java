package com.example.likeservice.controller;

import com.example.likeservice.model.Like;
import com.example.likeservice.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/postsOrComments")
public class LikeController {

    @Autowired
    LikeService likeService;

    @PostMapping(path= "/{postOrCommentId}/likes")
    public ResponseEntity<Like> createLike(@PathVariable("postOrCommentId") String postOrCommentId, @RequestBody Like like){
        return new ResponseEntity<Like>(likeService.createLike(postOrCommentId,like), HttpStatus.CREATED);
    }



}
