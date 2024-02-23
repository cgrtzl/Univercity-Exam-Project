package com.example.UnivercityProject.role;

import com.example.UnivercityProject.admin.repo.AdminRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceIMPL implements UserService {

    private final UserRepo repo;
    private final AdminRepo adminRepo;

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return adminRepo.findByName(username);
            }
        };
    }
}