new Vue({
    el: '#app',
    data: {
      competence: '',
      experience: '',
      diploma: '',
      result: '',
    },
    methods: {
      submitForm() {
        // Simuler un traitement ou un test basé sur les entrées de l'utilisateur
        this.result = `Compétence: ${this.competence}, Expérience: ${this.experience} années, Diplôme: ${this.diploma}`;
      },
    },
    template: `
      <div class="container">
        <header>
          <h1>Test de Compétence</h1>
        </header>
  
        <div class="form">
          <div class="form-section">
            <label for="competence">Compétence:</label>
            <select v-model="competence" required>
              <option value="" disabled selected>Choisissez votre compétence</option>
              <option value="beginner">Débutant</option>
              <option value="intermediate">Intermédiaire</option>
              <option value="advanced">Avancé</option>
            </select>
          </div>
  
          <div class="form-section">
            <label for="experience">Expérience (en années):</label>
            <input type="number" v-model="experience" min="0" required>
          </div>
  
          <div class="form-section">
            <label for="diploma">Diplôme:</label>
            <input type="text" v-model="diploma" required>
          </div>
  
          <button @click="submitForm">Soumettre</button>
        </div>
  
        <div v-if="result" class="result">
          <p>{{ result }}</p>
        </div>
      </div>
    `,
  });
  