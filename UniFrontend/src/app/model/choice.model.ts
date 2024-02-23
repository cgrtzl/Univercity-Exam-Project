import { Question } from "./question.model";

export interface Choice{
    id?: string;
    name: string;
    description: string;
    correct: boolean;
    question: Question;
}