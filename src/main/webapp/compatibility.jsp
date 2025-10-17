<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Compatibilité des Groupes Sanguins</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        .container { max-width: 1200px; margin: 0 auto; }
        .section { margin: 20px 0; padding: 20px; border: 1px solid #ddd; border-radius: 5px; }
        .stats { background-color: #e8f4fd; padding: 15px; border-radius: 5px; }
        .donneur, .receveur { 
            display: inline-block; 
            margin: 10px; 
            padding: 15px; 
            border: 1px solid #ccc; 
            border-radius: 5px; 
            background-color: #f9f9f9; 
        }
        .compatible { border-color: #28a745; background-color: #d4edda; }
        .button { 
            background-color: #007bff; 
            color: white; 
            padding: 8px 16px; 
            border: none; 
            border-radius: 4px; 
            cursor: pointer; 
            text-decoration: none;
            display: inline-block;
        }
        .button:hover { background-color: #0056b3; }
        .match-button { background-color: #28a745; }
        .match-button:hover { background-color: #1e7e34; }
        .status-dispo { color: #28a745; font-weight: bold; }
        .status-non-dispo { color: #dc3545; font-weight: bold; }
        .status-en-attente { color: #ffc107; font-weight: bold; }
        .status-satisfait { color: #28a745; font-weight: bold; }
    </style>
</head>
<body>
    <div class="container">
        <h1>Banque de Sang - Compatibilité des Groupes Sanguins</h1>
        
        <div class="section">
            <a href="donneurs" class="button">Gestion Donneurs</a>
            <a href="receveurs" class="button">Gestion Receveurs</a>
            <a href="compatibility" class="button">Vue d'ensemble</a>
        </div>

        <!-- Statistiques générales -->
        <c:if test="${not empty statistiques}">
            <div class="section stats">
                <h3>Statistiques de Compatibilité</h3>
                <p>${statistiques}</p>
            </div>
        </c:if>

        <!-- Vue d'ensemble des donneurs et receveurs -->
        <c:if test="${not empty donneursDisponibles or not empty receveursEnAttente}">
            <div class="section">
                <h3>Vue d'ensemble</h3>
                
                <h4>Donneurs Disponibles</h4>
                <c:forEach var="donneur" items="${donneursDisponibles}">
                    <div class="donneur">
                        <strong>${donneur.nom} ${donneur.prenom}</strong><br>
                        Groupe: ${donneur.groupeSanguin}<br>
                        <span class="status-dispo">${donneur.statut}</span><br>
                        <a href="compatibility?action=receveurs-compatible&donneurId=${donneur.id}" 
                           class="button">Voir receveurs compatibles</a>
                    </div>
                </c:forEach>

                <h4>Receveurs en Attente</h4>
                <c:forEach var="receveur" items="${receveursEnAttente}">
                    <div class="receveur">
                        <strong>${receveur.nom} ${receveur.prenom}</strong><br>
                        Groupe: ${receveur.groupeSanguin}<br>
                        Priorité: ${receveur.priorite}<br>
                        <span class="status-en-attente">${receveur.statut}</span><br>
                        <a href="compatibility?action=donneurs-compatible&receveurId=${receveur.id}" 
                           class="button">Voir donneurs compatibles</a>
                    </div>
                </c:forEach>
            </div>
        </c:if>

        <!-- Donneurs compatibles pour un receveur -->
        <c:if test="${not empty donneursCompatible}">
            <div class="section">
                <h3>Donneurs Compatibles pour ${receveur.nom} ${receveur.prenom}</h3>
                <p>Groupe sanguin du receveur: <strong>${receveur.groupeSanguin}</strong></p>
                
                <c:forEach var="donneur" items="${donneursCompatible}">
                    <div class="donneur compatible">
                        <strong>${donneur.nom} ${donneur.prenom}</strong><br>
                        Groupe: ${donneur.groupeSanguin}<br>
                        Âge: ${donneur.age} ans<br>
                        Poids: ${donneur.poids} kg<br>
                        <span class="status-dispo">${donneur.statut}</span><br>
                        <form method="post" action="compatibility" style="display: inline;">
                            <input type="hidden" name="action" value="match">
                            <input type="hidden" name="donneurId" value="${donneur.id}">
                            <input type="hidden" name="receveurId" value="${receveur.id}">
                            <button type="submit" class="button match-button">Créer un match</button>
                        </form>
                    </div>
                </c:forEach>
            </div>
        </c:if>

        <!-- Receveurs compatibles pour un donneur -->
        <c:if test="${not empty receveursCompatible}">
            <div class="section">
                <h3>Receveurs Compatibles pour ${donneur.nom} ${donneur.prenom}</h3>
                <p>Groupe sanguin du donneur: <strong>${donneur.groupeSanguin}</strong></p>
                
                <c:forEach var="receveur" items="${receveursCompatible}">
                    <div class="receveur compatible">
                        <strong>${receveur.nom} ${receveur.prenom}</strong><br>
                        Groupe: ${receveur.groupeSanguin}<br>
                        Priorité: ${receveur.priorite}<br>
                        <span class="status-en-attente">${receveur.statut}</span><br>
                        <form method="post" action="compatibility" style="display: inline;">
                            <input type="hidden" name="action" value="match">
                            <input type="hidden" name="donneurId" value="${donneur.id}">
                            <input type="hidden" name="receveurId" value="${receveur.id}">
                            <button type="submit" class="button match-button">Créer un match</button>
                        </form>
                    </div>
                </c:forEach>
            </div>
        </c:if>

        <!-- Message de confirmation -->
        <c:if test="${param.message == 'match-success'}">
            <div class="section stats">
                <h3>✅ Match créé avec succès !</h3>
                <p>Le donneur a été marqué comme non disponible et le receveur comme satisfait.</p>
            </div>
        </c:if>
    </div>
</body>
</html>
