<template>
    <div id="info_box">
        <div id="up_box">
            <p id="name"> {{ props.plant.flower_name }}</p>
            <p id="type">{{ props.plant.flower_type }}</p>
            <div id="picBox">
                <img :src="props.plant.pics[0]" class="flower_img">
                <img :src="props.plant.pics[1]" class="flower_img">
                <img :src="props.plant.pics[2]" class="flower_img">
            </div>
        </div>
        <div id="intro">
            {{ props.plant.flower_intro }}
        </div>
    </div>
</template>

<script>
export default {
    name: "InfoCard"
}
</script>
<script setup>
import { defineProps } from 'vue';
const props = defineProps({ plant: Object });
</script>
<style scoped>
#info_box {
    background: #d7edde;
    height: 89vh;
    width: 99vw;
    border-radius: 5px;
    margin: 0px auto;
    overflow-y: scroll;
    overflow-x: hidden;
}

#up_box {
    padding: 3vh 6vw 0vh;
    width: 100vw;
}

#name {
    font-size: 8vh;
    text-align: left
}

#type {
    font-size: 3vh;
    color: darkgreen;
    text-align: left;
    margin-top: -25px;
    margin-left: 20px;
}

#picBox {
    padding-top: 24px;
    margin-left: -20px;
    text-align: left;
}

.flower_img {
    height: 25vh;
    object-fit: contain;
    position: relative;
    margin-left: 20px;
}

#intro {
    top: 5vh;
    position: relative;
    background: whitesmoke;
    padding: 6vh 4vw;
    width: 94%;
    font-size: 2.5vh;
    line-height: 1.4;
}</style>
