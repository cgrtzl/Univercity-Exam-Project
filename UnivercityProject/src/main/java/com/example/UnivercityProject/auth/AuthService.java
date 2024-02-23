package com.example.UnivercityProject.auth;

import com.example.UnivercityProject.auth.dto.AdminSignInReq;
import com.example.UnivercityProject.auth.dto.JwtAuthenticationResponse;
import com.example.UnivercityProject.auth.dto.RefreshTokenRequest;
import com.example.UnivercityProject.auth.dto.SignInRequest;

public interface AuthService {

    JwtAuthenticationResponse studentSignin(SignInRequest signInRequest);

    JwtAuthenticationResponse adminSignin(AdminSignInReq adminSignInReq);

    JwtAuthenticationResponse refreshStudentToken(RefreshTokenRequest refreshStudentTokenRequest);

    JwtAuthenticationResponse refreshAdminToken(RefreshTokenRequest refreshAdminTokenRequest);
}
