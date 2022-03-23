package com.example.likeservice.controller;

import com.example.likeservice.model.Like;
import com.example.likeservice.model.LikeDTO;
import com.example.likeservice.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/postsOrComments")
public class LikeController {

    @Autowired
    LikeService likeService;

    @PostMapping(path= "/{postOrCommentId}/likes")
    public ResponseEntity<Like> createLike(@PathVariable("postOrCommentId") String postOrCommentId, @RequestBody Like like){
        return new ResponseEntity<Like>(likeService.createLike(postOrCommentId,like), HttpStatus.CREATED);
    }

    @DeleteMapping("/{postOrCommentId}/likes/{likeId}")
    public ResponseEntity<String> deleteLike(@PathVariable("likeId") String likeId,@PathVariable("postOrCommentId") String postOrCommentId ) {
        return new ResponseEntity<String>(likeService.deleteLike(likeId), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{postOrCommentId}/likes/count")
    public ResponseEntity<Integer> getCount(@PathVariable("postOrCommentId") String postOrCommentId) {
        return new ResponseEntity<Integer>(likeService.getCount(postOrCommentId), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{postOrCommentId}/likes")
    public  ResponseEntity<List<Like>> getLikesPage(@PathVariable("postOrCommentId") String postOrCommentId){
        return new ResponseEntity<List<Like>>(likeService.getLikesPage(postOrCommentId),HttpStatus.ACCEPTED);
    }

}
