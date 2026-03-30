import { useEffect, useMemo, useState } from "react";
import { getStatistics } from "../services/statistics.service";
import type { StatisticsData } from "../types/statistics";
import StatisticsBarChart from "../components/statistics/StatisticsBarChart";
import StatisticsHorizontalChart from "../components/statistics/StatisticsHorizontalChart";

type StatisticsEntry = {
    label: string;
    value: number;
};

function formatLabel(key: string) {
    const withSpaces = key.replace(/([A-Z])/g, " $1");
    return withSpaces.charAt(0).toUpperCase() + withSpaces.slice(1);
}

function toNumericEntries(statistics: StatisticsData | null): StatisticsEntry[] {
    if (!statistics) {
        return [];
    }

    return Object.entries(statistics)
        .filter(([, value]) => typeof value === "number")
        .map(([key, value]) => ({
            label: formatLabel(key),
            value: value as number,
        }));
}

export default function Statistics() {
    const [statistics, setStatistics] = useState<StatisticsData | null>(null);
    const [isLoading, setIsLoading] = useState(true);
    const [error, setError] = useState("");

    useEffect(() => {
        const fetchStatistics = async () => {
            try {
                setIsLoading(true);
                setError("");

                const data = await getStatistics();
                setStatistics(data);
            } catch (err) {
                setError("Impossible de charger les statistiques.");
            } finally {
                setIsLoading(false);
            }
        };

        fetchStatistics();
    }, []);

    const numericEntries = useMemo(
        () => toNumericEntries(statistics),
        [statistics]
    );

    const firstChartData = numericEntries.slice(0, 6);
    const secondChartData = numericEntries.slice(0, 6);

    return (
        <div className="space-y-6">
            <div>
                <p className="text-sm font-medium text-brand-600">Statistiques</p>
                <h2 className="text-2xl font-bold text-gray-800 dark:text-white">
                    Vue globale de l’application
                </h2>
                <p className="mt-2 text-sm text-gray-500 dark:text-gray-400">
                    Cette page affiche les statistiques globales du backend sous forme de
                    tableau et de graphiques simples.
                </p>
            </div>

            {isLoading && (
                <div className="rounded-2xl border border-gray-200 bg-white p-5 text-sm text-gray-600 shadow-sm dark:border-gray-800 dark:bg-gray-900 dark:text-gray-300">
                    Chargement des statistiques...
                </div>
            )}

            {!isLoading && error && (
                <div className="rounded-2xl border border-red-200 bg-red-50 p-5 text-sm text-red-700 shadow-sm dark:border-red-500/20 dark:bg-red-500/10 dark:text-red-300">
                    {error}
                </div>
            )}

            {!isLoading && !error && numericEntries.length === 0 && (
                <div className="rounded-2xl border border-gray-200 bg-white p-5 text-sm text-gray-600 shadow-sm dark:border-gray-800 dark:bg-gray-900 dark:text-gray-300">
                    Aucune statistique numérique disponible.
                </div>
            )}

            {!isLoading && !error && numericEntries.length > 0 && (
                <>
                    <div className="grid grid-cols-1 gap-6 xl:grid-cols-2">
                        <StatisticsBarChart
                            title="Répartition générale"
                            data={firstChartData}
                        />

                        <StatisticsHorizontalChart
                            title="Comparaison des modules"
                            data={secondChartData}
                        />
                    </div>
                    <div className="rounded-2xl border border-gray-200 bg-white p-5 shadow-sm dark:border-gray-800 dark:bg-gray-900">
                        <h3 className="text-lg font-semibold text-gray-800 dark:text-white">
                            Tableau des statistiques
                        </h3>

                        <div className="mt-5 overflow-x-auto">
                            <table className="min-w-full border-collapse">
                                <thead>
                                    <tr className="border-b border-gray-200 dark:border-gray-800">
                                        <th className="px-4 py-3 text-left text-sm font-semibold text-gray-700 dark:text-gray-300">
                                            Indicateur
                                        </th>
                                        <th className="px-4 py-3 text-left text-sm font-semibold text-gray-700 dark:text-gray-300">
                                            Valeur
                                        </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {numericEntries.map((entry) => (
                                        <tr
                                            key={entry.label}
                                            className="border-b border-gray-100 dark:border-gray-800"
                                        >
                                            <td className="px-4 py-3 text-sm font-medium text-gray-800 dark:text-white">
                                                {entry.label}
                                            </td>
                                            <td className="px-4 py-3 text-sm text-gray-600 dark:text-gray-300">
                                                {entry.value}
                                            </td>
                                        </tr>
                                    ))}
                                </tbody>
                            </table>
                        </div>
                    </div>



                    <div className="rounded-2xl border border-gray-200 bg-white p-5 shadow-sm dark:border-gray-800 dark:bg-gray-900">
                        <h3 className="text-lg font-semibold text-gray-800 dark:text-white">
                            Lecture rapide
                        </h3>
                        <p className="mt-3 text-sm text-gray-600 dark:text-gray-300">
                            Le tableau donne une lecture précise des valeurs, et les graphiques
                            permettent une comparaison visuelle rapide entre les modules.
                        </p>
                    </div>
                </>
            )}
        </div>
    );
}