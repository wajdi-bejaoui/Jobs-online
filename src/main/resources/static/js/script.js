function validateForm(event) {
    event.preventDefault();

    var projectTitle = document.getElementById("projectTitle").value;
    var projectDescription = document.getElementById("projectDescription").value;
    var projectBudget = document.getElementById("projectBudget").value;

    if (projectTitle === "" || projectDescription === "" || projectBudget === "") {
        alert("Veuillez remplir tous les champs du formulaire.");
        return;
    }

    // Vous pouvez ajouter ici la logique pour soumettre les données au serveur
    // par exemple, en utilisant une requête AJAX.

    alert("Offre de projet ajoutée avec succès!");
    document.getElementById("projectForm").reset();
}
