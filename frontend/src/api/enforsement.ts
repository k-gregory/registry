import axios from 'axios';

export interface TopEnforcement {
    id: number;
    facility: string;
    date: Date;
    state: string;
}

function mapEnforcement(v: TopEnforcement): TopEnforcement {
    return {
        id: v.id,
        facility: 'Some facility',
        date: new Date(),
        state: 'In progress',
    };
}

export async function fetchTopEnforcements(): Promise<TopEnforcement[] | string> {
    const response = await axios.get('/api/enforcement/top');
    if (response.status !== 200) {
        return response.statusText;
    } else {
        return response.data.map(mapEnforcement);
    }
}
