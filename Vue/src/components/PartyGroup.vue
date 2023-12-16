<template>
  <div id="custom-font">
    <div class="party-container">
      <form v:on:submit.prevent="createParty">
        <label for="partyName">Party Name: </label>
        <input v-model="partyName" type="text" />
    
        <div>
          <h3>Select Characters for the Party:</h3>
          <ul>
              <li v-for="character in userCharacters" :key="character.character_id">
                  <input
                  type="checkbox"
                  :id="'characterCheckbox_' + character.character_id"
                  :value="character.character_id"
                  :checked="selectedCharacters.includes(character.character_id)"
                  @change="toggleCharacterSelection(character)"
                  />
                  <label :for="'characterCheckbox_' + character.character_id">
                  {{ character.character_name }} | {{ character.creature }} | {{ character.class_profession }} | Strength: {{ character.character_strength }} | 
                  Dexterity: {{ character.character_dexterity }} | Constitution: {{ character.character_constitution }} | Intelligence: {{ character.character_intelligence }} |
                  Wisdom: {{ character.character_wisdom }} | Charisma: {{ character.character_charisma }}
              </label>
            </li>
          </ul>
        </div>
        <div>
      
          <ul>
            <li v-for="characterId in selectedCharacters" :key="characterId">
              {{ getCharacterName(characterId) }}
            </li>
          </ul>
        </div>

        <button @click="createParty">Create Party</button>
      </form>
  
      
    </div>
  </div>
  </template>
  
  <script>
  import axios from 'axios';

  export default {
    data() {
      return {
        partyName: '',
        selectedCharacters: [],
        userCharacters: [],
      };
    },
    mounted() {
      this.fetchCharacters();
    },
    methods: {
      toggleCharacterSelection(character) {
          const characterId = character.id; 
          const index = this.selectedCharacters.indexOf(characterId);

          if (index === -1) {
            this.selectedCharacters.push(characterId);
          } else {
            this.selectedCharacters.splice(index, 1);
          }
        },
      createParty() {
        console.log('Party object:', {
            partyName: this.partyName,
            characterIds: this.selectedCharacters,
          });
          if (this.selectedCharacters.length > 4) {
            console.error('Cannot select more than 4 characters.');
            return;
          }

          const party = {
            partyName: this.partyName,
            characterIds: [...this.selectedCharacters], 
          };

          axios.post('/party/createParty', party)
            .then(response => {
              console.log('Party created successfully:', response.data);
              this.selectedCharacters = []; 
            })
            .catch(error => {
              console.error('Error creating party:', error);
            });

          this.resetForm();
        
        },
        resetForm() {
            this.partyName = '';
            this.selectedCharacters = [];
            this.userCharacters = [];
          },
      async fetchCharacters() {
        try {
          const response = await axios.get(`http://localhost:9000/characters/all`);
          this.userCharacters = [...response.data];
          console.log('Characters:', this.userCharacters);
        } catch (error) {
          console.error('Error fetching characters:', error);
        }
      },
      // getCharacterName(characterId) {
      //   const character = this.userCharacters.find(char => char.character_id === characterId);
      //   return character ? character.character_name : 'Unknown Character';
      // },
    },
  };
</script>

<style>
.party-container {
  display: grid;
  grid-template-rows: 1fr;
  grid-template-areas: "party-on"
                       ;
  justify-content: center; 
  position: absolute;
  left: 36vh;
  bottom: 50vh;
  width: 60%;
  color: white;
}
</style>