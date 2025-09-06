package com.example.demo5;

import com.example.demo5.db.SSMSTeacherDataAccessLayerImpl;
import com.example.demo5.model.ErrorDTO;
import com.example.demo5.service.AddNewTeacherService;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;


public class AddNewTeacherServiceTest {
    SSMSTeacherDataAccessLayerImpl sqlLiteTeacherDataAccessLayer = new SSMSTeacherDataAccessLayerImpl();
    AddNewTeacherService addNewTeacherService = new AddNewTeacherService(sqlLiteTeacherDataAccessLayer);

    @Test
    void emptyNameTest(){
        ErrorDTO errorDTO = addNewTeacherService.prepareToCreateTeacher("" , "01070230174" , "m@mail.com" , "m");
        String expectedError = errorDTO.getErrorMessage();
        assertEquals(expectedError,errorDTO.getErrorMessage());
    }
    @Test
    void emptyPhoneNOTest(){
        ErrorDTO errorDTO = addNewTeacherService.prepareToCreateTeacher("m" , "" , "m@mail.com" , "m");
        String expectedError = errorDTO.getErrorMessage();
        assertEquals(expectedError,errorDTO.getErrorMessage());
    }
    @Test
    void emptyEmailTest(){
        ErrorDTO errorDTO = addNewTeacherService.prepareToCreateTeacher("m" , "01070230174" , "" , "m");
        String expectedError = errorDTO.getErrorMessage();
        assertEquals(expectedError,errorDTO.getErrorMessage());
    }
    @Test
    void emptyPasswordTest(){
        ErrorDTO errorDTO = addNewTeacherService.prepareToCreateTeacher("d" , "01070230174" , "m@mail.com" , "");
        String expectedError = errorDTO.getErrorMessage();
        assertEquals(expectedError,errorDTO.getErrorMessage());
    }
    @Test
    void wrongEmailTest(){
        ErrorDTO errorDTO = addNewTeacherService.prepareToCreateTeacher("f" , "01070230174" , "m" , "m");
        String expectedError = errorDTO.getErrorMessage();
        assertEquals(expectedError,errorDTO.getErrorMessage());
    }
    @Test
    void wrongPhoneNOInputTest(){
        ErrorDTO errorDTO = addNewTeacherService.prepareToCreateTeacher("m" , "re" , "m@mail.com" , "m");
        String expectedError = errorDTO.getErrorMessage();
        assertEquals(expectedError,errorDTO.getErrorMessage());
    }
    @Test
    void wrongNameTest(){
        ErrorDTO errorDTO = addNewTeacherService.prepareToCreateTeacher("0" , "1" , "m@mail.com" , "m");
        String expectedError = errorDTO.getErrorMessage();
        assertEquals(expectedError,errorDTO.getErrorMessage());
    }
    @Test
    void wrongPhoneNOLengthTest(){
        ErrorDTO errorDTO = addNewTeacherService.prepareToCreateTeacher("m" , "1" , "m@mail.com" , "m");
        String expectedError = errorDTO.getErrorMessage();
        assertEquals(expectedError,errorDTO.getErrorMessage());
    }
    @Test
    void saveNewTeacherSuccessfullyTest(){
        ErrorDTO errorDTO = addNewTeacherService.prepareToCreateTeacher("m" , "01070230174" , "m@mail.com" , "m");
        String expectedError = errorDTO.getErrorMessage();
        assertEquals(expectedError,errorDTO.getErrorMessage());
    }
}
