const cards = [
  { title: "Compétitions", value: "0", description: "Gestion des compétitions" },
  { title: "Épreuves", value: "0", description: "Suivi des épreuves" },
  { title: "Participants", value: "0", description: "Gestion des inscrits" },
  { title: "Résultats", value: "0", description: "Résultats sportifs" },
  { title: "Missions", value: "0", description: "Organisation des volontaires" },
  { title: "Notifications", value: "0", description: "Suivi des alertes" },
];

export default function Home() {
  return (
    <div className="space-y-6">
      <div>
        <p className="text-sm font-medium text-brand-600">Dashboard</p>
        <h2 className="text-2xl font-bold text-gray-800 dark:text-white">
          Tableau de bord CiblOrgaSport
        </h2>
        <p className="mt-2 text-sm text-gray-500 dark:text-gray-400">
          Base nettoyée du back-office. La connexion au backend sera ajoutée à
          l’étape suivante.
        </p>
      </div>

      <div className="grid grid-cols-1 gap-4 md:grid-cols-2 xl:grid-cols-3">
        {cards.map((card) => (
          <div
            key={card.title}
            className="rounded-2xl border border-gray-200 bg-white p-5 shadow-sm dark:border-gray-800 dark:bg-gray-900"
          >
            <p className="text-sm text-gray-500 dark:text-gray-400">
              {card.title}
            </p>
            <p className="mt-2 text-3xl font-bold text-gray-800 dark:text-white">
              {card.value}
            </p>
            <p className="mt-2 text-sm text-gray-500 dark:text-gray-400">
              {card.description}
            </p>
          </div>
        ))}
      </div>

      <div className="rounded-2xl border border-dashed border-gray-300 bg-white p-6 dark:border-gray-700 dark:bg-gray-900">
        <h3 className="text-lg font-semibold text-gray-800 dark:text-white">
          Prochaine étape
        </h3>
        <p className="mt-2 text-sm text-gray-500 dark:text-gray-400">
          Nous allons brancher l’authentification JWT avec ton endpoint
          <code className="mx-1 rounded bg-gray-100 px-2 py-1 dark:bg-gray-800">
            /api/auth/login
          </code>
          puis récupérer l’utilisateur connecté via
          <code className="mx-1 rounded bg-gray-100 px-2 py-1 dark:bg-gray-800">
            /api/users/me
          </code>
          .
        </p>
      </div>
    </div>
  );
}