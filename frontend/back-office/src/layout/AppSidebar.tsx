import { Link, useLocation } from "react-router";
import {
  GridIcon,
  ListIcon,
  TableIcon,
  UserCircleIcon,
  TaskIcon,
  MailIcon,
} from "../icons";
import { useSidebar } from "../context/SidebarContext";
import { useAuth } from "../context/AuthContext";

type MenuItem = {
  label: string;
  path: string;
  icon: React.ReactNode;
  roles: string[];
};

const menuItems: MenuItem[] = [
  {
    label: "Dashboard",
    path: "/",
    icon: <GridIcon />,
    roles: [
      "ADMIN",
      "ATHLETE",
      "VOLUNTEER",
      "SPECTATOR",
      "DEPLOYMENT_MANAGER",
      "COMMISSIONER",
    ],
  },
  {
    label: "Statistiques",
    path: "/statistics",
    icon: <GridIcon />,
    roles: ["ADMIN", "DEPLOYMENT_MANAGER"],
  },
  {
    label: "Compétitions",
    path: "/competitions",
    icon: <ListIcon />,
    roles: ["ADMIN", "DEPLOYMENT_MANAGER", "COMMISSIONER", "SPECTATOR"],
  },
  {
    label: "Épreuves",
    path: "/events",
    icon: <TableIcon />,
    roles: [
      "ADMIN",
      "ATHLETE",
      "VOLUNTEER",
      "SPECTATOR",
      "DEPLOYMENT_MANAGER",
      "COMMISSIONER",
    ],
  },
  {
    label: "Participants",
    path: "/participants",
    icon: <UserCircleIcon />,
    roles: ["ADMIN", "ATHLETE", "COMMISSIONER"],
  },
  {
    label: "Résultats",
    path: "/results",
    icon: <TableIcon />,
    roles: ["ADMIN", "ATHLETE", "SPECTATOR", "COMMISSIONER"],
  },
  {
    label: "Missions",
    path: "/missions",
    icon: <TaskIcon />,
    roles: ["ADMIN", "VOLUNTEER", "DEPLOYMENT_MANAGER"],
  },
  {
    label: "Planning volontaire",
    path: "/volunteer-planning",
    icon: <TaskIcon />,
    roles: ["ADMIN", "VOLUNTEER"],
  },
  {
    label: "Notifications",
    path: "/notifications",
    icon: <MailIcon />,
    roles: [
      "ADMIN",
      "ATHLETE",
      "VOLUNTEER",
      "SPECTATOR",
      "DEPLOYMENT_MANAGER",
      "COMMISSIONER",
    ],
  },
  {
    label: "Profil",
    path: "/profile",
    icon: <UserCircleIcon />,
    roles: [
      "ADMIN",
      "ATHLETE",
      "VOLUNTEER",
      "SPECTATOR",
      "DEPLOYMENT_MANAGER",
      "COMMISSIONER",
    ],
  },
];

const AppSidebar: React.FC = () => {
  const location = useLocation();
  const { isExpanded, isMobileOpen, isHovered, setIsHovered } = useSidebar();
  const { user } = useAuth();

  const expanded = isExpanded || isHovered;

  const filteredMenuItems = menuItems.filter((item) =>
    item.roles.includes(user?.role || "")
  );

  return (
    <aside
      onMouseEnter={() => setIsHovered(true)}
      onMouseLeave={() => setIsHovered(false)}
      className={`fixed left-0 top-0 z-50 flex h-screen flex-col border-r border-gray-200 bg-white transition-all duration-300 dark:border-gray-800 dark:bg-gray-900
        ${expanded ? "w-[290px]" : "w-[90px]"}
        ${isMobileOpen ? "translate-x-0" : "-translate-x-full lg:translate-x-0"}`}
    >
      <div className="flex h-[73px] items-center border-b border-gray-200 px-4 dark:border-gray-800">
        <Link to="/" className="flex items-center gap-3">
          <div className="flex h-10 w-10 items-center justify-center rounded-xl bg-brand-500 font-bold text-white">
            C
          </div>
          {expanded && (
            <div>
              <p className="text-sm font-semibold text-gray-800 dark:text-white">
                CiblOrgaSport
              </p>
              <p className="text-xs text-gray-500 dark:text-gray-400">
                Administration
              </p>
            </div>
          )}
        </Link>
      </div>

      <div className="flex-1 overflow-y-auto px-3 py-4">
        <p
          className={`mb-3 text-xs font-semibold uppercase tracking-wide text-gray-400 ${expanded ? "block" : "hidden"
            }`}
        >
          Navigation
        </p>

        <nav className="flex flex-col gap-2">
          {filteredMenuItems.map((item) => {
            const active = location.pathname === item.path;

            return (
              <Link
                key={item.path}
                to={item.path}
                className={`flex items-center gap-3 rounded-xl px-3 py-3 text-sm font-medium transition
                  ${active
                    ? "bg-brand-50 text-brand-700 dark:bg-brand-500/10 dark:text-brand-300"
                    : "text-gray-700 hover:bg-gray-100 dark:text-gray-300 dark:hover:bg-gray-800"
                  }
                  ${expanded ? "justify-start" : "justify-center"}
                `}
              >
                <span className="shrink-0">{item.icon}</span>
                {expanded && <span>{item.label}</span>}
              </Link>
            );
          })}
        </nav>
      </div>

      <div className="border-t border-gray-200 p-4 dark:border-gray-800">
        {expanded ? (
          <div className="rounded-xl bg-gray-50 p-4 dark:bg-gray-800">
            <p className="text-sm font-semibold text-gray-800 dark:text-white">
              Menu adapté au rôle
            </p>
            <p className="mt-1 text-xs text-gray-500 dark:text-gray-400">
              La navigation affichée dépend de l’utilisateur connecté.
            </p>
          </div>
        ) : (
          <div className="flex justify-center">
            <div className="h-3 w-3 rounded-full bg-green-500"></div>
          </div>
        )}
      </div>
    </aside>
  );
};

export default AppSidebar;