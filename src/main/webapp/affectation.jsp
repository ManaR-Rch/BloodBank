<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Affectation Donneur → Receveur</title>
</head>
<body>
<h2>Affectation d'un donneur à un receveur</h2>
<p><a href="index.jsp">Accueil</a></p>

<% String message = request.getParameter("message"); if (message != null) { %>
    <p><strong><%= message %></strong></p>
<% } %>

<ul>
<%
    java.util.List<com.bloodbank.model.Receveur> enAttente = (java.util.List<com.bloodbank.model.Receveur>) request.getAttribute("receveursEnAttente");
    if (enAttente != null && !enAttente.isEmpty()) {
        for (com.bloodbank.model.Receveur r : enAttente) {
%>
    <li>
        <%= r.getNom() %> <%= r.getPrenom() %> - <%= r.getGroupeSanguin() %> - Priorité: <%= r.getPriorite() %>
        <form action="affectation" method="post" style="display:inline; margin-left:10px;">
            <input type="hidden" name="receveurId" value="<%= r.getId() %>">
            <button type="submit">Affecter un donneur</button>
        </form>
    </li>
<%
        }
    } else {
%>
    <li>Aucun receveur en attente.</li>
<%
    }
%>
</ul>

</body>
</html>


