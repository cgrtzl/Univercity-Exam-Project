package com.example.UnivercityProject.exam.entity;

import com.example.UnivercityProject.classroom.entity.ClassroomDTO;
import lombok.Data;

import java.util.Date;

@Data
public class ExamDTO {

    private String id;
    private String name;
    private Date startDate;
    private Date endDate;
    private int duration;
    private ClassroomDTO classroom;
}
