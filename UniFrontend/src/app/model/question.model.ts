import { Exam } from "./exam.model";

export interface Question {
    id?: string;
    questionNumber: number;
    title: string;
    exam: Exam;
  }
