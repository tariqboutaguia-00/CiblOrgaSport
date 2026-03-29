import api from "./api";
import type { MissionItem } from "../types/mission";

export const getMissions = async (): Promise<MissionItem[]> => {
    const response = await api.get("/missions");
    return response.data.data;
};