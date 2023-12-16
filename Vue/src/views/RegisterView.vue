<template>
  <div id="custom-font" class="login-container">
    <form v-on:submit.prevent="register">
      <h1 class="register" id="signInText">Create Account</h1>
      <div role="alert" id="alertId" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <div class="form-input-group" id="user">
        <label for="username">Username</label>
        <input type="text" id="username" v-model="user.username" required autofocus />
      </div>
      <div class="form-input-group" id="pass">
        <label for="password">Password</label>
        <input type="password" id="password" v-model="user.password" required />
      </div>
      <div class="form-input-group" id="pass">
        <label for="confirmPassword">Confirm Password</label>
        <input type="password" id="confirmPassword" v-model="user.confirmPassword" required />
      </div>
      <button class="register" type="submit">Create Account</button>
      <p><router-link class="register" v-bind:to="{ name: 'login' }">Already have an account? Log in.</router-link></p>
    </form>
  </div>
</template>

<script>
import authService from '../services/AuthService';

export default {
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      registrationErrors: false,
      registrationErrorMsg: '*There were problems registering this user.*',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = "*There were problems registering this user.*";
    },
  },
};
</script>


<style scoped>
.login-container {
  background-image: url('../loginImages/LoginBG.jpg');
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
/* width: 40vh; */
#signInText {
  width: 22vh;
}
.register {
  position: relative;
  left: 42.5%;
  top: 45vh;
  color: rgb(245, 159, 48);
}
#user {
  display: grid;
  grid-template-columns: 15%;
  color: white;
  justify-content: center;
}
#pass {
  display: grid;
  grid-template-columns: 15%;
  color: white;
  justify-content: center;
}
.form-input-group {
  position: relative;
  top: 45vh;
  grid-template-columns: 10%;
}
label {
  margin-right: 0.5rem;
 
}

#alertId {
  color: rgb(238, 223, 10);
  text-align: center;
  font-size: 30px;
  padding: 0px;
  margin: 0px;
}
</style>

