<template>
    <h1>Add New Comment</h1>
    <comment-message-form v-bind:message="message" />
  </template>
  
  <script>
  import CommentMessageForm from '../components/CommentMessageForm.vue';
  
  export default {
    components: {
      CommentMessageForm
    },
    data() {
      return {
        message: {
          id: 0,
          created: new Date(),
          title: '',
          messageText: ''
        },
      }
    } 
  };
  </script>
  
  <style scoped>
  </style>