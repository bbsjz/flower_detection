<template>
    <div style="height: 10vh;background-color: rgba(3,3,41,0.9);display: block;position: static">
        <img src="../assets/logo.png" id="logo">
        <router-link to="/">
            <img src="../assets/home_btn.png" id="back_btn">
        </router-link>
        <!--  <img src="../assets/back_btn.png" id="back_btn">-->
    </div>

    <div id="predictBox">
        <div id="box_of_origin">
            <img :src="img" id="plant_origin_img">
        </div>
        <div id="text">{{ message }}</div>
        <hr id="hr" />
        <div v-for="result in results" :key="result.id" id="result_box">
            <div class="similarity">{{ result.similarity }}</div>
            <img class="result_pic" :src="result.url0" style="border-left-style: solid;border-left-color: darkgrey" />
            <img class="result_pic" :src="result.url1" />
            <div class="info_box">
                <div class="name">
                    {{ result.name_en }}
                </div>
                <div class="name">
                    {{ result.name_zh }}
                </div>
                <div class="btn_box">
                    <img class="btn" src="searchBtn.png" title="查看详情"
                        @click="getDetails(result.id, result.name_en, result.name_zh)">
                    <img class="btn" src="BingBtn.png" title="必应搜索" @click="searchMore(result.name_en)">
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: "PredictView"
}
</script>

<script setup>



import { ref } from 'vue';
import { useRouter, useRoute } from 'vue-router'

const router = useRouter();
const route = useRoute();

let img = ref()
let message = ref()
let results = ref()

if (route.params.from === "/") {
    img.value = history.state.params.img
    message.value = history.state.params.message
    results.value = history.state.params.possibility
    sessionStorage.setItem("img", img.value)
    sessionStorage.setItem("message", message.value)
    sessionStorage.setItem("possibility", JSON.stringify(results.value))
}
else {
    img.value = sessionStorage.getItem("img")
    message.value = sessionStorage.getItem("message")
    results.value = JSON.parse(sessionStorage.getItem("possibility"))
}


const getDetails = (id, name_en, name_zh) => {
    let params = { name_en: name_en, name_zh: name_zh }
    router.push({ name: 'plant', query: { id: id }, state: { params } })
}

const searchMore = (query) => {
    let url = "https://www.bing.com/search?q=" + query
    window.open(url)
}

</script>

<style>
#logo {
    height: 115%;
    width: auto;
}

#back_btn {
    float: right;
    height: 98%;
    width: auto;
    padding-top: 10px;
    cursor: pointer;
}

#back_btn:hover {
    height: 100%;
}

#predictBox {
    height: 88vh;
    width: 100vw;
    background: whitesmoke;
    overflow-y: scroll;
    overflow-x: hidden
}

#plant_origin_img {
    width: 18vw;
    aspect-ratio: 1;
    object-fit: cover;
    margin-top: 5vh;
    border-style: solid;
    border-color: darkgrey;
    padding: 10px
}

#box_of_origin {
    text-align: center;
}

#text {
    font-size: 24px;
    color: darkcyan;
    text-align: left;
    position: relative;
    left: 15vw;
    margin-top: 39px;
}

#hr {
    background-color: #ccc;
    height: 1px;
    width: 80%;
    border: none;
}

#result_box {
    display: flex;
    height: 29vh;
    position: relative;
    margin-top: 49px
}

.similarity {
    margin-top: 99px;
    margin-left: 23vw;
    width: 9vw;
    height: 79px;
    font-size: 24px;
    line-height: 79px;
    color: gray;
}

.result_pic {
    aspect-ratio: 1;
    padding: 10px;
    object-fit: cover;
    margin-left: 10px;
    transition: all 0.6s;
    cursor: pointer;
}

.result_pic:hover {
    box-shadow: 0px 0px 2px 3px grey;
}

.info_box {
    width: 9vw;
    height: 100%;
    text-align: left;
    padding: 60px 50px;
}

.name {
    font-size: 28px;
    margin-bottom: 20px;
}

.btn_box {
    height: 12%;
    margin-top: 29px;
    width: 249px;
    display: flex;
    margin-left: -45px;
}

.btn {
    height: 100%;
    aspect-ratio: 3;
    float: left;
    margin-left: 45px;
    object-fit: cover;
    cursor: pointer;
}
</style>
