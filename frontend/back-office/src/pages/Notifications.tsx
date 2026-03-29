import { useEffect, useState } from "react";
import {
  getMyNotifications,
  markNotificationAsRead,
  createNotification,
} from "../services/notification.service";
import type { NotificationItem } from "../types/notification";

export default function Notifications() {
  const [notifications, setNotifications] = useState<NotificationItem[]>([]);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState("");

  const [type, setType] = useState("sport");
  const [message, setMessage] = useState("");
  const [isSubmitting, setIsSubmitting] = useState(false);

  const fetchNotifications = async () => {
    try {
      setIsLoading(true);
      const data = await getMyNotifications();
      setNotifications(data);
    } catch {
      setError("Erreur chargement notifications");
    } finally {
      setIsLoading(false);
    }
  };

  useEffect(() => {
    fetchNotifications();
  }, []);

  const handleCreate = async (e: React.FormEvent) => {
    e.preventDefault();

    try {
      setIsSubmitting(true);

      await createNotification({ type, message });

      setMessage("");

      await fetchNotifications();
    } catch {
      setError("Erreur création notification");
    } finally {
      setIsSubmitting(false);
    }
  };

  const handleRead = async (id: number) => {
    await markNotificationAsRead(id);
    fetchNotifications();
  };

  return (
    <div className="space-y-6">
      <h2 className="text-2xl font-bold">Notifications</h2>

      {/* FORMULAIRE ADMIN */}
      <form
        onSubmit={handleCreate}
        className="p-4 border rounded-xl bg-white space-y-4"
      >
        <h3 className="font-semibold">Créer une notification</h3>

        <select
          value={type}
          onChange={(e) => setType(e.target.value)}
          className="w-full border p-2 rounded"
        >
          <option value="sport">Sport</option>
          <option value="sécurité">Sécurité</option>
          <option value="fan zone">Fan Zone</option>
        </select>

        <textarea
          placeholder="Message..."
          value={message}
          onChange={(e) => setMessage(e.target.value)}
          className="w-full border p-2 rounded"
        />

        <button
          type="submit"
          disabled={isSubmitting}
          className="bg-blue-600 text-white px-4 py-2 rounded"
        >
          {isSubmitting ? "Envoi..." : "Créer"}
        </button>
      </form>

      {/* LISTE */}
      {isLoading && <p>Chargement...</p>}
      {error && <p className="text-red-500">{error}</p>}

      {notifications.map((n) => (
        <div
          key={n.id}
          className={`p-4 border rounded ${n.read ? "bg-gray-100" : "bg-blue-50"
            }`}
        >
          <p className="font-semibold">{n.type}</p>
          <p>{n.message}</p>

          {!n.read && (
            <button
              onClick={() => handleRead(n.id)}
              className="text-sm text-blue-600 mt-2"
            >
              Marquer comme lu
            </button>
          )}
        </div>
      ))}
    </div>
  );
}