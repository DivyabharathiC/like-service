package com.example.likeservice.controller;

import com.example.likeservice.enums.Gender;
import com.example.likeservice.model.Like;
import com.example.likeservice.model.LikeDTO;
import com.example.likeservice.model.User;
import com.example.likeservice.service.LikeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@WebMvcTest(LikeController.class)
class LikeControllerTest {

    @MockBean
    LikeService likeService;

    @Autowired
    MockMvc mockMvc;

    public static String asJsonString(final Object object){
        try{
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void createLike() throws Exception {
        LikeDTO response = createLikeResponse();
        Like like = createNewLikeRequest();
        Mockito.when(likeService.createLike(like.getPostOrCommentId(),like)).thenReturn(response);
        mockMvc.perform(post("/api/v1/postsOrComments/1000/likes")
                        .content(asJsonString(like))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void getLikePage() throws Exception {
        LikeDTO request = getLike();
        Mockito.when(likeService.getLikePage(request.getLikeId(), request.getPostOrCommentId())).thenReturn(request);
        mockMvc.perform(get("/api/v1/postsOrComments/1000/likes/10000")
                        .content(asJsonString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getLikesPage() throws Exception {
        Like request = createNewLikeRequest();
        List<LikeDTO> likeLists = getListOfLikesByPostOrCommentId();
        Mockito.when(likeService.getLikesPage(request.getPostOrCommentId(), 1,3)).thenReturn(likeLists);
        mockMvc.perform(get("/api/v1/postsOrComments/1000/likes")
                        .content(asJsonString(likeLists))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    void getCount() throws Exception {
        Like request = createNewLikeRequest();
        List<LikeDTO> commentLists = getListOfLikesByPostOrCommentId();
        Mockito.when(likeService.getCount(request.getPostOrCommentId())).thenReturn(commentLists.size());
        mockMvc.perform(get("/api/v1/postsOrComments/1000/likes/count")
                        .content(asJsonString(commentLists))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void deleteLike() throws Exception {
        Like request = createNewLikeRequest();
        Mockito.when(likeService.deleteLike(request.getLikeId())).thenReturn(String.valueOf(true));
        mockMvc.perform(delete("/api/v1/postsOrComments/1000/likes/10000")
                        .content(asJsonString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    private Like createNewLikeRequest() {
        Like like = new Like();

        like.setLikeId("10000");
        like.setLikedBy("9");
        like.setPostOrCommentId("1000");

        return like;
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

    private LikeDTO createLikeResponse() {
        LikeDTO likeDTO = new LikeDTO();
        User response = createOneUserDataToPost();
        likeDTO.setLikeId("10000");
        likeDTO.setLikedBy(response);
        likeDTO.setPostOrCommentId("1000");

        return likeDTO;
    }

    private LikeDTO getLike() {
        LikeDTO likeDTO=new LikeDTO();
        User response = createOneUserDataToPost();

        likeDTO.setLikeId("10000");
        likeDTO.setLikedBy(response);
        likeDTO.setPostOrCommentId("1000");

        return likeDTO;

    }

    private List<LikeDTO> getListOfLikesByPostOrCommentId() {
        List<LikeDTO> lists = new ArrayList<>();
        User response = createOneUserDataToPost();

        LikeDTO likeDTO = new LikeDTO();
        likeDTO.setLikeId("10000");
        likeDTO.setLikedBy(response);
        likeDTO.setPostOrCommentId("1000");

        lists.add(likeDTO);

        LikeDTO likeDTO1 = new LikeDTO();
        likeDTO1.setLikeId("10001");
        likeDTO1.setLikedBy(response);
        likeDTO1.setPostOrCommentId("1000");

        lists.add(likeDTO1);

        LikeDTO likeDTO2 = new LikeDTO();
        likeDTO2.setLikeId("10002");
        likeDTO2.setLikedBy(response);
        likeDTO2.setPostOrCommentId("1000");

        lists.add(likeDTO2);

        return lists;
    }

}