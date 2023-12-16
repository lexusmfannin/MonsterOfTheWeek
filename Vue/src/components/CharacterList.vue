<template>
  <div>
    <div v-for="(character, index) in characters" :key="index" class="character-item">
      <div class="character-picture" v-on:mouseover="showAttributes(character)" @mouseleave="hideAttributes">
        <img :src="character.picture" alt="Character Picture" />
        <div v-if="showTooltip" class="tooltip" :style="{ top: tooltipPosition.top + 'px', left: tooltipPosition.left + 'px' }">
          <p>Name: {{ character.characterName }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    characters: {
      type: Array,
      required: true,
    },
  },
  data() {
    return {
      showTooltip: false,
      tooltipPosition: { top: 0, left: 0 },
    };
  },
  methods: {
    showAttributes(character) {
      this.showTooltip = true;
      this.tooltipPosition = { top: event.clientY, left: event.clientX };
    },
    hideAttributes() {
      this.showTooltip = false;
    },
  },
};
</script>

<style scoped>
.character-item {
  margin-right: 20px;
}

.character-picture {
  position: relative;
  border: solid 3px;
  border-color: blue;
  border: 5px;
  cursor: pointer;
}

.tooltip {
  position: fixed;
  background-color: #fff;
  border: 1px solid #ccc;
  padding: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  z-index: 999;
}

.character-picture:hover .tooltip {
  display: block;
}
</style>
