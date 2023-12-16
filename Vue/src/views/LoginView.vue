<template>
  <div id="custom-font" class="login-container">
    <form v-on:submit.prevent="login">
      <h1 class="register" id="signInText">WHO ENTERS THE DUNGEON?!</h1>
      <div class="alert" role="alert" v-if="invalidCredentials">
        Invalid username and password!
      </div>
      <div class="alert" role="alert" v-if="this.$route.query.registration">
        Thank you for registering, please sign in.
      </div>
      <div class="form-input-group" id="user">
        <label for="username">Username</label>
        <input type="text" id="username" v-model="user.username" required autofocus />
      </div>
      <div class="form-input-group" id="pass">
        <label for="password">Password</label>
        <input type="password" id="password" v-model="user.password" required />
      </div>
      <div>
      </div>
      <button id="sign-in-btn" class="register" type="submit">Sign in</button>
      <p>
      <router-link class="register" v-bind:to="{ name: 'register' }">Need an account? Sign up.</router-link></p>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {

      const lowercaseUsername = this.user.username.toLowerCase();
      const userWithLowercaseUsername = { ...this.user, username: lowercaseUsername };

      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>

<style scoped>
#sign-in-btn {
  color: black;
}
.login-container {
  background-image: url('../loginImages/LoginBG2.png');
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
  min-height: 100vh;
}
.container{
  position: relative;
  left: 48%;
  top: 51vh;
  border: solid 2px;
}
#signInText {
  display: flex;
  justify-content: center;
  position: absolute;
  top: 54vh;
  left: 20%;
  width: 60%;
}
.register {
  position: relative;
  left: 42.5%;
  top: 65vh;
  color: white;
}
#user {
  position: relative;
  top: 62vh;
  display: grid;
  grid-template-columns: 15%;
  color: white;
  justify-content: center;
}
#pass {
  position: relative;
  top: 62vh;
  display: grid;
  grid-template-columns: 15%;
  color: white;
  justify-content: center;
}
.form-input-group {
  position: relative;
  top: 55vh;
  grid-template-columns: 10%;
}
label {
  margin-right: 0.5rem;
}
.alert {
  color: yellow;
  font-size: larger;
  position: relative;
  left: 40.6%;
  top: 61vh;
}
</style>

