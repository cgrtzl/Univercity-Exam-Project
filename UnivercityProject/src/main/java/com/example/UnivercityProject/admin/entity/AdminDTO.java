package com.example.UnivercityProject.admin.entity;

import com.example.UnivercityProject.role.Role;
import lombok.Data;

@Data
public class AdminDTO {
    private String id;
    private String name;
    private String password;
    private Role role;
}
