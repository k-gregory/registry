import axios from 'axios';

export interface Executant {
    id: number;
    firstName: string;
    middleName: string;
    lastName: string;
    phoneNumber: string;
    facilityName: string;
    facilityId: number;
}

export function getFullName(executant: Executant): string {
    return `${executant.firstName} ${executant.middleName} ${executant.lastName}`;
}

export function fetchExecutants(): Promise<Executant[]> {
    return axios
        .get('/api/executant')
        .then((response) => response.data);
}
