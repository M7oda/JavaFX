package com.example.demo5;

import com.example.demo5.db.SSMSStudentDataAccessLayerImpl;
import com.example.demo5.model.ErrorDTO;
import com.example.demo5.service.AddNewStudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddNewStudentServiceTest {
    private SSMSStudentDataAccessLayerImpl ssmsStudentDataAccessLayer;
    private AddNewStudentService addNewStudentService;

    @BeforeEach
    public void setUp(){
        ssmsStudentDataAccessLayer = mock(SSMSStudentDataAccessLayerImpl.class);
        addNewStudentService = new AddNewStudentService(ssmsStudentDataAccessLayer);
    }

    @Test
    void emptyNameTest(){

        ErrorDTO errorDTO = addNewStudentService.prepareToCreateStudent("" , "m@gmail.com","m");
        String expectedError = errorDTO.getErrorMessage();
        assertEquals(expectedError ,errorDTO.getErrorMessage());
    }
    @Test
    void emptyEmailTest(){
        ErrorDTO errorDTO = addNewStudentService.prepareToCreateStudent("m" , "","m");
        String expectedError = errorDTO.getErrorMessage();
        assertEquals(expectedError ,errorDTO.getErrorMessage());
    }
    @Test
    void emptyPasswordTest(){
        ErrorDTO errorDTO = addNewStudentService.prepareToCreateStudent("m" , "m@gmail.com","");
        String expectedError = errorDTO.getErrorMessage();
        assertEquals(expectedError ,errorDTO.getErrorMessage());
    }
    @Test
    void wrongEmailTest(){
        ErrorDTO errorDTO = addNewStudentService.prepareToCreateStudent("a" , "a","m");
        String expectedError = errorDTO.getErrorMessage();
        assertEquals(expectedError ,errorDTO.getErrorMessage());
    }
    @Test
    void wrongNameTest(){
        ErrorDTO errorDTO = addNewStudentService.prepareToCreateStudent("0" , "m@gmail.com","m");
        String expectedError = errorDTO.getErrorMessage();
        assertEquals(expectedError ,errorDTO.getErrorMessage());
    }
    @Test
    void saveNewStudentSuccessfullyTest(){
        ErrorDTO errorDTO = addNewStudentService.prepareToCreateStudent("n" , "m@gmail.com","m");
        String expectedError = errorDTO.getErrorMessage();
        assertEquals(expectedError ,errorDTO.getErrorMessage());
    }
}