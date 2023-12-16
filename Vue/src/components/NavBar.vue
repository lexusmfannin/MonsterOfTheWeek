
<template>
  <div class="blank-box" />
  <div class="navbar">
    <router-link id="nav-btn" class="home" to="/" v-if="token !== ''">
      <button id="custom-font">Home</button>
    </router-link>
    <router-link id="nav-btn" class="vote" to="/vote" v-if="token !== ''">
      <button id="custom-font">Voting</button>
    </router-link>
    <router-link id="nav-btn" class="profile" to="/profile" v-if="token !== ''">
      <button id="custom-font">My Profile</button>
    </router-link>
    <router-link id="nav-btn" class="social" to="/social" v-if="token !== ''">
      <button id="custom-font">Party</button>
    </router-link>
    <router-link id="nav-btn" class="logout" v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">
      <button id="custom-font">Logout</button>
    </router-link>
  </div>
  </template>
  
  <script>


  export default {
    methods: {
    toggleSidePanel() {
      this.$store.dispatch("toggleNav")
    }
  },
    computed: {
      token() {
        return this.$store.state.token;
      },
    },
  };
  </script>
  
<style>
#nav-btn:hover {
  box-shadow: 5px 5px 5px rgb(33, 156, 144);
}
.home {
  position: absolute;
  top: 25.8%;
  padding: .2%;
  left: .8%;
}
.profile { 
  position: absolute;
  top: 30.9%;
  padding: .2%;
  left: .8%;
}
.vote {
  position: absolute;
  top: 41%;
  padding: .2%;
  left: .8%;
}
.social {
  position: absolute;
  top: 36%;
  padding: .2%;
  left: .8%;
}


.logout {
  position: absolute;
  top: 50%;
  padding: .2%;
  left: .8%;
}
.blank-box {
  border: solid 2px;
  position: absolute;
  width: 10%;
  height: 30%;
  top: 24%;
  box-shadow:  10px 10px 10px rgb(238, 5, 5);
}
</style>