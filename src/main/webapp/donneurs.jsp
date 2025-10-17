<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Liste des donneurs</title>
</head>
<body>
<h2>Liste des donneurs</h2>
<p><a href="index.jsp">Accueil</a> | <a href="ajouterDonneur.jsp">Ajouter un donneur</a></p>

<ul>
    <%
        java.util.List<com.bloodbank.model.Donneur> liste = (java.util.List<com.bloodbank.model.Donneur>) request.getAttribute("donneurs");
        if (liste != null) {
            for (com.bloodbank.model.Donneur d : liste) {
    %>
    <li>
        <%= d.getNom() %> <%= d.getPrenom() %> - <%= d.getGroupeSanguin() %>
    </li>
    <%
            }
        } else {
    %>
    <li>Aucun donneur pour le moment.</li>
    <%
        }
    %>
</ul>
</body>
</html>
