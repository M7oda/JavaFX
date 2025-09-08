package com.example.demo5.model;

public class LoginAdminResponse {
    private AdminDTO adminDTO;
    private ErrorDTO errorDTO;

    public LoginAdminResponse(AdminDTO adminDTO,ErrorDTO errorDTO){
        this.adminDTO = adminDTO;
        this.errorDTO = errorDTO;
    }

    public AdminDTO getAdminDTO() {
        return adminDTO;
    }

    public ErrorDTO getErrorDTO() {
        return errorDTO;
    }
}
