import { useEffect, useState } from "react";
import { getCompetitions } from "../services/competition.service";
import type { Competition } from "../types/competition";

function formatDate(date: string) {
  if (!date) {
    return "-";
  }

  return new Date(date).toLocaleDateString("fr-FR");
}

export default function Competitions() {
  const [competitions, setCompetitions] = useState<Competition[]>([]);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    const fetchCompetitions = async () => {
      try {
        setIsLoading(true);
        setError("");

        const data = await getCompetitions();
        setCompetitions(data);
      } catch (err) {
        setError("Impossible de charger les compétitions.");
      } finally {
        setIsLoading(false);
      }
    };

    fetchCompetitions();
  }, []);

  return (
    <div className="space-y-6">
      <div>
        <p className="text-sm font-medium text-brand-600">Compétitions</p>
        <h2 className="text-2xl font-bold text-gray-800 dark:text-white">
          Liste des compétitions
        </h2>
        <p className="mt-2 text-sm text-gray-500 dark:text-gray-400">
          Cette page affiche les compétitions récupérées depuis le backend.
        </p>
      </div>

      <div className="rounded-2xl border border-gray-200 bg-white p-5 shadow-sm dark:border-gray-800 dark:bg-gray-900">
        {isLoading && (
          <div className="rounded-xl bg-gray-50 p-4 text-sm text-gray-600 dark:bg-gray-800 dark:text-gray-300">
            Chargement des compétitions...
          </div>
        )}

        {!isLoading && error && (
          <div className="rounded-xl bg-red-50 p-4 text-sm text-red-700 dark:bg-red-500/10 dark:text-red-300">
            {error}
          </div>
        )}

        {!isLoading && !error && competitions.length === 0 && (
          <div className="rounded-xl bg-gray-50 p-4 text-sm text-gray-600 dark:bg-gray-800 dark:text-gray-300">
            Aucune compétition trouvée.
          </div>
        )}

        {!isLoading && !error && competitions.length > 0 && (
          <div className="overflow-x-auto">
            <table className="min-w-full border-collapse">
              <thead>
                <tr className="border-b border-gray-200 dark:border-gray-800">
                  <th className="px-4 py-3 text-left text-sm font-semibold text-gray-700 dark:text-gray-300">
                    ID
                  </th>
                  <th className="px-4 py-3 text-left text-sm font-semibold text-gray-700 dark:text-gray-300">
                    Nom
                  </th>
                  <th className="px-4 py-3 text-left text-sm font-semibold text-gray-700 dark:text-gray-300">
                    Lieu
                  </th>
                  <th className="px-4 py-3 text-left text-sm font-semibold text-gray-700 dark:text-gray-300">
                    Date début
                  </th>
                  <th className="px-4 py-3 text-left text-sm font-semibold text-gray-700 dark:text-gray-300">
                    Date fin
                  </th>
                </tr>
              </thead>
              <tbody>
                {competitions.map((competition) => (
                  <tr
                    key={competition.id}
                    className="border-b border-gray-100 dark:border-gray-800"
                  >
                    <td className="px-4 py-3 text-sm text-gray-600 dark:text-gray-300">
                      {competition.id}
                    </td>
                    <td className="px-4 py-3 text-sm font-medium text-gray-800 dark:text-white">
                      {competition.name}
                    </td>
                    <td className="px-4 py-3 text-sm text-gray-600 dark:text-gray-300">
                      {competition.location}
                    </td>
                    <td className="px-4 py-3 text-sm text-gray-600 dark:text-gray-300">
                      {formatDate(competition.startDate)}
                    </td>
                    <td className="px-4 py-3 text-sm text-gray-600 dark:text-gray-300">
                      {formatDate(competition.endDate)}
                    </td>
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