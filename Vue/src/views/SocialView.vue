<template>
  <div id="background">
    <h1 id="custom-font" class="social-title">Let's Party!</h1>
  </div>
  <global-logo-header />
  <NavBar />
  <PartyGroup />
</template>

<script>  
import MessageService from '../services/MessageService.js';

import GlobalLogoHeader from '../components/GlobalLogoHeader.vue';
import NavBar from '../components/NavBar.vue';
import PartyGroup from '../components/PartyGroup.vue';

export default {
components: {
    GlobalLogoHeader,
    PartyGroup,
    NavBar
},
data() {
  return {
  }
},
};
</script>