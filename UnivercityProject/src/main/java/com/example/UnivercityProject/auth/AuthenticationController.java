package com.example.UnivercityProject.auth;

import com.example.UnivercityProject.auth.dto.AdminSignInReq;
import com.example.UnivercityProject.auth.dto.JwtAuthenticationResponse;
import com.example.UnivercityProject.auth.dto.RefreshTokenRequest;
import com.example.UnivercityProject.auth.dto.SignInRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    @Autowired
    private final AuthService authService;

    @PostMapping("/student-login")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SignInRequest signInRequest){
        return ResponseEntity.ok(authService.studentSignin(signInRequest));
    }

    @PostMapping("/admin-login")
    public ResponseEntity<JwtAuthenticationResponse> login(@RequestBody AdminSignInReq adminSignInReq) {
        return ResponseEntity.ok(authService.adminSignin(adminSignInReq));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest){
        return ResponseEntity.ok(authService.refreshAdminToken(refreshTokenRequest));
    }
}
