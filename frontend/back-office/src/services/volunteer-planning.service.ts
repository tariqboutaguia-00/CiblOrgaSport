import api from "./api";
import type { VolunteerPlanningItem } from "../types/volunteer-planning";

export const getVolunteerPlanningToday = async (
    volunteerId: number
): Promise<VolunteerPlanningItem[]> => {
    const response = await api.get(`/missions/volunteer/${volunteerId}/today`);
    return response.data.data;
};