package com.example.likeservice.repo;

import com.example.likeservice.model.Like;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DataMongoTest
class LikeRepoTest {

    @Autowired
    LikeRepo likeRepo;


    @BeforeEach
    void setUp() {
        Like like = createLike();
        likeRepo.save(like);
    }

    @AfterEach
    void tearDown() {
        likeRepo.deleteAll();
    }

    private Like createLike() {
        Like like = new Like();

        like.setLikeId("10000");
        like.setLikedBy("9");
        like.setPostOrCommentId("1000");

        return like;
    }
}