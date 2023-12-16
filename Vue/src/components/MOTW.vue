<template>
  <div id="custom-font">
    <div class="m-stats" v-if="singleMonster">
      <p class="m-name">Name: {{ singleMonster.name }}</p>
      <p class="m-size">Size: {{ singleMonster.size }}</p>
      <p class="m-challenge">Challenge Rating: {{ singleMonster.challenge_rating }}</p>
      <div class="m-abilities">
        <p>Special Abilities:</p>
        <ul>
          <li v-for="(ability, index) in singleMonster.special_abilities" :key="index">
            <strong>{{ ability.name }}:</strong> {{ ability.desc }}
          </li>
        </ul>
        <p>Attacks:</p>
        <ul>
          <li v-for="(actions, index) in singleMonster.special_abilities" :key="index">
            <strong>{{ actions.name }}:</strong> {{ actions.desc }}
          </li>
        </ul>
      </div>
  <img class="m-img" :src="monsterImageUrl" alt="Monster Image" @error="handleImageError" />
    </div>
    <button id="custom-font" class="r-monster" v-on:click="getSingleMonster">Monster Demo</button>
  </div>
  <!-- <img :src="monsterImageUrl()" /> -->
</template>
<script>
import axios from 'axios';
export default {
  data() {
    return {
      singleMonster: null,
      imageUrl: "https://www.dnd5eapi.co/api/images/monsters/adult-black-dragon.png",
    };
  },
  computed: {
  monsterImageUrl() {
    if (this.singleMonster.index) {
      const index = this.singleMonster.index.toLowerCase();
      return `https://www.dnd5eapi.co/api/images/monsters/${index}.png`;
    }else
      return '';
    // return 'https://www.dnd5eapi.co/api/images/monsters/adult-black-dragon.png';
    }
  },
  methods: {
  async getSingleMonster() {
    try {
      const index = 'adult-black-dragon';
      const response = await axios.get(`/api/monsters/${index}`);
      this.singleMonster = response.data;
      console.log(response.data);
    } catch (error) {
      console.error('Error fetching monster:', error);
    }
  },
},
};
</script>
<style>
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
.m-name {
  border: solid 2px;
  position: absolute;
  top: 7.8vh;
  left: 8.5%;
  padding-left: .5%;
  padding-right: .5%;
  color: white;
}
.m-size {
  border: solid 2px;
  position: absolute;
  top: 7.8vh;
  left: 40%;
  padding-left: .5%;
  padding-right: .5%;
  color: white;
}
.m-challenge {
  border: solid 2px;
  position: absolute;
  top: 7.8vh;
  left: 77.4%;
  padding-left: .5%;
  padding-right: .5%;
  color: white;
}
.m-abilities {
  border: solid 2px;
  position: absolute;
  top: 14vh;
  left: 8.5%;
  width: 60vh;
  height: 52.8vh;
  overflow: scroll;
  padding-left: .5%;
  color: white;
  background-color: black;
}
.m-img {
  width: 52.7vh;
  position: absolute;
  top: 15.7vh;
  left: 55.9%;
  height: 53.1vh;
}
</style>