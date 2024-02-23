import { Classroom } from "./classroom.model";

export interface Exam {
    id?: string;
    name: string;
    startDate: Date;
    endDate: Date;
    duration: number;
    classroom: Classroom;
  }
