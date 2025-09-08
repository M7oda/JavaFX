package com.example.demo5.service;

import com.example.demo5.db.AdminDataAccessLayer;
import com.example.demo5.model.Admin;
import com.example.demo5.model.ErrorDTO;
import com.example.demo5.model.LoginAdminResponse;
import com.example.demo5.model.LoginStudentResponse;

public class AdminLoginService {
    AdminDataAccessLayer adminDataAccessLayer;

    public AdminLoginService(AdminDataAccessLayer adminDataAccessLayer){
        this.adminDataAccessLayer = adminDataAccessLayer;
    }

    public LoginAdminResponse prepareAdminLogin(String email , String password){
        if (!email.isEmpty() && !password.isEmpty() && email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")){
            try {
                String hashPassword = PasswordHashingService.getHash(password);
                int id = adminDataAccessLayer.AdminLogin(email , hashPassword);
                if (id !=-1) {
                    return new LoginAdminResponse(adminDataAccessLayer.searchAdmin(id) , null);
                }else {
                    return new LoginAdminResponse(null , new ErrorDTO("Admin not found"));
                }
            }catch (Exception e){
                System.out.println(e);
            }
        } else if (email.isEmpty()) {
            return new LoginAdminResponse(null,new ErrorDTO("The email is empty"));
        } else if (password.isEmpty()) {
            return new LoginAdminResponse(null, new ErrorDTO("The password is empty"));
        } else if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
            return new LoginAdminResponse(null, new ErrorDTO("Invalid email"));
        }
        return null;
    }
}
