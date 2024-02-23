package com.example.UnivercityProject.admin.repo;

import com.example.UnivercityProject.admin.entity.Admin;
import com.example.UnivercityProject.role.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepo extends MongoRepository<Admin, String> {

    Admin findByName(String name);
    Admin findByRole(Role role);
}
