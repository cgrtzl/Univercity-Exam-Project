package com.example.UnivercityProject.role;

import com.example.UnivercityProject.student.entity.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepo extends MongoRepository<Student, Long> {
    Student findByNumber(String number);
}
