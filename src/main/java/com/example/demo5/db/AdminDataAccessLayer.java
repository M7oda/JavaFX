package com.example.demo5.db;

import com.example.demo5.model.Admin;
import com.example.demo5.model.AdminDTO;

public interface AdminDataAccessLayer {
    public int AdminLogin(String email , String password);
    public AdminDTO searchAdmin (int id);

}
