import { useEffect, useState } from "react";
import {
  createNotification,
  getMyNotifications,
  markNotificationAsRead,
} from "../services/notification.service";
import type { NotificationItem } from "../types/notification";
import { useAuth } from "../context/AuthContext";

function formatDate(date?: string) {
  if (!date) {
    return "-";
  }

  const parsedDate = new Date(date);

  if (Number.isNaN(parsedDate.getTime())) {
    return date;
  }

  return parsedDate.toLocaleString("fr-FR");
}

function getNotificationTitle(notification: NotificationItem) {
  return notification.title || notification.type || "Notification";
}

function getNotificationMessage(notification: NotificationItem) {
  return notification.message || "Aucun message disponible.";
}

export default function Notifications() {
  const { user } = useAuth();

  const [notifications, setNotifications] = useState<NotificationItem[]>([]);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState("");
  const [successMessage, setSuccessMessage] = useState("");
  const [updatingId, setUpdatingId] = useState<number | null>(null);

  const [type, setType] = useState("sport");
  const [message, setMessage] = useState("");
  const [isSubmitting, setIsSubmitting] = useState(false);

  const canCreateNotification =
    user?.role === "ADMIN" || user?.role === "DEPLOYMENT_MANAGER";

  const fetchNotifications = async () => {
    try {
      setIsLoading(true);
      setError("");

      const data = await getMyNotifications();
      setNotifications(Array.isArray(data) ? data : []);
    } catch (err) {
      setError("Impossible de charger les notifications.");
    } finally {
      setIsLoading(false);
    }
  };

  useEffect(() => {
    fetchNotifications();
  }, []);

  const handleCreateNotification = async (
    event: React.FormEvent<HTMLFormElement>
  ) => {
    event.preventDefault();

    setError("");
    setSuccessMessage("");

    if (!message.trim()) {
      setError("Le message de la notification est obligatoire.");
      return;
    }

    try {
      setIsSubmitting(true);

      await createNotification({
        type,
        message: message.trim(),
      });

      setSuccessMessage("Notification créée avec succès.");
      setMessage("");

      await fetchNotifications();
    } catch (err) {
      setError("Impossible de créer la notification.");
    } finally {
      setIsSubmitting(false);
    }
  };

  const handleMarkAsRead = async (notificationId: number) => {
    try {
      setError("");
      setSuccessMessage("");
      setUpdatingId(notificationId);

      await markNotificationAsRead(notificationId);

      setNotifications((previousNotifications) =>
        previousNotifications.map((notification) =>
          notification.id === notificationId
            ? { ...notification, read: true }
            : notification
        )
      );
    } catch (err) {
      setError("Impossible de marquer cette notification comme lue.");
    } finally {
      setUpdatingId(null);
    }
  };

  return (
    <div className="space-y-6">
      <div>
        <p className="text-sm font-medium text-brand-600">Notifications</p>
        <h2 className="text-2xl font-bold text-gray-800 dark:text-white">
          Notifications
        </h2>
        <p className="mt-2 text-sm text-gray-500 dark:text-gray-400">
          Cette page permet de créer une notification et d’afficher les
          notifications de l’utilisateur connecté.
        </p>
      </div>

      {canCreateNotification && (
        <div className="rounded-2xl border border-gray-200 bg-white p-5 shadow-sm dark:border-gray-800 dark:bg-gray-900">
          <h3 className="text-lg font-semibold text-gray-800 dark:text-white">
            Créer une notification
          </h3>
          <p className="mt-2 text-sm text-gray-500 dark:text-gray-400">
            La notification sera envoyée aux utilisateurs abonnés au type choisi.
          </p>

          <form onSubmit={handleCreateNotification} className="mt-5 space-y-4">
            <div>
              <label
                htmlFor="notification-type"
                className="mb-2 block text-sm font-medium text-gray-700 dark:text-gray-300"
              >
                Type
              </label>
              <select
                id="notification-type"
                value={type}
                onChange={(event) => setType(event.target.value)}
                className="w-full rounded-xl border border-gray-300 px-4 py-3 text-sm outline-none focus:border-brand-500 dark:border-gray-700 dark:bg-gray-800 dark:text-white"
              >
                <option value="sport">Sport</option>
                <option value="sécurité">Sécurité</option>
                <option value="fan zone">Fan zone</option>
              </select>
            </div>

            <div>
              <label
                htmlFor="notification-message"
                className="mb-2 block text-sm font-medium text-gray-700 dark:text-gray-300"
              >
                Message
              </label>
              <textarea
                id="notification-message"
                value={message}
                onChange={(event) => setMessage(event.target.value)}
                rows={4}
                placeholder="Saisissez le message à envoyer..."
                className="w-full rounded-xl border border-gray-300 px-4 py-3 text-sm outline-none focus:border-brand-500 dark:border-gray-700 dark:bg-gray-800 dark:text-white"
              />
            </div>

            <button
              type="submit"
              disabled={isSubmitting}
              className="rounded-xl bg-brand-500 px-4 py-3 text-sm font-semibold text-white hover:bg-brand-600 disabled:cursor-not-allowed disabled:opacity-70"
            >
              {isSubmitting ? "Envoi..." : "Créer la notification"}
            </button>
          </form>
        </div>
      )}

      {successMessage && (
        <div className="rounded-2xl border border-green-200 bg-green-50 p-4 text-sm text-green-700 shadow-sm dark:border-green-500/20 dark:bg-green-500/10 dark:text-green-300">
          {successMessage}
        </div>
      )}

      {isLoading && (
        <div className="rounded-2xl border border-gray-200 bg-white p-5 text-sm text-gray-600 shadow-sm dark:border-gray-800 dark:bg-gray-900 dark:text-gray-300">
          Chargement des notifications...
        </div>
      )}

      {!isLoading && error && (
        <div className="rounded-2xl border border-red-200 bg-red-50 p-5 text-sm text-red-700 shadow-sm dark:border-red-500/20 dark:bg-red-500/10 dark:text-red-300">
          {error}
        </div>
      )}

      {!isLoading && !error && notifications.length === 0 && (
        <div className="rounded-2xl border border-gray-200 bg-white p-5 text-sm text-gray-600 shadow-sm dark:border-gray-800 dark:bg-gray-900 dark:text-gray-300">
          Aucune notification trouvée.
        </div>
      )}

      {!isLoading && !error && notifications.length > 0 && (
        <div className="grid grid-cols-1 gap-4">
          {notifications.map((notification) => (
            <article
              key={notification.id}
              className={`rounded-2xl border p-5 shadow-sm ${notification.read
                  ? "border-gray-200 bg-white dark:border-gray-800 dark:bg-gray-900"
                  : "border-brand-200 bg-brand-50 dark:border-brand-500/20 dark:bg-brand-500/10"
                }`}
            >
              <div className="flex flex-col gap-4 md:flex-row md:items-start md:justify-between">
                <div className="space-y-2">
                  <div className="flex flex-wrap items-center gap-2">
                    <h3 className="text-lg font-semibold text-gray-800 dark:text-white">
                      {getNotificationTitle(notification)}
                    </h3>

                    <span
                      className={`rounded-full px-3 py-1 text-xs font-medium ${notification.read
                          ? "bg-gray-100 text-gray-600 dark:bg-gray-800 dark:text-gray-300"
                          : "bg-brand-500 text-white"
                        }`}
                    >
                      {notification.read ? "Lue" : "Non lue"}
                    </span>
                  </div>

                  <p className="text-sm text-gray-600 dark:text-gray-300">
                    {getNotificationMessage(notification)}
                  </p>

                  <p className="text-xs text-gray-500 dark:text-gray-400">
                    Type : {notification.type || "-"}
                  </p>

                  <p className="text-xs text-gray-500 dark:text-gray-400">
                    Date : {formatDate(notification.createdAt)}
                  </p>
                </div>

                <div>
                  {!notification.read && (
                    <button
                      type="button"
                      onClick={() => handleMarkAsRead(notification.id)}
                      disabled={updatingId === notification.id}
                      className="rounded-xl bg-brand-500 px-4 py-2 text-sm font-medium text-white hover:bg-brand-600 disabled:cursor-not-allowed disabled:opacity-70"
                    >
                      {updatingId === notification.id
                        ? "Mise à jour..."
                        : "Marquer comme lue"}
                    </button>
                  )}
                </div>
              </div>
            </article>
          ))}
        </div>
      )}
    </div>
  );
}