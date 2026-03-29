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
- `GET /api/incidents` : récupérer la liste des incidents
- `POST /api/incidents` : déclarer un incident
- `GET /api/notifications` : récupérer la liste des notifications
- `POST /api/notifications` : créer une notification
- `GET /api/volunteers` : récupérer la liste des volontaires
- `POST /api/volunteers` : créer un profil volontaire
- `GET /api/missions` : récupérer la liste des missions
- `POST /api/missions` : créer une mission
- `POST /api/missions/{missionId}/assign/{volunteerId}` : affecter un volontaire à une mission
- `GET /api/statistics` : récupérer un résumé statistique de l’application
- `PATCH /api/athletes/{athleteId}/charter` : accepter ou refuser la charte européenne
- `PATCH /api/participants/{participantId}/withdraw` : déclarer forfait
- `GET /api/results/athlete/{athleteId}` : consulter les performances d’un athlète
- `GET /api/results/performances` : consulter toutes les performances
- `PATCH /api/participants/{participantId}/compliance` : vérifier la conformité d’un participant
- `PATCH /api/events/{eventId}/cancel` : annuler une épreuve
- `PATCH /api/events/{eventId}/reschedule` : reprogrammer une épreuve
- `PATCH /api/events/{eventId}/meeting-points` : gérer les points de rendez-vous d’une épreuve
- `PATCH /api/users/{userId}/access` : activer ou désactiver l’accès à un compte
- `POST /api/notifications/subscribe` : s’abonner à un type de notification
- `GET /api/notifications/user/{userId}` : récupérer les notifications d’un utilisateur
- `PATCH /api/notifications/{id}/read` : marquer une notification comme lue
- `GET /api/users/me` : récupérer l’utilisateur connecté
- `GET /api/missions/volunteer/{volunteerId}/today` : consulter le planning du jour d’un volontaire
- `GET /api/events/public-schedule` : consulter les horaires et lieux destinés au public
- `GET /api/notifications/me` : récupérer les notifications de l’utilisateur connecté

## Format des réponses API

Les réponses de l’API utilisent un format JSON homogène avec :
- `success`
- `message`
- `data`
- `timestamp`

Les erreurs de validation et les erreurs métier sont également gérées de manière centralisée.

## Données initiales

L’application initialise automatiquement des utilisateurs de test au démarrage :

- admin@ciblorgasport.com (ADMIN)
- tariq@ciblorgasport.com (ATHLETE)
- sara@ciblorgasport.com (VOLUNTEER)

```text
Email : admin@ciblorgasport.com
Mot de passe : admin123
```
## Authentification

L’API utilise désormais un système JWT.

### Login
- `POST /api/auth/login`

### Utilisation du token
Les endpoints protégés nécessitent un header :

```text
Authorization: Bearer <token>
```
## Autorisation par rôles

L’application distingue désormais les droits selon le rôle de l’utilisateur.

Exemples :
- les utilisateurs authentifiés peuvent consulter certaines ressources
- les actions de création sensibles sont réservées à l’administrateur
- les statistiques globales sont réservées à l’administrateur

### Frontend

Le frontend est séparé en deux applications React :

- `frontend/front-office` : site vitrine public
- `frontend/back-office` : interface de gestion

#### État actuel
- back-office : template admin nettoyé et préparé pour l’intégration métier
- front-office : template TeleportHQ nettoyé et simplifié pour obtenir une base vitrine claire, professionnelle et maintenable
- authentification JWT intégrée côté back-office (login + stockage token)
- configuration CORS ajoutée pour permettre les appels entre le frontend local et le backend Spring Boot
- récupération de l’utilisateur connecté via `/api/users/me` et protection des routes privées du back-office
- dashboard et navigation adaptés selon le rôle de l’utilisateur connecté
- affichage réel des compétitions dans le back-office via appel API Axios
- affichage réel des épreuves dans le back-office
- affichage réel des participants dans le back-office
- affichage réel des résultats dans le back-office
- affichage réel des missions dans le back-office
- affichage des notifications de l’utilisateur connecté avec marquage comme lu

## Statut

Développement du frontend en cours.