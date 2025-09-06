package com.example.demo5;
import com.example.demo5.db.SSMSStudentDataAccessLayerImpl;
import com.example.demo5.model.LoginResponse;
import com.example.demo5.service.StudentLoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

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
        LoginResponse studentLoginResponse = studentLoginService.prepareStudentLogin("", "s");
            String expectedError = "The email is empty";
            assertEquals(expectedError, studentLoginResponse.getErrorDTO().getErrorMessage());
        }

    @Test
    void emptyPasswordTest(){
        LoginResponse studentLoginResponse = studentLoginService.prepareStudentLogin("m@gmail.com","");
            String expectedError = "The password is empty";
            assertEquals(expectedError, studentLoginResponse.getErrorDTO().getErrorMessage());
    }
    @Test
    void wrongEmailTest(){
        LoginResponse studentLoginResponse = studentLoginService.prepareStudentLogin("a","s");
        String expectedError = "Invalid email";
        assertEquals(expectedError, studentLoginResponse.getErrorDTO().getErrorMessage());
    }
    @Test
    void emailOrPasswordWrongTest(){
        LoginResponse studentLoginResponse = studentLoginService.prepareStudentLogin("ancoices@gmail.com","151");
        String expectedError = "Student not found";
        assertEquals(expectedError,studentLoginResponse.getErrorDTO().getErrorMessage());
    }

    @Test
    void loginStudentSuccessfully(){
        SSMSStudentDataAccessLayerImpl studentDataAccessLayer = new SSMSStudentDataAccessLayerImpl();
        StudentLoginService loginService = new StudentLoginService(studentDataAccessLayer);
        LoginResponse studentLoginResponse = loginService.prepareStudentLogin("sara@gmail.com","sara");
        assertNotNull(studentLoginResponse);
    }
}
