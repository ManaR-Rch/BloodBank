<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Affectation Donneur → Receveur</title>
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
<h2>Affectation d'un donneur à un receveur</h2>

<% String message = request.getParameter("message"); if (message != null) { %>
    <p><strong><%= message %></strong></p>
<% } %>

<ul class="list">
<%
    java.util.List<com.bloodbank.model.Receveur> enAttente = (java.util.List<com.bloodbank.model.Receveur>) request.getAttribute("receveursEnAttente");
    if (enAttente != null && !enAttente.isEmpty()) {
        for (com.bloodbank.model.Receveur r : enAttente) {
%>
    <li>
        <div>
          <strong><%= r.getNom() %> <%= r.getPrenom() %></strong>
          <span class="badge"><%= r.getGroupeSanguin() %></span>
          <span class="badge">Priorité: <%= r.getPriorite() %></span>
        </div>
        <form action="affectation" method="post" style="display:inline; margin-left:10px;">
            <input type="hidden" name="receveurId" value="<%= r.getId() %>">
            <button class="btn btn-success" type="submit">Affecter un donneur</button>
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
</section>
</main>

</body>
</html>


