-- Script SQL pour créer la base de données PostgreSQL
-- Exécuter ce script dans PostgreSQL pour initialiser la base de données

-- Créer la base de données
CREATE DATABASE bloodbank_db;

-- Se connecter à la base de données bloodbank_db
\c bloodbank_db;

-- Créer l'utilisateur postgres avec mot de passe (optionnel)
-- CREATE USER postgres WITH PASSWORD 'postgres';
-- GRANT ALL PRIVILEGES ON DATABASE bloodbank_db TO postgres;

-- Les tables seront créées automatiquement par Hibernate grâce à hbm2ddl.auto=update
-- Mais voici les tables qui seront créées :

/*
CREATE TABLE donneurs (
    id BIGSERIAL PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    prenom VARCHAR(50) NOT NULL,
    cin VARCHAR(20) NOT NULL UNIQUE,
    telephone VARCHAR(20),
    groupe_sanguin VARCHAR(5) NOT NULL,
    poids DOUBLE PRECISION NOT NULL,
    age INTEGER NOT NULL,
    statut VARCHAR(20) NOT NULL
);

CREATE TABLE receveurs (
    id BIGSERIAL PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    prenom VARCHAR(50) NOT NULL,
    cin VARCHAR(20) NOT NULL UNIQUE,
    telephone VARCHAR(20),
    groupe_sanguin VARCHAR(5) NOT NULL,
    priorite VARCHAR(20) NOT NULL,
    statut VARCHAR(20) NOT NULL
);

-- Insérer quelques données de test
INSERT INTO donneurs (nom, prenom, cin, telephone, groupe_sanguin, poids, age, statut) VALUES
('Dupont', 'Jean', '1234567890', '0123456789', 'O+', 70.5, 30, 'DISPO'),
('Martin', 'Marie', '0987654321', '0987654321', 'A+', 65.0, 28, 'DISPO'),
('Bernard', 'Pierre', '1122334455', '1122334455', 'B-', 80.0, 35, 'NON_DISPO');

INSERT INTO receveurs (nom, prenom, cin, telephone, groupe_sanguin, priorite, statut) VALUES
('Durand', 'Sophie', '2233445566', '2233445566', 'O+', 'URGENT', 'EN_ATTENTE'),
('Leroy', 'Paul', '3344556677', '3344556677', 'A-', 'CRITIQUE', 'EN_ATTENTE'),
('Moreau', 'Julie', '4455667788', '4455667788', 'AB+', 'NORMAL', 'EN_ATTENTE');
*/
