<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Liste des donneurs</title>
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
<h2>Liste des donneurs</h2>

<ul class="list">
    <%
        java.util.List<com.bloodbank.model.Donneur> liste = (java.util.List<com.bloodbank.model.Donneur>) request.getAttribute("donneurs");
        if (liste != null) {
            for (com.bloodbank.model.Donneur d : liste) {
    %>
    <li>
        <div>
          <strong><%= d.getNom() %> <%= d.getPrenom() %></strong>
          <span class="badge"><%= d.getGroupeSanguin() %></span>
        </div>
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
<div>
  <a class="btn btn-primary" href="ajouterDonneur.jsp">Ajouter un donneur</a>
</div>
</section>
</main>
</body>
</html>
