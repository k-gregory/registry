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

export function updateExecutant(id: number,
                                firstName: string,
                                middleName: string,
                                lastName: string): Promise<Executant> {
    return axios
        .put(`/api/executant/${id}`, {firstName, middleName, lastName})
        .then((response) => response.data);
}

export function createExecutant(executant: Executant): Promise<Executant> {
    const request = {
        firstName: executant.firstName,
        middleName: executant.middleName,
        lastName: executant.lastName,
        phoneNumber:  executant.phoneNumber,
        facilityId: executant.facilityId,
    };

    return axios.post('/api/executant', request)
        .then((response) => response.data);
}
