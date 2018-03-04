import axios from 'axios';

export interface TopEnforcement {
    id: number;
    facility: string;
    date: Date;
    state: string;
}

function mapEnforcement(v: any): TopEnforcement {
    return {
        id: v.id,
        facility: v.facilityName,
        date: new Date(v.startedAt),
        state: v.state,
    };
}

export async function fetchTopEnforcements(): Promise<TopEnforcement[]> {
    const response = await axios.get('/api/enforcement/top');
    return response.data.map(mapEnforcement);
}
