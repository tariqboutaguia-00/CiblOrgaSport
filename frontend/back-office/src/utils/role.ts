export const ROLE_LABELS: Record<string, string> = {
  ADMIN: "Administrateur",
  ATHLETE: "Athlète",
  VOLUNTEER: "Volontaire",
  SPECTATOR: "Spectateur",
  DEPLOYMENT_MANAGER: "Responsable déploiement",
  COMMISSIONER: "Commissaire",
};

export const getRoleLabel = (role?: string) => {
  if (!role) {
    return "Rôle inconnu";
  }

  return ROLE_LABELS[role] || role;
};

export const isAdmin = (role?: string) => role === "ADMIN";
export const isAthlete = (role?: string) => role === "ATHLETE";
export const isVolunteer = (role?: string) => role === "VOLUNTEER";
export const isSpectator = (role?: string) => role === "SPECTATOR";
export const isDeploymentManager = (role?: string) =>
  role === "DEPLOYMENT_MANAGER";
export const isCommissioner = (role?: string) => role === "COMMISSIONER";