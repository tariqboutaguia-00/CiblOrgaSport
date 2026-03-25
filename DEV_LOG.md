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