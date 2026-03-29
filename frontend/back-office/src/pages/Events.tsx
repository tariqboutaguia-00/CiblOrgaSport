import { useEffect, useMemo, useState } from "react";
import { getEvents } from "../services/event.service";
import type { EventItem } from "../types/event";

function formatValue(value: unknown): string {
  if (value === null || value === undefined || value === "") {
    return "-";
  }

  if (typeof value === "string") {
    const trimmed = value.trim();

    if (!trimmed) {
      return "-";
    }

    const asDate = new Date(trimmed);
    if (!Number.isNaN(asDate.getTime()) && trimmed.includes("-")) {
      return asDate.toLocaleString("fr-FR");
    }

    return trimmed;
  }

  if (typeof value === "number" || typeof value === "boolean") {
    return String(value);
  }

  if (Array.isArray(value)) {
    return value.length > 0 ? `${value.length} élément(s)` : "-";
  }

  if (typeof value === "object") {
    const objectValue = value as Record<string, unknown>;

    if ("id" in objectValue && objectValue.id !== null && objectValue.id !== undefined) {
      return `#${String(objectValue.id)}`;
    }

    if ("name" in objectValue && typeof objectValue.name === "string") {
      return objectValue.name;
    }

    if ("title" in objectValue && typeof objectValue.title === "string") {
      return objectValue.title;
    }

    return "Objet lié";
  }

  return String(value);
}

function formatColumnLabel(key: string): string {
  const withSpaces = key.replace(/([A-Z])/g, " $1");
  return withSpaces.charAt(0).toUpperCase() + withSpaces.slice(1);
}

export default function Events() {
  const [events, setEvents] = useState<EventItem[]>([]);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    const fetchEvents = async () => {
      try {
        setIsLoading(true);
        setError("");

        const data = await getEvents();
        setEvents(Array.isArray(data) ? data : []);
      } catch (err) {
        setError("Impossible de charger les épreuves.");
      } finally {
        setIsLoading(false);
      }
    };

    fetchEvents();
  }, []);

  const columns = useMemo(() => {
    if (events.length === 0) {
      return [];
    }

    const priorityKeys = [
      "id",
      "name",
      "title",
      "location",
      "startDate",
      "endDate",
      "date",
      "time",
      "status",
      "competition",
    ];

    const firstItemKeys = Object.keys(events[0]);

    const orderedKeys = [
      ...priorityKeys.filter((key) => firstItemKeys.includes(key)),
      ...firstItemKeys.filter((key) => !priorityKeys.includes(key)),
    ];

    return orderedKeys;
  }, [events]);

  return (
    <div className="space-y-6">
      <div>
        <p className="text-sm font-medium text-brand-600">Épreuves</p>
        <h2 className="text-2xl font-bold text-gray-800 dark:text-white">
          Liste des épreuves
        </h2>
        <p className="mt-2 text-sm text-gray-500 dark:text-gray-400">
          Cette page affiche les épreuves récupérées depuis le backend.
        </p>
      </div>

      <div className="rounded-2xl border border-gray-200 bg-white p-5 shadow-sm dark:border-gray-800 dark:bg-gray-900">
        {isLoading && (
          <div className="rounded-xl bg-gray-50 p-4 text-sm text-gray-600 dark:bg-gray-800 dark:text-gray-300">
            Chargement des épreuves...
          </div>
        )}

        {!isLoading && error && (
          <div className="rounded-xl bg-red-50 p-4 text-sm text-red-700 dark:bg-red-500/10 dark:text-red-300">
            {error}
          </div>
        )}

        {!isLoading && !error && events.length === 0 && (
          <div className="rounded-xl bg-gray-50 p-4 text-sm text-gray-600 dark:bg-gray-800 dark:text-gray-300">
            Aucune épreuve trouvée.
          </div>
        )}

        {!isLoading && !error && events.length > 0 && (
          <div className="overflow-x-auto">
            <table className="min-w-full border-collapse">
              <thead>
                <tr className="border-b border-gray-200 dark:border-gray-800">
                  {columns.map((column) => (
                    <th
                      key={column}
                      className="px-4 py-3 text-left text-sm font-semibold text-gray-700 dark:text-gray-300"
                    >
                      {formatColumnLabel(column)}
                    </th>
                  ))}
                </tr>
              </thead>

              <tbody>
                {events.map((event, index) => (
                  <tr
                    key={String(event.id ?? index)}
                    className="border-b border-gray-100 dark:border-gray-800"
                  >
                    {columns.map((column) => (
                      <td
                        key={column}
                        className="px-4 py-3 text-sm text-gray-600 dark:text-gray-300"
                      >
                        {formatValue(event[column])}
                      </td>
                    ))}
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        )}
      </div>
    </div>
  );
}