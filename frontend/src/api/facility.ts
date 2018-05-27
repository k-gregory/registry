import axios from 'axios';

export interface Facility {
    id: number;
    name: string;
    headName: string;
    headId: number | null;
}

export function fetchFacilities(): Promise<Facility[]> {
    return axios.get('/api/facility')
        .then((response) => response.data);
}

export function updateFacility(id: number, name: string, headId: number): Promise<Facility> {
    return axios.put(`/api/facility/${id}`, {name, facilityHeadId: headId})
        .then((response) => response.data);
}

export function createFacility(name: string): Promise<Facility> {
    return axios.post(`/api/facility`, {name})
        .then((response) => response.data);
}
