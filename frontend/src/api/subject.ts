import axios from 'axios';

export interface Subject {
  id: number;
  name: string;
  inn: string;
}

export function fetchSubjects(): Promise<Subject[]> {
  return axios.get('/api/subject')
      .then((response) => response.data);
}

export function createSubject(s: Subject): Promise<Subject> {
  return axios.post(`/api/subject`, s)
      .then((response) => response.data);
}