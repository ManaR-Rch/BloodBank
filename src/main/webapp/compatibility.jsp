<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Compatibilité des Groupes Sanguins</title>
    <link rel="stylesheet" href="style.css">
    <meta name="viewport" content="width=device-width, initial-scale=1" />
</head>
<body>
  <header class="app-header">
    <div class="container row">
      <a class="brand" href="index.jsp"><span class="dot"></span> BloodBank</a>
      <nav class="nav">
        <a href="donneurs">Donneurs</a>
        <a href="ajouterDonneur.jsp">Ajouter Donneur</a>
        <a href="receveurs">Receveurs</a>
        <a href="ajouterReceveur.jsp">Ajouter Receveur</a>
        <a href="compatibility">Compatibilités</a>
        <a href="affectation">Affectation</a>
      </nav>
    </div>
  </header>

  <main class="container stack">
    <section class="card">
        <h1>Compatibilité des Groupes Sanguins</h1>
        <div>
            <a href="donneurs" class="btn btn-outline">Gestion Donneurs</a>
            <a href="receveurs" class="btn btn-outline">Gestion Receveurs</a>
            <a href="compatibility" class="btn btn-outline">Vue d'ensemble</a>
        </div>

        <!-- Statistiques générales -->
        <c:if test="${not empty statistiques}">
            <div class="card">
                <h3>Statistiques de Compatibilité</h3>
                <p>${statistiques}</p>
            </div>
        </c:if>

        <!-- Vue d'ensemble des donneurs et receveurs -->
        <c:if test="${not empty donneursDisponibles or not empty receveursEnAttente}">
            <div class="card">
                <h3>Vue d'ensemble</h3>
                
                <h4>Donneurs Disponibles</h4>
                <c:forEach var="donneur" items="${donneursDisponibles}">
                    <div class="card" style="display:inline-block; margin:8px;">
                        <strong>${donneur.nom} ${donneur.prenom}</strong><br>
                        Groupe: ${donneur.groupeSanguin}<br>
                        <span class="badge badge-success">${donneur.statut}</span><br>
                        <a href="compatibility?action=receveurs-compatible&donneurId=${donneur.id}" 
                           class="btn btn-primary">Voir receveurs compatibles</a>
                    </div>
                </c:forEach>

                <h4>Receveurs en Attente</h4>
                <c:forEach var="receveur" items="${receveursEnAttente}">
                    <div class="card" style="display:inline-block; margin:8px;">
                        <strong>${receveur.nom} ${receveur.prenom}</strong><br>
                        Groupe: ${receveur.groupeSanguin}<br>
                        Priorité: ${receveur.priorite}<br>
                        <span class="badge badge-warn">${receveur.statut}</span><br>
                        <a href="compatibility?action=donneurs-compatible&receveurId=${receveur.id}" 
                           class="btn btn-primary">Voir donneurs compatibles</a>
                    </div>
                </c:forEach>
            </div>
        </c:if>

        <!-- Donneurs compatibles pour un receveur -->
        <c:if test="${not empty donneursCompatible}">
            <div class="card">
                <h3>Donneurs Compatibles pour ${receveur.nom} ${receveur.prenom}</h3>
                <p>Groupe sanguin du receveur: <strong>${receveur.groupeSanguin}</strong></p>
                
                <c:forEach var="donneur" items="${donneursCompatible}">
                    <div class="card" style="display:inline-block; margin:8px;">
                        <strong>${donneur.nom} ${donneur.prenom}</strong><br>
                        Groupe: ${donneur.groupeSanguin}<br>
                        Âge: ${donneur.age} ans<br>
                        Poids: ${donneur.poids} kg<br>
                        <span class="badge badge-success">${donneur.statut}</span><br>
                        <form method="post" action="compatibility" style="display: inline;">
                            <input type="hidden" name="action" value="match">
                            <input type="hidden" name="donneurId" value="${donneur.id}">
                            <input type="hidden" name="receveurId" value="${receveur.id}">
                            <button type="submit" class="btn btn-success">Créer un match</button>
                        </form>
                    </div>
                </c:forEach>
            </div>
        </c:if>

        <!-- Receveurs compatibles pour un donneur -->
        <c:if test="${not empty receveursCompatible}">
            <div class="card">
                <h3>Receveurs Compatibles pour ${donneur.nom} ${donneur.prenom}</h3>
                <p>Groupe sanguin du donneur: <strong>${donneur.groupeSanguin}</strong></p>
                
                <c:forEach var="receveur" items="${receveursCompatible}">
                    <div class="card" style="display:inline-block; margin:8px;">
                        <strong>${receveur.nom} ${receveur.prenom}</strong><br>
                        Groupe: ${receveur.groupeSanguin}<br>
                        Priorité: ${receveur.priorite}<br>
                        <span class="badge badge-warn">${receveur.statut}</span><br>
                        <form method="post" action="compatibility" style="display: inline;">
                            <input type="hidden" name="action" value="match">
                            <input type="hidden" name="donneurId" value="${donneur.id}">
                            <input type="hidden" name="receveurId" value="${receveur.id}">
                            <button type="submit" class="btn btn-success">Créer un match</button>
                        </form>
                    </div>
                </c:forEach>
            </div>
        </c:if>

        <!-- Message de confirmation -->
        <c:if test="${param.message == 'match-success'}">
            <div class="card">
                <h3>✅ Match créé avec succès !</h3>
                <p>Le donneur a été marqué comme non disponible et le receveur comme satisfait.</p>
            </div>
        </c:if>
  </main>
</body>
</html>
