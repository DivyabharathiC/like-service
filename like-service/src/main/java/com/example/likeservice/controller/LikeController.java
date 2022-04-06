package com.example.likeservice.controller;

import com.example.likeservice.model.Like;
import com.example.likeservice.model.LikeDTO;
import com.example.likeservice.service.LikeService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.QueryParam;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin(value ="*")
@RequestMapping(path = "/api/v1/postsOrComments/{postOrCommentId}/likes")
public class LikeController {

    private static Logger logger = LoggerFactory.getLogger(LikeController.class);
    @Autowired
    LikeService likeService;

    @PostMapping(path= "")
    public ResponseEntity<LikeDTO> createLike(@PathVariable("postOrCommentId") String postOrCommentId, @RequestBody @Valid Like like){
        logger.info("Starting of createLike request from Like application");
        return new ResponseEntity<LikeDTO>(likeService.createLike(postOrCommentId,like), HttpStatus.CREATED);
    }

    @DeleteMapping("/{likeId}")
    public ResponseEntity<String> deleteLike(@PathVariable("likeId") String likeId,@PathVariable("postOrCommentId") String postOrCommentId ) {
        logger.info("Starting of deleteLike request from Like application");
        return new ResponseEntity<String>(likeService.deleteLike(likeId), HttpStatus.ACCEPTED);
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getCount(@PathVariable("postOrCommentId") String postOrCommentId) {
        logger.info("Starting of getCountLike request from Like application");
        return new ResponseEntity<Integer>(likeService.getCount(postOrCommentId), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{likeId}")
    public  ResponseEntity<LikeDTO> getLikePage(@PathVariable("likeId") String likeId, @PathVariable("postOrCommentId") String postOrCommentId){
        logger.info("Starting of getLikesPage using like id request from Like application");
        return new ResponseEntity<LikeDTO>(likeService.getLikePage(likeId, postOrCommentId),HttpStatus.ACCEPTED);
    }

    @GetMapping("")
    public  ResponseEntity<List<LikeDTO>> getLikesPage(@PathVariable("postOrCommentId") String postOrCommentId,
                                                       @QueryParam(value = "page") Integer page,
                                                       @QueryParam(value = "size") Integer size){
        logger.info("Starting of getLikesPage using postOrCommentId request from Like application");
        return new ResponseEntity<List<LikeDTO>>(likeService.getLikesPage(postOrCommentId,page,size),HttpStatus.ACCEPTED);
    }

}
