package com.codedecode.userInfo.controller;

import com.codedecode.userInfo.dto.UserDTO;
import com.codedecode.userInfo.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class UserControllerTest {

    @Mock
    UserService userService;


    @InjectMocks
    UserController userController;


    @BeforeEach
    public void setUp() {
        AutoCloseable autoCloseable = MockitoAnnotations.openMocks(this);//in order for Mock and InjectMocks annotations to take effect, you need to call MockitoAnnotations.openMocks(this);
    }

    @Test
    public void addUser()
    {
        UserDTO userData=new UserDTO(1,"Rahul","Password","LaxmiChock","pune");

        // Mock the service behavior
        when(userService.addUser(userData)).thenReturn(userData);

        // Call the controller method
        ResponseEntity<UserDTO> response = userController.addUser(userData);

        // Verify the response
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(userData, response.getBody());

        // Verify that the service method was called
        verify(userService, times(1)).addUser(userData);

    }



     @Test
    public void fetchUserDetailsById()
     {
         // Create a mock restaurant ID
         Integer mockUserId = 1;

         // Create a mock user to be returned by the service
         UserDTO mockUser =new UserDTO(1,"Rahul","Password","LaxmiChock","pune");


         // Mock the service behavior
         when(userService.fetchUserDetailsById(mockUserId)).thenReturn(new ResponseEntity<>(mockUser, HttpStatus.OK));


         // Call the controller method
         ResponseEntity<UserDTO> response = userController.fetchUserDetailsById(mockUserId);

         // Verify the response
         assertEquals(HttpStatus.OK, response.getStatusCode());
         assertEquals(mockUser, response.getBody());

         // Verify that the service method was called
         verify(userService, times(1)).fetchUserDetailsById(mockUserId);

     }



}
