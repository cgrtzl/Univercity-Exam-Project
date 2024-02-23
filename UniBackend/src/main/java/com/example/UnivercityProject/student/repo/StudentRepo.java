package com.example.UnivercityProject.student.repo;

import com.example.UnivercityProject.classroom.entity.Classroom;
import com.example.UnivercityProject.role.Role;
import com.example.UnivercityProject.student.entity.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepo extends MongoRepository<Student,String> {
    Student findByEmail(String studentEmail);
    Student findByRole(Role role);
    List<Student> findByClassroom(Classroom classroom);

    Optional<Student> findByNumber(String username);
}
