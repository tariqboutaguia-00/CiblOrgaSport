import api from './api';

export const getPublicSchedule = async () => {
    const response = await api.get('/events/public-schedule');
    return response.data.data;
};