<html>
<head>
  <title>Ajouter un donneur</title>
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
    <h2>Ajouter un donneur</h2>
    <form class="form" action="donneurs" method="post">
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
      <label>Âge
        <input class="input" type="number" name="age" min="18" required>
      </label>
      <label>Poids (kg)
        <input class="input" type="number" name="poids" step="0.1" min="0" required>
      </label>
      <div>
        <button class="btn btn-primary" type="submit">Ajouter</button>
        <a class="btn btn-outline" href="donneurs">Annuler</a>
      </div>
    </form>
  </section>
</main>

</body>
</html>
