import api from "./api";
import type { Competition } from "../types/competition";

export const getCompetitions = async (): Promise<Competition[]> => {
    const response = await api.get("/competitions");
    return response.data.data;
};