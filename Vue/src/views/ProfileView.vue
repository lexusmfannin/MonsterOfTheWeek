<template>
  <div id="background">
    <NavBar />
    <Profile />
    <GetParty />
    <GlobalLogoHeader />
    <CharacterList :characters="yourCharacters" />
  </div>
</template>

<script>
import axios from 'axios';
import Profile from '../components/Profile.vue'
import GlobalLogoHeader from '../components/GlobalLogoHeader.vue';
import CharacterList from '../components/CharacterList.vue';
import GetParty from '../components/GetParty.vue';
import NavBar from '../components/NavBar.vue';

export default {
  components: {
    NavBar,
    Profile,
    GlobalLogoHeader,
    CharacterList,
    GetParty,
  },
};
</script>
