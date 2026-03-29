import { useAuth } from "../../context/AuthContext";
import {
  getRoleLabel,
  isAdmin,
  isAthlete,
  isCommissioner,
  isDeploymentManager,
  isSpectator,
  isVolunteer,
} from "../../utils/role";

type RoleCard = {
  title: string;
  description: string;
};

const adminCards: RoleCard[] = [
  {
    title: "Gestion globale",
    description: "Superviser les compétitions, les épreuves, les participants et les résultats.",
  },
  {
    title: "Administration",
    description: "Accéder aux principales pages de gestion du système.",
  },
  {
    title: "Pilotage",
    description: "Suivre les données essentielles avant la soutenance.",
  },
];

const athleteCards: RoleCard[] = [
  {
    title: "Mes participations",
    description: "Consulter les épreuves auxquelles l’athlète participe.",
  },
  {
    title: "Mes performances",
    description: "Suivre les résultats et les performances personnelles.",
  },
  {
    title: "Charte européenne",
    description: "Gérer la validation de la charte côté athlète.",
  },
];

const volunteerCards: RoleCard[] = [
  {
    title: "Mes missions",
    description: "Consulter les missions attribuées au volontaire.",
  },
  {
    title: "Planning du jour",
    description: "Voir les tâches prévues pour aujourd’hui.",
  },
  {
    title: "Organisation terrain",
    description: "Accéder rapidement aux informations utiles à l’exécution des missions.",
  },
];

const spectatorCards: RoleCard[] = [
  {
    title: "Horaires publics",
    description: "Consulter les horaires et lieux des épreuves.",
  },
  {
    title: "Résultats",
    description: "Suivre les résultats sportifs et les informations utiles.",
  },
  {
    title: "Notifications",
    description: "Recevoir les informations importantes sur les compétitions.",
  },
];

const deploymentManagerCards: RoleCard[] = [
  {
    title: "Points de rendez-vous",
    description: "Gérer les points de rendez-vous des épreuves.",
  },
  {
    title: "Accès utilisateurs",
    description: "Activer ou désactiver l’accès à certains comptes.",
  },
  {
    title: "Suivi opérationnel",
    description: "Piloter les besoins logistiques et organisationnels.",
  },
];

const commissionerCards: RoleCard[] = [
  {
    title: "Conformité",
    description: "Vérifier la conformité des participants.",
  },
  {
    title: "Incidents",
    description: "Suivre et gérer les incidents liés aux épreuves.",
  },
  {
    title: "Reprogrammation",
    description: "Annuler ou reprogrammer les épreuves si nécessaire.",
  },
];

function getRoleCards(role?: string): RoleCard[] {
  if (isAdmin(role)) return adminCards;
  if (isAthlete(role)) return athleteCards;
  if (isVolunteer(role)) return volunteerCards;
  if (isSpectator(role)) return spectatorCards;
  if (isDeploymentManager(role)) return deploymentManagerCards;
  if (isCommissioner(role)) return commissionerCards;

  return [
    {
      title: "Dashboard",
      description: "Aucune vue spécifique n’est encore définie pour ce rôle.",
    },
  ];
}

function getRoleIntro(role?: string) {
  if (isAdmin(role)) {
    return "Vous avez accès à une vue globale de l’application et des fonctions principales de gestion.";
  }

  if (isAthlete(role)) {
    return "Cette vue met en avant les informations utiles à l’athlète, comme les participations et les performances.";
  }

  if (isVolunteer(role)) {
    return "Cette vue met en avant le planning et les missions du volontaire.";
  }

  if (isSpectator(role)) {
    return "Cette vue met en avant les informations publiques utiles au spectateur.";
  }

  if (isDeploymentManager(role)) {
    return "Cette vue met en avant le pilotage opérationnel et la gestion des accès.";
  }

  if (isCommissioner(role)) {
    return "Cette vue met en avant la conformité, les incidents et la gestion des épreuves.";
  }

  return "Cette vue sera enrichie selon les besoins métier.";
}

export default function Home() {
  const { user } = useAuth();

  const roleCards = getRoleCards(user?.role);
  const roleLabel = getRoleLabel(user?.role);

  return (
    <div className="space-y-6">
      <div>
        <p className="text-sm font-medium text-brand-600">Dashboard</p>
        <h2 className="text-2xl font-bold text-gray-800 dark:text-white">
          Tableau de bord CiblOrgaSport
        </h2>
        <p className="mt-2 text-sm text-gray-500 dark:text-gray-400">
          Bienvenue {user?.email}. Vous êtes connecté en tant que{" "}
          <span className="font-semibold">{roleLabel}</span>.
        </p>
      </div>

      <div className="rounded-2xl border border-gray-200 bg-white p-5 shadow-sm dark:border-gray-800 dark:bg-gray-900">
        <h3 className="text-lg font-semibold text-gray-800 dark:text-white">
          Vue adaptée au rôle
        </h3>
        <p className="mt-2 text-sm text-gray-500 dark:text-gray-400">
          {getRoleIntro(user?.role)}
        </p>

        <div className="mt-4 grid gap-3 md:grid-cols-2">
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
              {roleLabel}
            </p>
          </div>
        </div>
      </div>

      <div className="grid grid-cols-1 gap-4 md:grid-cols-2 xl:grid-cols-3">
        {roleCards.map((card) => (
          <div
            key={card.title}
            className="rounded-2xl border border-gray-200 bg-white p-5 shadow-sm dark:border-gray-800 dark:bg-gray-900"
          >
            <p className="text-lg font-semibold text-gray-800 dark:text-white">
              {card.title}
            </p>
            <p className="mt-2 text-sm text-gray-500 dark:text-gray-400">
              {card.description}
            </p>
          </div>
        ))}
      </div>
    </div>
  );
}