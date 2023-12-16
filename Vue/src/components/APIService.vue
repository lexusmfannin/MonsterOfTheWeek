<template>
    <select>
      <option v-for="(item, index) in options" :value="item.value" :key="index">
      {{ item.text }}</option>
    </select>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    name: 'AvailableOptions',
    data() {
      return {
        options: [],
        selectedOption: null
      };
    },
    mounted() {
      this.giveOptions() 
    },
    methods: {
      getRandomMonster() {
        return axios.get('http://localhost:9000/api/monsters/random')
          .then(response => response.data)
          .catch(error => {
            console.error('Error fetching random monster:', error);
            throw error;
          });
      },
      giveOptions() {
        return axios.get('http://localhost:9000/api/classes').getOptions()
        .then((options) => {
          this.options = options;
        })
        .catch(error => {
          console.error('Error fetching character classes:', error);
        });
      }
    }
  };

  </script>