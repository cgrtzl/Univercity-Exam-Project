package com.example.UnivercityProject.auth;

import com.example.UnivercityProject.admin.entity.Admin;
import com.example.UnivercityProject.admin.repo.AdminRepo;
import com.example.UnivercityProject.auth.dto.AdminSignInReq;
import com.example.UnivercityProject.auth.dto.JwtAuthenticationResponse;
import com.example.UnivercityProject.auth.dto.RefreshTokenRequest;
import com.example.UnivercityProject.auth.dto.SignInRequest;
import com.example.UnivercityProject.student.entity.Student;
import com.example.UnivercityProject.student.repo.StudentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthServiceIMPL implements AuthService{
    private final AdminRepo adminRepo;
    private final StudentRepo studentRepo;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public JwtAuthenticationResponse studentSignin(SignInRequest signInRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                signInRequest.getEmail(),
                signInRequest.getPassword()));

        var user = studentRepo.findByEmail(signInRequest.getEmail());
        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshStudentToken(new HashMap<>(),user);

        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);
        return jwtAuthenticationResponse;

    }

    public JwtAuthenticationResponse adminSignin(AdminSignInReq adminSignInReq){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                adminSignInReq.getName(),
                adminSignInReq.getPassword()));

        var user = adminRepo.findByName(adminSignInReq.getName());
        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshAdminToken(new HashMap<>(),user);

        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);
        return jwtAuthenticationResponse;
    }

    public JwtAuthenticationResponse refreshStudentToken(RefreshTokenRequest refreshTokenRequest){
        String userEmail = jwtService.extractUsername(refreshTokenRequest.getToken());
        Student user = studentRepo.findByEmail(userEmail);
        if (jwtService.isTokenValid(refreshTokenRequest.getToken(), user)){
            var jwt = jwtService.generateToken(user);

            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());
            return jwtAuthenticationResponse;
        }
        return null;
    }

    public JwtAuthenticationResponse refreshAdminToken(RefreshTokenRequest refreshTokenRequest){
        String userName = jwtService.extractUsername(refreshTokenRequest.getToken());
        Admin user = adminRepo.findByName(userName);
        if (jwtService.isTokenValid(refreshTokenRequest.getToken(), user)){
            var jwt = jwtService.generateToken(user);

            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());
            return jwtAuthenticationResponse;
        }
        return null;
    }
}
