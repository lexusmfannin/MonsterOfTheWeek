<template>
    <div >
      <h1>Edit Message</h1>
      <comment-message-form v-bind:message="message" />
    </div>
  </template>
  
  <script>
  
  import messageService from '../services/MessageService';
  import CommentMessageForm from '../components/CommentMessageForm.vue';
  import axios from 'axios';
  
  export default {
    components: {
      CommentMessageForm
    },
    data() {
      return {
        message: {},
        isLoading: true
      }
    },
    methods: {
      getMessage(id) {
        messageService.get(id)
          .then(response => {
            this.message = response.data;
            this.isLoading = false;
          })
      }
    },
    
    created() {
      this.getMessage(this.$route.params.messageId);
    } 
  };
  </script>