import { useNavigate } from "react-router";
import { useSidebar } from "../context/SidebarContext";
import { useAuth } from "../context/AuthContext";

const AppHeader: React.FC = () => {
  const { isMobileOpen, toggleSidebar, toggleMobileSidebar } = useSidebar();
  const { user, logout } = useAuth();
  const navigate = useNavigate();

  const handleToggle = () => {
    if (window.innerWidth >= 1024) {
      toggleSidebar();
    } else {
      toggleMobileSidebar();
    }
  };

  const handleLogout = () => {
    logout();
    navigate("/login");
  };

  return (
    <header className="sticky top-0 z-40 w-full border-b border-gray-200 bg-white dark:border-gray-800 dark:bg-gray-900">
      <div className="flex items-center justify-between px-4 py-4 lg:px-6">
        <div className="flex items-center gap-3">
          <button
            onClick={handleToggle}
            aria-label="Ouvrir ou fermer la sidebar"
            className="flex h-10 w-10 items-center justify-center rounded-lg border border-gray-200 text-gray-600 hover:bg-gray-100 dark:border-gray-700 dark:text-gray-300 dark:hover:bg-gray-800"
          >
            {isMobileOpen ? "×" : "☰"}
          </button>

          <div>
            <h1 className="text-lg font-semibold text-gray-800 dark:text-white">
              CiblOrgaSport
            </h1>
            <p className="text-sm text-gray-500 dark:text-gray-400">
              Back-office de gestion
            </p>
          </div>
        </div>

        <div className="flex items-center gap-3">
          <div className="hidden text-right sm:block">
            <p className="text-sm font-semibold text-gray-800 dark:text-white">
              {user?.email || "Utilisateur"}
            </p>
            <p className="text-xs text-gray-500 dark:text-gray-400">
              {user?.role || "ROLE_INCONNU"}
            </p>
          </div>

          <button
            onClick={handleLogout}
            className="rounded-lg border border-gray-200 px-3 py-2 text-sm text-gray-700 hover:bg-gray-100 dark:border-gray-700 dark:text-gray-300 dark:hover:bg-gray-800"
          >
            Déconnexion
          </button>
        </div>
      </div>
    </header>
  );
};

export default AppHeader;