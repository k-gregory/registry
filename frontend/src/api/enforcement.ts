import axios from 'axios';

export interface TopEnforcement {
    id: number;
    facility: string;
    date: Date;
    state: string;
}

export interface Enforcement {
    id: number;
    senderName: string;
    receiverName: string;
    startedAt: Date;
    state: string;
    facilityName: string;
}

export interface EnforcementS {
  senderId: number,
  receiverId: number,
  facilityId: number
}

function mapTopEnforcement(v: any): TopEnforcement {
    return {
        id: v.id,
        facility: v.facilityName,
        date: new Date(v.startedAt),
        state: v.state,
    };
}

function mapEnforcement(v: any): Enforcement {
  return {
    id: v.id,
    senderName: v.senderName,
    receiverName: v.receiverName,
    startedAt: new Date(v.startedAt),
    facilityName: v.facilityName,
    state: v.state
  }

}

export function createEnforcement(e: EnforcementS): Promise<Enforcement> {
  return axios.post(`/api/enforcement`, e)
      .then((response) => response.data);
}

export const fetchAllEnforcements = () => axios
    .get('/api/enforcement')
    .then((resp)=>resp.data.map(mapEnforcement));

export const fetchTopEnforcements = () => axios
    .get('/api/enforcement/top')
    .then((response) => response.data.map(mapTopEnforcement));

