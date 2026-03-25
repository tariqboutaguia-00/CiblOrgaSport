# DEV LOG - CiblOrgaSport

## Étape 1

### Objectif
Initialiser le projet avec une structure propre et une base de documentation.

### Actions
- création des dossiers backend et frontend
- initialisation du dépôt Git
- ajout du fichier .gitignore
- création du README avec description du projet et architecture

### Résultat
Structure prête pour commencer le développement du backend

## Étape 2

### Objectif
Créer la base du backend Spring Boot avec les dépendances nécessaires au projet.

### Actions
- génération du projet Spring Boot
- ajout des dépendances principales
- intégration du projet dans le dossier backend
- vérification de la structure Maven

### Dépendances ajoutées
- Spring Web
- Spring Security
- Spring Data JPA
- Validation
- PostgreSQL Driver
- Flyway
- Actuator
- Lombok

### Résultat
Backend Spring Boot initialisé et prêt pour la configuration

## Étape 3

### Objectif
Nettoyer la configuration du backend et préparer la connexion à PostgreSQL.

### Actions
- suppression du fichier HELP.md
- configuration de Spring Boot dans application.yaml
- ajout de la configuration datasource, JPA, Flyway et Actuator
- création du dossier de migration Flyway
- ajout de la première migration SQL
- préparation des packages métier du backend

### Résultat
Configuration du backend structurée et prête pour la mise en place de la base de données

## Étape 4

### Objectif
Mettre en place PostgreSQL en local avec Docker pour permettre le démarrage complet du backend.

### Actions
- création du fichier docker-compose.yml
- ajout du service PostgreSQL
- démarrage du conteneur en local
- test du lancement de Spring Boot avec la base configurée
- vérification de l’endpoint de santé `http://localhost:8080/actuator/health`

### Résultat
Base PostgreSQL locale opérationnelle avec Docker et backend prêt à démarrer avec une vraie datasource

## Étape 5

### Objectif
Créer le premier module métier du backend avec la gestion des utilisateurs.

### Actions
- création de l’entité User
- ajout de l’enum Role
- création du repository UserRepository
- création du service UserService
- création du controller UserController
- ajout de la première migration SQL pour la table users
- ajout d’une configuration de sécurité temporaire ouverte

### Résultat
Premier module backend fonctionnel avec un endpoint GET /api/users

## Étape 6

### Objectif
Permettre la création d’utilisateurs via l’API.

### Actions
- création du DTO CreateUserRequest
- ajout de la logique createUser dans UserService
- ajout du endpoint POST /api/users
- ajout d’une gestion simple des erreurs et validations

### Résultat
Création et consultation d’utilisateurs possibles via l’API

## Étape 7

### Objectif
Sécuriser le stockage des mots de passe dans la base de données.

### Actions
- ajout du bean PasswordEncoder dans SecurityConfig
- utilisation de BCrypt dans UserService
- hashage du mot de passe avant sauvegarde

### Résultat
Les mots de passe des utilisateurs ne sont plus stockés en clair

## Étape 8

### Objectif
Mettre en place une première authentification par email et mot de passe.

### Actions
- création des DTO LoginRequest et LoginResponse
- création de AuthService
- création de AuthController
- vérification du mot de passe hashé avec PasswordEncoder

### Résultat
L’utilisateur peut se connecter via l’endpoint POST /api/auth/login

## Étape 9

### Objectif
Créer un compte administrateur par défaut pour faciliter les tests et l’initialisation du système.

### Actions
- création de DataInitializer
- ajout d’une logique de création automatique d’un admin au démarrage
- sécurisation du mot de passe admin avec BCrypt

### Résultat
Un compte administrateur par défaut est disponible pour tester l’application

## Étape 10

### Objectif
Mettre en place les premiers tests unitaires sur les services principaux du backend.

### Actions
- création des tests de UserService
- création des tests de AuthService
- vérification du comportement de création d’utilisateur
- vérification du comportement de login

### Résultat
Les premiers tests unitaires du backend sont en place

## Étape 11

### Objectif
Commencer la gestion métier des compétitions dans l’application.

### Actions
- création de l’entité Competition
- ajout de la migration SQL pour la table competitions
- création du repository CompetitionRepository
- création des DTO de création et de réponse
- création du service CompetitionService
- création du controller CompetitionController

### Résultat
Gestion de base des compétitions disponible via l’API

## Étape 12

### Objectif
Ajouter la gestion des épreuves liées aux compétitions.

### Actions
- création de l’entité Event
- ajout de la migration SQL pour la table events
- création du repository EventRepository
- création des DTO de création et de réponse
- création du service EventService
- création du controller EventController
- ajout de la relation entre Event et Competition

### Résultat
Gestion de base des épreuves disponible via l’API

## Étape 13

### Objectif
Ajouter la gestion des profils athlètes reliés aux utilisateurs.

### Actions
- création de l’entité Athlete
- ajout de la migration SQL pour la table athletes
- création du repository AthleteRepository
- création des DTO de création et de réponse
- création du service AthleteService
- création du controller AthleteController
- ajout de la relation entre Athlete et User

### Résultat
Gestion de base des profils athlètes disponible via l’API

## Étape 14

### Objectif
Permettre l’inscription d’un athlète à une épreuve.

### Actions
- création de l’entité Participant
- ajout de la migration SQL pour la table participants
- création du repository ParticipantRepository
- création des DTO de création et de réponse
- création du service ParticipantService
- création du controller ParticipantController
- ajout des relations avec Athlete et Event

### Résultat
Gestion de l’inscription des athlètes aux épreuves disponible via l’API

## Étape 15

### Objectif
Permettre l’enregistrement des résultats des participants aux épreuves.

### Actions
- création de l’entité Result
- ajout de la migration SQL pour la table results
- création du repository ResultRepository
- création des DTO de création et de réponse
- création du service ResultService
- création du controller ResultController
- ajout de la relation entre Result et Participant

### Résultat
Gestion des résultats disponible via l’API

## Étape 16

### Objectif
Permettre la gestion des incidents liés aux épreuves.

### Actions
- création de l’entité Incident
- ajout de la migration SQL pour la table incidents
- création du repository IncidentRepository
- création des DTO de création et de réponse
- création du service IncidentService
- création du controller IncidentController
- ajout de la relation entre Incident et Event

### Résultat
Gestion de base des incidents disponible via l’API