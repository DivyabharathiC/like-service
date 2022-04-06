package com.example.likeservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Like")
public class Like {

    @Id
    private  String likeId;
    @NotEmpty(message = "postOrCommentId is required")
    private String postOrCommentId;
    @NotEmpty(message = "user ID is required")
    private String likedBy;
    private LocalDateTime createdAt;

}
