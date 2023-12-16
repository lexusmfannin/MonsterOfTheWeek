<template>
  <div id="custom-font">
    <form v-on:submit.prevent="submitForm">
      <div class="field">
        <label for="title">Title</label>
        <input type="text" id="title" name="title" v-model="editMessage.title" />
      </div>
      <div class="field">
        <label for="messageText">Message</label>
        <textarea id="messageText" name="messageText" v-model="editMessage.messageText" />
      </div>
      <div class="actions">
        <button id="custom-font" class="btn-submit" type="button">Submit</button> 
        <button id="custom-font" class="btn-cancel" type="button" v-on:click="cancelForm">Cancel</button>
      </div>
    </form>
    </div>
  </template>
  
  <script>
  import messageService from '../services/MessageService';
  import axios from 'axios';
  
  export default {
    props: {
      message: {
        type: Object,
        required: true
      },
    },
    data() {
      return {
        //initialize a new object with the same property values.
        editMessage: {
          id: this.message.id,
          created: this.message.created,
          title: this.message.title,
          messageText: this.message.messageText,
        }
      };
    },
    methods: {
      submitForm() {
        // Do client-side form validation 
        if (!this.validateForm()) {
          //Form isn't valid, user must update and submit again.
          return;
        }
        // Check for add or edit
        if (this.editMessage.id === 0) {
          // For errors, call handleErrorResponse
          messageService.create(this.editMessage)
            .then(response => {
              if(response.status == 201) {
                this.$router.push({name: 'SocialView'});
              }
            })
            .catch(error => {
              this.handleErrorResponse(error, 'adding');
            });
        } else {
          // For errors, call handleErrorResponse
          messageService.update(this.message.id, this.editMessage)
            .then(response => {
              if (response.status == 200) {
                this.$router.push({name: 'SocialView'});
              }
            })
            .catch(error => {
              this.handleErrorResponse(error), 'updating';
            });
        }
      },
      cancelForm() {
        this.$router.back();
      },
      },
      validateForm() {
        let msg = '';
  
        this.editMessage.title = this.editMessage.title.trim();
        if (this.editMessage.title.length === 0) {
          msg += 'The new message must have a title. ';
        }
  
        this.editMessage.messageText = this.editMessage.messageText.trim();
        if (this.editMessage.messageText.length === 0) {
          msg += 'The message must contain message text.';
        }

        if (msg.length > 0) {
        this.$store.commit('SET_NOTIFICATION', msg);
        return false;
      }
        
        return true;
      },
    };
  </script>
  
  <style>
  </style>