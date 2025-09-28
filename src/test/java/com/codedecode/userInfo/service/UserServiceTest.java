package com.codedecode.userInfo.service;

import com.codedecode.userInfo.dto.UserDTO;
import com.codedecode.userInfo.entity.User;
import com.codedecode.userInfo.mapper.UserMapper;
import com.codedecode.userInfo.repository.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    UserRepo userRepo;

    @InjectMocks
    UserService userService;


    @BeforeEach
    public void setUp() {
        AutoCloseable autoCloseable = MockitoAnnotations.openMocks(this);//in order for Mock and InjectMocks annotations to take effect, you need to call MockitoAnnotations.openMocks(this);
    }


    @Test
    public void addUser()
    {

        User mockUserData=new User(1,"Rahul","Password","LaxmiChock","pune");
        UserDTO userDto= UserMapper.INSTANCE.mapUserToUserDTO(mockUserData);
        // Mock the repository behavior
        when(userRepo.save(mockUserData)).thenReturn(mockUserData);
        UserDTO savedUserDTO=userService.addUser(userDto);
       assertEquals(userDto,savedUserDTO);
        // Verify that the repository method was called
        verify(userRepo, times(1)).save(mockUserData);

    }

    @Test
    public void fetchUserDetailsByExisting_Id()
     {
         //createa mock userId
        Integer mockUserId = 1;

        //craete mock object
        User mockUserData=new User(1,"Rahul","Password","LaxmiChock","pune");
        when(userRepo.findById(mockUserId)).thenReturn(Optional.of(mockUserData));
         ResponseEntity<UserDTO>  response=userService.fetchUserDetailsById(mockUserId);
         assertEquals(HttpStatus.OK,response.getStatusCode());
         assertEquals(mockUserId,response.getBody().getUserId());
         // Verify that the repository method was called
         verify(userRepo, times(1)).findById(mockUserId);

    }

    @Test
    public void fetchUserDetailsBy_Non_Existing_Id()
    {

        //createa mock userId
        Integer mockUserId = 1;
        when(userRepo.findById(mockUserId)).thenReturn(Optional.empty());
        ResponseEntity<UserDTO>  response=userService.fetchUserDetailsById(mockUserId);
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
        assertNull(response.getBody());
        // Verify that the repository method was called
        verify(userRepo, times(1)).findById(mockUserId);



    }





}
