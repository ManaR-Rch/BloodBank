
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Accueil - BloodBank</title>
    <link rel="stylesheet" href="style.css">
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <style> .hero h1 { color: var(--primary); } </style>
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
    <section class="card hero center">
      <h1>Bienvenue</h1>
      <p class="muted">Gestion simple des donneurs, receveurs et compatibilités.</p>
      <div class="spacer"></div>
      <div>
        <a class="btn btn-primary" href="donneurs">Voir Donneurs</a>
        <a class="btn btn-success" href="receveurs">Voir Receveurs</a>
      </div>
    </section>
  </main>
</body>
</html>
