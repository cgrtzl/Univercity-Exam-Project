import { Classroom } from "./classroom.model";

export interface Student {
    id?: string;
    name: string;
    number: string;
    password: string;
    classroom: Classroom;
  }
