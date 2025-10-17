<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Liste des receveurs</title>
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
<h2>Liste des receveurs</h2>

<ul class="list">
    <%
        java.util.List<com.bloodbank.model.Receveur> liste =
                (java.util.List<com.bloodbank.model.Receveur>) request.getAttribute("receveurs");
        if (liste != null) {
            for (com.bloodbank.model.Receveur r : liste) {
    %>
    <li>
        <div>
          <strong><%= r.getNom() %> <%= r.getPrenom() %></strong>
          <span class="badge"><%= r.getGroupeSanguin() %></span>
          <span class="badge <%= ("SERVI".equals(String.valueOf(r.getStatut())) ? "badge-success" : "badge-warn") %>">Statut: <%= r.getStatut() %></span>
          <span class="badge">Priorité: <%= r.getPriorite() %></span>
        </div>
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
<div>
  <a class="btn btn-success" href="ajouterReceveur.jsp">Ajouter un receveur</a>
  <a class="btn btn-outline" href="affectation">Aller à l'affectation</a>
</div>
</section>
</main>

</body>
</html>
