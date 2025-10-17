# Configuration Hibernate + PostgreSQL

## 🎯 Vue d'ensemble

Votre projet BloodBank a été configuré avec Hibernate et PostgreSQL pour une gestion professionnelle de la base de données.

## 📁 Structure ajoutée

```
src/main/java/com/bloodbank/
├── model/
│   ├── Donneur.java (annoté @Entity)
│   ├── Receveur.java (annoté @Entity)
│   ├── StatutDonneur.java (enum)
│   ├── StatutReceveur.java (enum)
│   └── PrioriteReceveur.java (enum)
├── dao/
│   ├── HibernateDonneurDAO.java (nouveau)
│   └── HibernateReceveurDAO.java (nouveau)
└── util/
    └── HibernateUtil.java (nouveau)

src/main/resources/
├── hibernate.cfg.xml (configuration Hibernate)
└── database_setup.sql (script de création DB)
```

## 🔧 Configuration PostgreSQL

### 1. Installer PostgreSQL
- Télécharger depuis : https://www.postgresql.org/download/
- Installer avec les paramètres par défaut
- Mémoriser le mot de passe de l'utilisateur `postgres`

### 2. Créer la base de données
```sql
-- Se connecter à PostgreSQL
psql -U postgres

-- Créer la base de données
CREATE DATABASE bloodbank_db;

-- Se connecter à la nouvelle base
\c bloodbank_db;
```

### 3. Vérifier la configuration dans hibernate.cfg.xml
```xml
<!-- Adapter selon votre configuration -->
<property name="hibernate.connection.url">jdbc:postgresql://localhost:5432/bloodbank_db</property>
<property name="hibernate.connection.username">postgres</property>
<property name="hibernate.connection.password">votre_mot_de_passe</property>
```

## 🚀 Utilisation

### Activer Hibernate dans les services

Pour utiliser Hibernate au lieu de la version en mémoire, modifiez les services :

```java
// Dans DonneurService.java, remplacer :
private DonneurDAO donneurDAO = new DonneurDAO();

// Par :
private HibernateDonneurDAO donneurDAO = new HibernateDonneurDAO();
```

### Première exécution
1. Démarrer PostgreSQL
2. Lancer l'application
3. Hibernate créera automatiquement les tables grâce à `hbm2ddl.auto=update`

## 📊 Tables créées automatiquement

```sql
-- Table donneurs
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

-- Table receveurs
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
```

## 🔍 Vérification

### Logs de démarrage
Vous devriez voir ces messages :
```
✅ SessionFactory Hibernate créée avec succès !
✅ Donneur ajouté avec succès : [nom]
✅ Receveur ajouté avec succès : [nom]
```

### Vérifier les données
```sql
-- Se connecter à la base
psql -U postgres -d bloodbank_db

-- Vérifier les tables
\dt

-- Voir les données
SELECT * FROM donneurs;
SELECT * FROM receveurs;
```

## ⚠️ Points importants

1. **Hibernate EntityManager** : Ajouté dans pom.xml pour les annotations @Entity
2. **Enums** : Les statuts et priorités sont maintenant des enums type-safe
3. **Transactions** : Gestion automatique des transactions avec rollback en cas d'erreur
4. **Connexions** : Pool de connexions configuré (10 connexions max)
5. **Logs SQL** : Activés pour le développement (`show_sql=true`)

## 🛠️ Dépannage

### Erreur de connexion PostgreSQL
- Vérifier que PostgreSQL est démarré
- Vérifier les credentials dans hibernate.cfg.xml
- Vérifier que la base `bloodbank_db` existe

### Erreur de dépendances
```bash
# Nettoyer et recompiler
mvn clean compile
```

### Tables non créées
- Vérifier les logs d'erreur
- Vérifier que les entités sont bien mappées dans hibernate.cfg.xml
- Vérifier les permissions de l'utilisateur PostgreSQL

## 📝 Prochaines étapes

1. **Migrer progressivement** : Remplacer les anciens DAO par les versions Hibernate
2. **Ajouter des relations** : Relations OneToMany, ManyToMany si nécessaire
3. **Optimiser** : Index sur les colonnes fréquemment utilisées
4. **Sécuriser** : Configuration de production avec connexions chiffrées

Votre projet est maintenant prêt pour une utilisation professionnelle avec Hibernate ! 🎉
