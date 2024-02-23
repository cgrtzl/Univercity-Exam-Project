package com.example.UnivercityProject.auth;

import com.example.UnivercityProject.admin.entity.Admin;
import com.example.UnivercityProject.student.entity.Student;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface JwtService {
    String extractUsername(String token);
    String generateToken(UserDetails userDetails);
    boolean isTokenValid(String token, UserDetails userDetails);
    String generateRefreshAdminToken(Map<String, Object> extraClaims, Admin userDetails);
    String generateRefreshStudentToken(Map<String, Object> extraClaims, Student userDetails);
}
