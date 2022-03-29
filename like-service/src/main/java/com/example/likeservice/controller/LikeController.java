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
@CrossOrigin(value ="*")
@RequestMapping(path = "/api/v1/postsOrComments/{postOrCommentId}/likes")
public class LikeController {

    @Autowired
    LikeService likeService;

    @PostMapping(path= "")
    public ResponseEntity<LikeDTO> createLike(@PathVariable("postOrCommentId") String postOrCommentId, @RequestBody Like like){
        return new ResponseEntity<LikeDTO>(likeService.createLike(postOrCommentId,like), HttpStatus.CREATED);
    }

    @DeleteMapping("/{likeId}")
    public ResponseEntity<String> deleteLike(@PathVariable("likeId") String likeId,@PathVariable("postOrCommentId") String postOrCommentId ) {
        return new ResponseEntity<String>(likeService.deleteLike(likeId), HttpStatus.ACCEPTED);
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getCount(@PathVariable("postOrCommentId") String postOrCommentId) {
        return new ResponseEntity<Integer>(likeService.getCount(postOrCommentId), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{likeId}")
    public  ResponseEntity<LikeDTO> getLikesPage(@PathVariable("likeId") String likeId, @PathVariable("postOrCommentId") String postOrCommentId){
        return new ResponseEntity<LikeDTO>(likeService.getLikePage(likeId, postOrCommentId),HttpStatus.ACCEPTED);
    }

    @GetMapping("")
    public  ResponseEntity<List<LikeDTO>> getLikesPage(@PathVariable("postOrCommentId") String postOrCommentId){
        return new ResponseEntity<List<LikeDTO>>(likeService.getLikesPage(postOrCommentId),HttpStatus.ACCEPTED);
    }

}
