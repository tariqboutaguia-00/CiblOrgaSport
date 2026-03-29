import api from "./api";

export interface CurrentUser {
    id: number;
    email: string;
    role: string;
    firstName?: string;
    lastName?: string;
    active?: boolean;
}

export const getCurrentUser = async (): Promise<CurrentUser> => {
    const response = await api.get("/users/me");
    return response.data.data;
};