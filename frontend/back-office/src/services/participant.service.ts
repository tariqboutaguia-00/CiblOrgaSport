import api from "./api";
import type { ParticipantItem } from "../types/participant";

export const getParticipants = async (): Promise<ParticipantItem[]> => {
    const response = await api.get("/participants");
    return response.data.data;
};