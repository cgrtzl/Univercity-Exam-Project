package com.example.UnivercityProject.admin.entity;

public class AdminMapperIMPL {
    public static AdminDTO adminToDTO(Admin admin) {

        AdminDTO adminDTO = new AdminDTO();

        adminDTO.setId(admin.getId());
        adminDTO.setName(admin.getName());
        adminDTO.setPassword(admin.getPassword());
        adminDTO.setRole(admin.getRole());

        return adminDTO;
    }

    public static Admin adminToDocument(AdminDTO adminDTO) {

        Admin admin = new Admin();

        admin.setId(adminDTO.getId());
        admin.setName(adminDTO.getName());
        admin.setPassword(adminDTO.getPassword());
        admin.setRole(adminDTO.getRole());

        return admin;
    }
}