import api from "./api";
import type { StatisticsData } from "../types/statistics";

export const getStatistics = async (): Promise<StatisticsData> => {
    const response = await api.get("/statistics");
    return response.data.data;
};