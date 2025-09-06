package com.example.demo5.db;

import com.example.demo5.model.Admin;

public interface AdminDataAccessLayer {
    public Admin AdminLogin(String email , String password);
}
