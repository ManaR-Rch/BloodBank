<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Liste des receveurs</title>
</head>
<body>
<h2>Liste des receveurs</h2>
<p><a href="index.jsp">Accueil</a> | <a href="ajouterReceveur.jsp">Ajouter un receveur</a></p>

<ul>
    <%
        java.util.List<com.bloodbank.model.Receveur> liste =
                (java.util.List<com.bloodbank.model.Receveur>) request.getAttribute("receveurs");
        if (liste != null) {
            for (com.bloodbank.model.Receveur r : liste) {
    %>
    <li>
        <%= r.getNom() %> <%= r.getPrenom() %> - <%= r.getGroupeSanguin() %> - Priorité: <%= r.getPriorite() %> - Statut: <%= r.getStatut() %>
    </li>
    <%
            }
        } else {
    %>
    <li>Aucun receveur pour le moment.</li>
    <%
        }
    %>
</ul>

</body>
</html>
