<template>
    <div id="cards_board">
        <div id="cards_header"></div>
        <div id="cards_box">
            <view v-for="plant in props.plants" :key="plant.name_zh">
                <div style="float: left; width: 20%; overflow: hidden">
                    <img :src="plant.url" :id="plant.id" class="card_img" @click="look_up_flower(plant.id)" />
                    <p class="plant_name">{{ plant.name_zh }}</p>
                </div>
            </view>
        </div>
    </div>
</template>

<script>
export default {
    name: "CardsBoard",
};
</script>
<script setup>
import { defineProps, defineEmits } from "vue";
const emits = defineEmits(['postSearchRequest']);

const props = defineProps({ plants: Array })
const look_up_flower = (plantId) => {
    emits('postSearchRequest', plantId)

};
</script>
<style scoped>
/*只在 webkit 核心的浏览器中有效，例如 Chrome、新 Edge、Safari 等*/
::-webkit-scrollbar {
    display: none;
}

#cards_board {
    background: rgba(211, 237, 213, 0.3);
    overflow-y: scroll;
    width: 100vw;
    height: 90vh;
    position: relative;
    top: -2.5vh;
}

#cards_header {
    height: 0vh;
    background: rgba(211, 237, 213, 0.8);
}

#cards_box {
    padding: 5vh 6vw;
    display: block;
}

.card_img {
    width: 100%;
    aspect-ratio: 1;
    object-fit: cover;
    cursor: pointer;
    transition: all 0.6s;
}

.card_img:hover {
    transform: scale(1.4);
}

.plant_name {
    position: relative;
    margin-left: 30%;
    margin-top: -22%;
    color: whitesmoke;
    font-size: 3.5vh;
    line-height: 1;
    font-weight: bolder;
}
</style>
