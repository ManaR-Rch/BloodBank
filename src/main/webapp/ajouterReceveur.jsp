<html>
<head>
  <title>Ajouter un receveur</title>
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

<main class="container">
  <section class="card">
    <h2>Ajouter un receveur</h2>
    <form class="form" action="receveurs" method="post">
      <label>Nom
        <input class="input" type="text" name="nom" required>
      </label>
      <label>Prénom
        <input class="input" type="text" name="prenom" required>
      </label>
      <label>CIN
        <input class="input" type="text" name="cin" required>
      </label>
      <label>Téléphone
        <input class="input" type="text" name="telephone">
      </label>
      <label>Groupe sanguin
        <input class="input" type="text" name="groupe" placeholder="ex: O-" required>
      </label>
      <div>
        <button class="btn btn-success" type="submit">Ajouter</button>
        <a class="btn btn-outline" href="receveurs">Annuler</a>
      </div>
    </form>
  </section>
</main>

</body>
</html>


