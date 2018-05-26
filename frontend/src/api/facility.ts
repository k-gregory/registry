import axios from 'axios';

export interface Facility {
    id: number;
    name: string;
}

export function fetchFacilities(): Promise<Facility[]> {
    return axios.get('/api/facility')
        .then((response) => response.data);
}
