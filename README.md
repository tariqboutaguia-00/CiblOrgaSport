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

Les premiers tests unitaires ont été ajoutés pour le module utilisateur et l’authentification.

## Endpoints implémentés

Le backend expose actuellement les endpoints suivants :

- `GET /api/users` : récupérer la liste des utilisateurs  
- `POST /api/users` : créer un utilisateur

Les mots de passe des utilisateurs sont maintenant stockés de manière sécurisée dans la base de données.

- `POST /api/auth/login` : authentifier un utilisateur avec email et mot de passe
- `GET /api/competitions` : récupérer la liste des compétitions
- `POST /api/competitions` : créer une compétition
- `GET /api/events` : récupérer la liste des épreuves
- `POST /api/events` : créer une épreuve
- `GET /api/athletes` : récupérer la liste des athlètes
- `POST /api/athletes` : créer un profil athlète
- `GET /api/participants` : récupérer la liste des participants
- `POST /api/participants` : inscrire un athlète à une épreuve
- `GET /api/results` : récupérer la liste des résultats
- `POST /api/results` : enregistrer un résultat pour un participant

## Données initiales

Au démarrage de l’application, un compte administrateur est créé automatiquement si aucun administrateur n’existe.

```text
Email : admin@ciblorgasport.com
Mot de passe : admin123
```

## Statut

Développement du backend en cours.