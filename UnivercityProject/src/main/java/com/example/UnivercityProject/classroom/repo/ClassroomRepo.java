package com.example.UnivercityProject.classroom.repo;

import com.example.UnivercityProject.classroom.entity.Classroom;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassroomRepo extends MongoRepository<Classroom,String> {
    @Query(value = "SELECT COUNT(s) FROM Student s WHERE s.classroom.id = :classroomId")
    Long countStudentsByClassroom(@Param("classroomId") String classroomId);
}
