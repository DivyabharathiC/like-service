package com.example.likeservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class LikeDTO {

    @Id
    private  String likeId;
    @NotEmpty(message = "postOrCommentId is required")
    private String postOrCommentId;
    @NotEmpty(message = "user ID is required")
    private User likedBy;
    private LocalDateTime createdAt;

}
