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
@ToString
public class LikeDTO {
    @Id
    private String postOrCommentId;
    private  String likeId;
    private String likedBy;
    private LocalDateTime createdAt;


}
