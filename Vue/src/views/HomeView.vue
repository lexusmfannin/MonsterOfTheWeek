<template>
  <div id="background">
    <h1 id="custom-font" class="home-header">Home</h1>
    <NavBar />
    <MOTW />
    <Welcome />
    <GlobalLogoHeader />
  </div>
</template>

<script>
import NavBar from '../components/NavBar.vue';
import MOTW from '../components/MOTW.vue';
import Welcome from '../components/Welcome.vue';
import GlobalLogoHeader from '../components/GlobalLogoHeader.vue';

export default {
  components: {
    NavBar,
    MOTW,
    Welcome,
    GlobalLogoHeader
  }
};
</script>

<style>

</style>