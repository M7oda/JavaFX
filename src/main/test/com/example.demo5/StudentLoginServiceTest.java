package com.example.demo5;
import com.example.demo5.db.SSMSStudentDataAccessLayerImpl;
import com.example.demo5.model.LoginStudentResponse;
import com.example.demo5.service.StudentLoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StudentLoginServiceTest {
    private StudentLoginService studentLoginService;
    private SSMSStudentDataAccessLayerImpl ssmsStudentDataAccessLayer;

    @BeforeEach
    void setUp() {
        ssmsStudentDataAccessLayer = mock(SSMSStudentDataAccessLayerImpl.class);
        studentLoginService = new StudentLoginService(ssmsStudentDataAccessLayer);
    }

    @Test
    void emptyEmailTest() {
        LoginStudentResponse studentLoginStudentResponse = studentLoginService.prepareStudentLogin("", "s");
            String expectedError = "The email is empty";
            assertEquals(expectedError, studentLoginStudentResponse.getErrorDTO().getErrorMessage());
        }

    @Test
    void emptyPasswordTest(){
        LoginStudentResponse studentLoginStudentResponse = studentLoginService.prepareStudentLogin("m@gmail.com","");
            String expectedError = "The password is empty";
            assertEquals(expectedError, studentLoginStudentResponse.getErrorDTO().getErrorMessage());
    }
    @Test
    void wrongEmailTest(){
        LoginStudentResponse studentLoginStudentResponse = studentLoginService.prepareStudentLogin("a","s");
        String expectedError = "Invalid email";
        assertEquals(expectedError, studentLoginStudentResponse.getErrorDTO().getErrorMessage());
    }
    @Test
    void emailOrPasswordWrongTest(){
        LoginStudentResponse studentLoginStudentResponse = studentLoginService.prepareStudentLogin("ancoices@gmail.com","151");
        String expectedError = "Student not found";
        assertEquals(expectedError, studentLoginStudentResponse.getErrorDTO().getErrorMessage());
    }

    @Test
    void loginStudentSuccessfully(){
        when(ssmsStudentDataAccessLayer.studentLogin("sara@gmail.com","sara")!=-1).thenReturn(true);

    }
}
