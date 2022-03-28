package com.example.likeservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Like")
public class Like {

    @Id
    private  String likeId;
    private String postOrCommentId;
    private String likedBy;
    private LocalDateTime createdAt;

}
