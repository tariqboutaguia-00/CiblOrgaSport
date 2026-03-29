import api from "./api";
import type { ResultItem } from "../types/result";

export const getResults = async (): Promise<ResultItem[]> => {
    const response = await api.get("/results");
    return response.data.data;
};