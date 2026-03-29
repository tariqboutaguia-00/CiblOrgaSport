import { useState } from "react";
import { getVolunteerPlanningToday } from "../services/volunteer-planning.service";
import type { VolunteerPlanningItem } from "../types/volunteer-planning";

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
    if (value.length === 0) {
      return "-";
    }

    return `${value.length} élément(s)`;
  }

  if (typeof value === "object") {
    const objectValue = value as Record<string, unknown>;

    if ("name" in objectValue && typeof objectValue.name === "string") {
      return objectValue.name;
    }

    if ("title" in objectValue && typeof objectValue.title === "string") {
      return objectValue.title;
    }

    if ("email" in objectValue && typeof objectValue.email === "string") {
      return objectValue.email;
    }

    if ("id" in objectValue && objectValue.id !== null && objectValue.id !== undefined) {
      return `#${String(objectValue.id)}`;
    }

    return "Objet lié";
  }

  return String(value);
}

function formatColumnLabel(key: string): string {
  const withSpaces = key.replace(/([A-Z])/g, " $1");
  return withSpaces.charAt(0).toUpperCase() + withSpaces.slice(1);
}

export default function VolunteerPlanning() {
  const [volunteerId, setVolunteerId] = useState("");
  const [planning, setPlanning] = useState<VolunteerPlanningItem[]>([]);
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState("");
  const [hasSearched, setHasSearched] = useState(false);

  const columns = planning.length > 0 ? Object.keys(planning[0]) : [];

  const handleSearch = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    setError("");
    setHasSearched(true);
    setPlanning([]);

    const parsedId = Number(volunteerId);

    if (!volunteerId.trim() || Number.isNaN(parsedId) || parsedId <= 0) {
      setError("Veuillez saisir un identifiant de volontaire valide.");
      return;
    }

    try {
      setIsLoading(true);

      const data = await getVolunteerPlanningToday(parsedId);
      setPlanning(Array.isArray(data) ? data : []);
    } catch (err) {
      setError("Impossible de charger le planning du volontaire.");
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="space-y-6">
      <div>
        <p className="text-sm font-medium text-brand-600">Planning volontaire</p>
        <h2 className="text-2xl font-bold text-gray-800 dark:text-white">
          Planning du jour
        </h2>
        <p className="mt-2 text-sm text-gray-500 dark:text-gray-400">
          Cette page permet de consulter les missions du jour d’un volontaire via son identifiant.
        </p>
      </div>

      <div className="rounded-2xl border border-gray-200 bg-white p-5 shadow-sm dark:border-gray-800 dark:bg-gray-900">
        <form onSubmit={handleSearch} className="grid gap-4 md:grid-cols-[1fr_auto] md:items-end">
          <div>
            <label
              htmlFor="volunteerId"
              className="mb-2 block text-sm font-medium text-gray-700 dark:text-gray-300"
            >
              Identifiant du volontaire
            </label>
            <input
              id="volunteerId"
              type="number"
              min="1"
              value={volunteerId}
              onChange={(event) => setVolunteerId(event.target.value)}
              placeholder="Exemple : 1"
              className="w-full rounded-xl border border-gray-300 px-4 py-3 text-sm outline-none focus:border-brand-500 dark:border-gray-700 dark:bg-gray-800 dark:text-white"
            />
          </div>

          <button
            type="submit"
            disabled={isLoading}
            className="rounded-xl bg-brand-500 px-4 py-3 text-sm font-semibold text-white hover:bg-brand-600 disabled:cursor-not-allowed disabled:opacity-70"
          >
            {isLoading ? "Chargement..." : "Afficher le planning"}
          </button>
        </form>
      </div>

      {error && (
        <div className="rounded-2xl border border-red-200 bg-red-50 p-4 text-sm text-red-700 shadow-sm dark:border-red-500/20 dark:bg-red-500/10 dark:text-red-300">
          {error}
        </div>
      )}

      {!isLoading && hasSearched && !error && planning.length === 0 && (
        <div className="rounded-2xl border border-gray-200 bg-white p-5 text-sm text-gray-600 shadow-sm dark:border-gray-800 dark:bg-gray-900 dark:text-gray-300">
          Aucune mission trouvée pour ce volontaire aujourd’hui.
        </div>
      )}

      {!isLoading && !error && planning.length > 0 && (
        <div className="rounded-2xl border border-gray-200 bg-white p-5 shadow-sm dark:border-gray-800 dark:bg-gray-900">
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
                {planning.map((item, index) => (
                  <tr
                    key={String(item.id ?? index)}
                    className="border-b border-gray-100 dark:border-gray-800"
                  >
                    {columns.map((column) => (
                      <td
                        key={column}
                        className="px-4 py-3 text-sm text-gray-600 dark:text-gray-300"
                      >
                        {formatValue(item[column])}
                      </td>
                    ))}
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </div>
      )}
    </div>
  );
}