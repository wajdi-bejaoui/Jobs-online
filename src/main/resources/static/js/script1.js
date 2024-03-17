document.addEventListener('DOMContentLoaded', function () {
    const form = document.getElementById('application-form');
  
    form.addEventListener('submit', function (event) {
      event.preventDefault();
      // Ajoutez ici la logique de soumission du formulaire
      alert('Formulaire soumis avec succès!');
    });
  });
  