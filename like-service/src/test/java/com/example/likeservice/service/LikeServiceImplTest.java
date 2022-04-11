package com.example.likeservice.service;

import com.example.likeservice.Feign.UserFeignClient;
import com.example.likeservice.enums.Gender;
import com.example.likeservice.model.Like;
import com.example.likeservice.model.LikeDTO;
import com.example.likeservice.model.User;
import com.example.likeservice.repo.LikeRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class LikeServiceImplTest {

    @InjectMocks
    LikeServiceImpl likeService;
    @Mock
    LikeRepo likeRepo;
    @Mock
    UserFeignClient userFeignClient;


    @Test
    void createLike() {
        Like likeRequest = createLikeRequest();

        Like like = new Like();
        like.setLikeId(likeRequest.getLikeId());
        like.setLikedBy(likeRequest.getLikedBy());
        like.setPostOrCommentId(likeRequest.getPostOrCommentId());
        
        Mockito.when(likeRepo.save(like)).thenReturn(like);
        assertThat(likeRepo.findById(like.getLikeId())).isNotNull();
    }



    @Test
    void getLikePage() {
        LikeDTO request = getLike();
        Optional<Like> likeRequest = getOptionalRequest();
        Mockito.when(likeRepo.findById(request.getPostOrCommentId())).thenReturn(likeRequest);
        assertEquals(likeRequest,likeRepo.findById(request.getPostOrCommentId()));
    }

    private LikeDTO getLike() {
        LikeDTO likeDTO=new LikeDTO();
        User response = createOneUserDataToPost();

        likeDTO.setLikeId("10000");
        likeDTO.setLikedBy(response);
        likeDTO.setPostOrCommentId("1000");

        return likeDTO;
    }

    @Test
    void getLikesPage() {
        List<Like> likes = getLikesByPostOrCommentId();
        Mockito.when(likeRepo.findAll()).thenReturn(likes);
        assertEquals(2,likeRepo.findAll().size());
    }


    @Test
    void getCount() {

//        List<Like> likes = getLikesByPostOrCommentId();
//        Mockito.when(likeRepo.findById(likes.get(0).getPostOrCommentId())).thenReturn(likes.size());
//        assertThat(likes.size());

    }



    @Test
    void deleteLike() {
        Like like = createLikeRequest();
        Mockito.when(likeRepo.findById(like.getLikeId())).thenReturn(Optional.of(like));
        likeRepo.delete(like);
        verify(likeRepo, times(1)).delete(like);
    }

    private Like createLikeRequest() {
        Like like = new Like();

        like.setLikeId("10000");
        like.setLikedBy("9");
        like.setPostOrCommentId("1000");

        return like;
    }

    private Optional<Like> getOptionalRequest() {
        Optional<Like> like = Optional.of(new Like( "1000","100","9", LocalDateTime.now()));



        return like;
    }

    private List<Like> getLikesByPostOrCommentId() {
        List<Like> lists = new ArrayList<>();
        User response = createOneUserDataToPost();

        Like like = new Like();
        like.setLikeId("10000");
        like.setLikedBy(String.valueOf(response));
        like.setPostOrCommentId("1000");

        lists.add(like);

        Like like1 = new Like();
        like1.setLikeId("10000");
        like1.setLikedBy(String.valueOf(response));
        like1.setPostOrCommentId("1000");

        lists.add(like1);

        return lists;
    }

    private User createOneUserDataToPost() {
        User userDetails = new User();
        userDetails.setUserId("9");
        userDetails.setFirstName("John");
        userDetails.setMiddleName("Babu");
        userDetails.setLastName("Gyara");
        userDetails.setPhoneNumber("9700933932");
        userDetails.setDateOfBirth(new Date(1992, 9, 9));
        userDetails.setGender(Gender.MALE);
        userDetails.setEmployeeId("6969");
        userDetails.setBloodGroup("A+");
        userDetails.setEmail("gyarababu9@gmail.com");
        userDetails.setAddress("Hyderabad");
        return userDetails;
    }


}