import { useAuth } from "../context/AuthContext";
import { getRoleLabel } from "../utils/role";

export default function Profile() {
  const { user, isAuthenticated } = useAuth();

  return (
    <div className="space-y-6">
      <div>
        <p className="text-sm font-medium text-brand-600">Profil</p>
        <h2 className="text-2xl font-bold text-gray-800 dark:text-white">
          Mon profil
        </h2>
        <p className="mt-2 text-sm text-gray-500 dark:text-gray-400">
          Cette page affiche les informations de l’utilisateur actuellement connecté.
        </p>
      </div>

      <div className="rounded-2xl border border-gray-200 bg-white p-6 shadow-sm dark:border-gray-800 dark:bg-gray-900">
        <h3 className="text-lg font-semibold text-gray-800 dark:text-white">
          Informations générales
        </h3>

        <div className="mt-5 grid gap-4 md:grid-cols-2">
          <div className="rounded-xl bg-gray-50 p-4 dark:bg-gray-800">
            <p className="text-xs uppercase tracking-wide text-gray-500 dark:text-gray-400">
              Identifiant
            </p>
            <p className="mt-1 text-sm font-medium text-gray-800 dark:text-white">
              {user?.id ?? "-"}
            </p>
          </div>

          <div className="rounded-xl bg-gray-50 p-4 dark:bg-gray-800">
            <p className="text-xs uppercase tracking-wide text-gray-500 dark:text-gray-400">
              Email
            </p>
            <p className="mt-1 text-sm font-medium text-gray-800 dark:text-white">
              {user?.email || "-"}
            </p>
          </div>

          <div className="rounded-xl bg-gray-50 p-4 dark:bg-gray-800">
            <p className="text-xs uppercase tracking-wide text-gray-500 dark:text-gray-400">
              Rôle
            </p>
            <p className="mt-1 text-sm font-medium text-gray-800 dark:text-white">
              {getRoleLabel(user?.role)}
            </p>
          </div>

          <div className="rounded-xl bg-gray-50 p-4 dark:bg-gray-800">
            <p className="text-xs uppercase tracking-wide text-gray-500 dark:text-gray-400">
              Session
            </p>
            <p className="mt-1 text-sm font-medium text-gray-800 dark:text-white">
              {isAuthenticated ? "Connecté" : "Non connecté"}
            </p>
          </div>
        </div>
      </div>

      <div className="rounded-2xl border border-gray-200 bg-white p-6 shadow-sm dark:border-gray-800 dark:bg-gray-900">
        <h3 className="text-lg font-semibold text-gray-800 dark:text-white">
          Résumé
        </h3>
        <p className="mt-3 text-sm text-gray-600 dark:text-gray-300">
          Cette page permet de vérifier rapidement quel utilisateur est connecté
          dans le back-office. Elle est utile pour tester l’authentification JWT,
          l’affichage du rôle et la cohérence de la session côté frontend.
        </p>
      </div>
    </div>
  );
}