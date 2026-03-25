# CiblOrgaSport

Application de gestion d’événements sportifs de natation développée dans le cadre du projet GLOP.

---

## Objectif

L’application permet de gérer l’organisation de compétitions sportives de natation :

- les compétitions
- les épreuves
- les participants
- les résultats
- les volontaires
- les incidents
- les notifications

---

## Utilisateurs

- Athlete
- Commissioner
- Deployment Manager
- Volunteer
- Spectator

---

## Structure du projet

```text
ciblorgasport/
├── backend/
├── frontend/
├── README.md
├── DEV_LOG.md
└── .gitignore
```

---

## Architecture prévue

### Backend

- Java 21
- Spring Boot
- Spring Security
- Spring Data JPA
- PostgreSQL
- Flyway
- Docker

### Frontend

- React
- Axios

---

## Déploiement

- Frontend : Vercel  
- Backend : Render  
- Base de données : Neon (PostgreSQL)

---

## Fonctionnalités prévues

- gestion des compétitions  
- gestion des épreuves  
- gestion des athlètes  
- gestion des participations  
- gestion des résultats  
- gestion des volontaires et missions  
- gestion des incidents  
- gestion des notifications  
- consultation de statistiques simples  

---

## API prévue

### Préfixe principal

```text
/api
```

### Exemples d’endpoints

- `/api/auth`  
- `/api/competitions`  
- `/api/events`  
- `/api/participants`  
- `/api/results`  

---

## Tests prévus

Le projet intégrera des tests pour vérifier les fonctionnalités principales, notamment :

- authentification
- gestion des compétitions
- gestion des épreuves
- gestion des participations
- gestion des résultats

## Statut

Initialisation du projet en cours.