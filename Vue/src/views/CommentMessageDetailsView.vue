<template>
   
      <nav>
        <router-link v-bind:to="{ name: 'SocialView' }">Back to Message Board</router-link>
      </nav>
      <comment-message-details v-bind:message="message" />

  </template>
  
  <script>
  import messageService from '../services/MessageService';
  import CommentMessageDetails from '../components/CommentMessageDetails.vue';
  
  export default {
    components: {
      CommentMessageDetails,
    },
    data(){
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
        .catch(error => {
            this.handleErrorResponse(error);
          });
      },
    },
    created() {
      this.getMessage(this.$route.params.messageId);
    }
  };
  </script>
  
  <style scoped>
  </style>