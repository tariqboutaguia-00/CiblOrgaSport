import api from "./api";
import type { EventItem } from "../types/event";

export const getEvents = async (): Promise<EventItem[]> => {
    const response = await api.get("/events");
    return response.data.data;
};