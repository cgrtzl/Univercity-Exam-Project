package com.example.UnivercityProject;

import com.example.UnivercityProject.admin.entity.Admin;
import com.example.UnivercityProject.admin.repo.AdminRepo;
import com.example.UnivercityProject.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class UnivercityProjectApplication implements CommandLineRunner {

	@Autowired
	private AdminRepo adminRepo;

	public static void main(String[] args) {
		SpringApplication.run(UnivercityProjectApplication.class, args);
		System.out.println("Working..");

	}

	public void run(String... args){
		Admin admin1 =adminRepo.findByRole(Role.ADMIN);
		if (null == admin1){
			System.out.println("a");
			Admin admin	 = new Admin();
			admin.setName("admin");
			admin.setPassword(new BCryptPasswordEncoder().encode("admin"));
			admin.setRole(Role.ADMIN);
			adminRepo.save(admin);
		}
	}

}
