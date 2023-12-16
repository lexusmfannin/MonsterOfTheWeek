<template>
  
    <div id="background">
      
      <GlobalLogoHeader/>
      <Vote />
    </div>
    <main>
      <!-- /*Want to display the MOTW details*/ -->
  
      <!-- <CharacterList /> -->
      
    </main>
  </template>
  
  <script>
  import Vote from '../components/Vote.vue';
  import CharacterList from '../components/CharacterList.vue'
  import GlobalLogoHeader from '../components/GlobalLogoHeader.vue';


  export default {
    components: {
    // CharacterList,
    Vote,
    GlobalLogoHeader,
},

  data() {
    return {

    };
  },
};

  </script>