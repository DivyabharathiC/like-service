package com.example.likeservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class LikeDTO {

    @Id
    private  String likeId;
    private String postOrCommentId;
    private User likedBy;
    private LocalDateTime createdAt;

}
