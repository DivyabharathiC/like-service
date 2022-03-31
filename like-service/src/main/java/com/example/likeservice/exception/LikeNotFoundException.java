package com.example.likeservice.exception;


public class LikeNotFoundException extends RuntimeException{
    public LikeNotFoundException(String s) {
       super(s);
    }
}
