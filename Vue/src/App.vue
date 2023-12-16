<template>
  <div id="capstone-app">
    <div id="nav" v-if="isLoggedIn">
       &nbsp; | &nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
    </div>
    <router-view />
  </div>

</template>

<script>
export default {
  computed: {
    isLoggedIn() {
      return this.$store.state.isLoggedIn;
    },
    notification() {
      return this.$store.state.notification;
    },
    notificationClass() {
      return {
        'status-message': true,
        error: this.notification?.type?.toLowerCase() === 'error',
        success: this.notification?.type?.toLowerCase() === 'success'
      };
    }
  },
  methods: {
    clearNotification() {
      this.$store.commit('CLEAR_NOTIFICATION');
    }
  }
};
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Pixelify+Sans&display=swap');
#custom-font {
  font-family: 'Pixelify Sans', sans-serif;
}
#background {
  background-image: url('src/ProfileImages/background.jpg');
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
  min-height: 100vh;
}
.welcome {
  display: flex;
  position: absolute;
  bottom: 82.5vh;
  right: 40%;
  color: white;
  border-bottom: solid 5px;
  border-color: rgb(33, 156, 144);
}
.home-header{
  display: flex;
  position: absolute;
  top: 9.6vh;
  left: .3%;
  color: white;
  text-decoration: underline rgb(33, 156, 144);
  width: 6%;
}
html {
  font: 62.5% / 1.15 sans-serif;
}
body {
  width: 100%;
  margin: 0 auto;
  font:
    1.6rem/1.25 "Helvetica Neue",
    Helvetica,
    Arial,
    sans-serif;
  background-color: #f5f5f5;
}
.roll-stats {
  position: absolute;
  bottom: 11%;
}
.save-character {
  position: absolute;
  bottom: 5%;
}
.add-character {
  position: absolute;
  bottom: 10vh;
  right: 7.9%;
}
.profile-title {
  display: flex;
  position: absolute;
  bottom: 82.5vh;
  right: 40%;
  border-bottom: solid 5px;
  color: white;
  border-color: rgb(33, 156, 144);
}
.select-party {
  display: flex;
  position: absolute;
  top: 18vh;
  left: 20%;
  color: white;
  border-bottom: solid 4px;
  border-color: rgb(33, 156, 144);
}
.empty {
  position: absolute;
  bottom: 30%;
  left: 45%;
}
.character-container {
  display: inline-block;
}
.vote-btn {
  display: flex;
  position: absolute;
  bottom: 6.5vh;
  right: 50%;
}
.no-character {
  display: flex;
  position: absolute;
  top: 55vh;
  right: 45%;
  color: white;
}
.social-title {
  display: flex;
  position: absolute;
  bottom: 82.5vh;
  right: 44%;
  border-bottom: solid 5px;
  color: white;
  border-color: rgb(33, 156, 144);
}
.btn-add {
  display: flex;
  position: absolute;
  top: 35.5vh;
  right: 30%;
}
.r-monster {
  display: flex;
  position: absolute;
  bottom: 6.5vh;
  right: 44%;
}
.m-stats {
  display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: start;
  width: 87%;
  top: 14.4%;
  position: absolute;
  left: 17vh;
}
#m-name {
  border: solid 2px;
  position: absolute;
  top: 7.8vh;
  left: 8.5%;
  padding-left: .5%;
  padding-right: .5%;
}
#m-size {
  border: solid 2px;
  position: absolute;
  top: 7.8vh;
  left: 40%;
  padding-left: .5%;
  padding-right: .5%;
}
#m-challenge {
  border: solid 2px;
  position: absolute;
  top: 7.8vh;
  left: 77.4%;
  padding-left: .5%;
  padding-right: .5%;
}
#m-abilities {
  border: solid 2px;
  position: absolute;
  top: 14vh;
  left: 8.5%;
  width: 60vh;
  height: 52.8vh;
  overflow: scroll;
  padding-left: .5%;
}
#m-img {
  width: 52.7vh;
  position: absolute;
  top: 15.7vh;
  left: 55.9%;
  height: 53.1vh;
}
.character-details {
  display: flex;
  position: absolute;
  top: 20vh;
  right: 40%;
  color: white;
  border-bottom: solid 5px;
  border-color: rgb(33, 156, 144);
}
.detail-set1 {
  display: flex;
  flex-direction: column;
  max-width: 8%;
  position: relative;
  top: 32vh;
  left: 78.5vh;
  color: white;
}
.detail-set2 {
  display: flex;
  flex-direction: column;
  max-width: 30%;
  position: relative;
  top: 8.6vh;
  left: 95.5vh;
  color: white;
}
.detail-name {
  position: absolute;
  top: 27vh;
  left: 46%;
  color: white;
}
.detail-option {
  display: flex;
  align-items: center;
  justify-content: space-between;
  position: absolute;
  width: 13%;
  bottom: 40vh;
  left: 44%;
}
.edit-character {
  display: flex;
  position: relative;
  right: -850px;
  bottom: -300px;
  width: 90%;
  flex-direction: column;
  align-items: flex-start;
  margin-bottom: 20px;

}





.random-img {
  top: 40vh;
  left: 50%;
}
.character-item {
  position: relative;
  left: 20%;
  top: 30vh;
  width: 90%;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  margin-bottom: 20px;
  list-style: none; 
  cursor: pointer;
}
.image-container {
  display: inline-block;
}
.detailed-stats {
  max-width: 70%;
  border: solid 4px;
  position: relative;
  bottom: 15vh;
  left: 36%;
  opacity: 0;
  transition: opacity 0.3s ease;
  background: white;
  padding: 10px;
  z-index: 1;
}
.character-item:hover .detailed-stats {
  opacity: 1;
}
.image-container img {
  position: relative;
  left: 21%;
  bottom: 3vh;
  width: 10vh;
}
.edit-character {
  position: absolute;
  top: 30%;
  left: 43%;
  color: black;
}
.save-character {
  position: relative;
  top: 50%;
}

</style>