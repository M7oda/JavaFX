package com.example.demo5.service;

import com.example.demo5.db.AdminDataAccessLayer;
import com.example.demo5.model.Admin;
import com.example.demo5.model.ErrorDTO;
import com.example.demo5.model.LoginResponse;

public class AdminLoginService {
    AdminDataAccessLayer adminDataAccessLayer;

    public AdminLoginService(AdminDataAccessLayer adminDataAccessLayer){
        this.adminDataAccessLayer = adminDataAccessLayer;
    }

    public LoginResponse prepareAdminLogin(String email , String password){
        if (!email.isEmpty() && !password.isEmpty() && email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")){
            try {
                String hashPassword = PasswordHashingService.getHash(password);
                Admin admin = adminDataAccessLayer.AdminLogin(email , hashPassword);
                if (admin !=null) {
                    return new LoginResponse(admin , null);
                }else {
                    return new LoginResponse(null , new ErrorDTO("Admin not found"));
                }
            }catch (Exception e){
                System.out.println(e);
            }
        } else if (email.isEmpty()) {
            return new LoginResponse(null,new ErrorDTO("The email is empty"));
        } else if (password.isEmpty()) {
            return new LoginResponse(null, new ErrorDTO("The password is empty"));
        } else if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
            return new LoginResponse(null, new ErrorDTO("Invalid email"));
        }
        return null;
    }
}
