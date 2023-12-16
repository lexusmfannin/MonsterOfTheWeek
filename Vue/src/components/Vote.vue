<template>
  <h1 id="custom-font" class="social-title">Let's Battle!</h1>
     <div id="custom-font">
      <div class="voting-form">
        <div class="pick-party">
          <h3>Select Who You Want To Battle The Monster</h3>
          <ul class="party-name">
            <li v-for="party in filteredparties" v-bind:key="party.name"
                v-bind:class="{ finished: party.selected }">
              <input name="selected-checkbox" type="checkbox" v-model="party.selected" />
              {{ party.name }}
            </li>
          </ul>
          <div>
            <button id="custom-font" class="vote-btn" v-on:click="resetForm()">Vote</button>
          </div>
        </div>
      </div>
      
    </div>
    <NavBar />
</template>
  
<script>
import NavBar from './NavBar.vue';
import axios from 'axios';
  
  export default {
    components: {
      NavBar,
    },
    data() {
      return {
        filterText: '',
        parties: [
          {
            name: "Scout's Honor",
            selected: false
          },
          {
            name: "Blue Gelly",
            selected: false
          },
          {
            name: "Purple Dragons",
            selected: false
          },
          {
            name: "Green Lizards",
            selected: false
          }
        ],
      };
    },
    // mounted() {
    //   this.fetchParties();
    // },
    computed: {
      filteredparties() {
      return this.parties.filter((party) => {
        return party.name.includes(this.filterText);
      });
    }
    },
    // methods: {
    //   async fetchParties() {
    //     try {
    //       const response = await axios.get('/api/parties');
    //       this.parties = response.data;
    //     } catch (error) {
    //       console.error('Error fetching parties:', error);
    //     }
    //   },
    //   voteForSelectedUsers() {
    //   },
    //   getCharacterName(characterId) {
    //   },
    // },
    methods: {
      resetForm() {
            this.filterText = '';
            this.parties = [];
          },
    }
  };
  </script>
  
  <style>
  .pick-party {
    grid-area: pick-party;
  }
  .party-name {
    grid-area: party-name;
  }
  .voting-form {
    display: grid;
    grid-template-rows: 1fr 1fr;
    grid-template-areas: "pick-party"
                         "party-name";
    justify-content: center; 
    position: relative;
    top: 40vh;
    left: 35vh;
    width: 60%;
    color: white;
  }

  </style>
  