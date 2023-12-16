<template>
  <div id="custom-font">
    <h1 class="character-details">Character Details</h1>
    <CharacterDetail v-bind:character="character"/>
    <NavBar />
    <GlobalLogoHeader />
  </div>
</template>


<script>
import axios from 'axios';
import CharacterDetail from '../components/CharacterDetail.vue';
import NavBar from '../components/NavBar.vue';
import GlobalLogoHeader from '../components/GlobalLogoHeader.vue'



export default {
data(){
    return {
        character: {}
    }
},
components: {
    CharacterDetail,
    NavBar,
    GlobalLogoHeader
},
methods: {
    async fetchCharacterDetails() {
      try {
        const response = await axios.get(`http://localhost:9000/characters/${this.$route.params.id}`);
        this.character = response.data;
      } catch (error) {
        console.error('Error fetching character details:', error);
      }
    },
  },
  created() {
    this.fetchCharacterDetails();
  }  
}
</script>