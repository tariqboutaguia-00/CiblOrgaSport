import api from "./api";
import type { NotificationItem } from "../types/notification";

export const getMyNotifications = async (): Promise<NotificationItem[]> => {
    const response = await api.get("/notifications/me");
    return response.data.data;
};

export const markNotificationAsRead = async (
    notificationId: number
): Promise<void> => {
    await api.patch(`/notifications/${notificationId}/read`);
};