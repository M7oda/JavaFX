package com.example.demo5.service;

import com.example.demo5.db.StudentDataAccessLayer;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHashingService {
    private static byte[] getSHA(String password) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        return messageDigest.digest(password.getBytes(StandardCharsets.UTF_8));
    }
    public static String getHash(String password) throws NoSuchAlgorithmException{
        byte[] hash = getSHA(password);
        BigInteger number = new BigInteger(1,hash);
         StringBuilder hexString = new StringBuilder(number.toString(16));
         while (hexString.length() < 64){
             hexString.insert(0,'0');
         }
         return hexString.toString();
    }
}
