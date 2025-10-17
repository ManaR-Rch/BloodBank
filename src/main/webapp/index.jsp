
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>BloodBank - Bienvenue</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; background-color: #f8f9fa; }
        .container { max-width: 800px; margin: 0 auto; background: white; padding: 30px; border-radius: 10px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }
        .nav-link { 
            display: inline-block; 
            margin: 10px 15px; 
            padding: 12px 24px; 
            background-color: #dc3545; 
            color: white; 
            text-decoration: none; 
            border-radius: 5px; 
            font-weight: bold;
            transition: background-color 0.3s;
        }
        .nav-link:hover { background-color: #c82333; }
        .feature { margin: 20px 0; padding: 15px; background-color: #e8f4fd; border-left: 4px solid #007bff; }
        h1 { color: #dc3545; text-align: center; }
        .architecture { background-color: #f8f9fa; padding: 20px; border-radius: 5px; margin: 20px 0; }
    </style>
</head>
<body>
    <div class="container">
        <h1>🚑 Bienvenue dans BloodBank</h1>
        <p style="text-align: center; font-size: 18px; color: #28a745;">
            ✅ Configuration Maven + Tomcat réussie<br>
            ✅ Architecture MVC implémentée
        </p>

        <div class="feature">
            <h3>🏥 Gestion des Donneurs</h3>
            <p>Gérez les donneurs de sang avec validation de l'éligibilité selon les critères médicaux.</p>
            <a href="donneurs" class="nav-link">Voir les donneurs</a>
            <a href="ajouterDonneur.jsp" class="nav-link">Ajouter un donneur</a>
        </div>

        <div class="feature">
            <h3>🩸 Gestion des Receveurs</h3>
            <p>Gérez les receveurs avec système de priorité automatique.</p>
            <a href="receveurs" class="nav-link">Voir les receveurs</a>
        </div>

        <div class="feature">
            <h3>🔗 Compatibilité des Groupes Sanguins</h3>
            <p>Algorithme de compatibilité ABO et Rhésus pour optimiser les matchings.</p>
            <a href="compatibility" class="nav-link">Voir les compatibilités</a>
        </div>

        <div class="architecture">
            <h3>🏗️ Architecture MVC</h3>
            <ul>
                <li><strong>Model:</strong> Classes métier (Donneur, Receveur)</li>
                <li><strong>View:</strong> Pages JSP dans le dossier webapp</li>
                <li><strong>Controller:</strong> Servlets dans le package controller</li>
                <li><strong>Service:</strong> Logique métier (éligibilité, compatibilité)</li>
                <li><strong>DAO:</strong> Accès aux données (simulation en mémoire)</li>
            </ul>
        </div>
    </div>
</body>
</html>
