import { Exam } from "./exam.model";
import { Student } from "./student.model";

export interface Result{
    id?: string;
    score: number;
    exam: Exam;
    student: Student;
}