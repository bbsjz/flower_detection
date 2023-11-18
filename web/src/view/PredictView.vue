<template>
    <div style="height: 10vh;background-color: rgba(3,3,41,0.9);display: block;position: static">
        <img src="../assets/logo.png" id="logo">
        <router-link to="/">
            <img src="../assets/home_btn.png" id="back_btn">
        </router-link>
        <!--  <img src="../assets/back_btn.png" id="back_btn">-->
    </div>
    <div id="pre-btn-container" style="display: block;position: static">
      <el-button id="predict-btn" type="primary"  @click="drawer = true">
        chat >
      </el-button>
    </div>

  <el-drawer v-model="drawer" title="I am the title" :with-header="false">
<!--      <span>Hi there!</span>
    <div class="bubbles-container">
      &lt;!&ndash; 气泡会动态生成在这里 &ndash;&gt;
    </div>
    <div class="send-content">
      <textarea class="text-input" rows="6" placeholder="请输入文本" v-model="text"></textarea>
      <button class="send-button" @click="addDiv">发送</button>
    </div>-->
<!--    <div class="chat-box">
      <ChatPage :image_base64="image_base64" />
    </div>-->
    <ChatPage :image_base64="image_base64" />
    </el-drawer>
    <div id="predictBox">
        <div id="box_of_origin">
            <img :src="img" id="plant_origin_img">
        </div>
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

</script>

<script setup>

//import axios from "axios";
//const text = ref('')

import { ref } from 'vue';
import { useRouter, useRoute } from 'vue-router'
import ChatPage from "@/components/ChatPage.vue";


const drawer = ref(false)
const image_base64=ref()



const router = useRouter();
const route = useRoute();



// let img = ref()
let img = ref()
let results = ref()
//const image_base64 = ref();

if (route.params.from === "/") {
    img.value = history.state.params.img
    console.log(img.value)
    image_base64.value = history.state.params.img
    //img.value = require('@/assets/bg1.jpg')
    //image_base64.value = require('@/assets/bg1.jpg')//要改
  results.value = history.state.params.possibility
    sessionStorage.setItem("img", img.value)
    sessionStorage.setItem("possibility", JSON.stringify(results.value))
}
else {
    img.value = sessionStorage.getItem("img")
    image_base64.value = sessionStorage.getItem("img")
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
//发送消息
/*function addDiv() {
    const div = document.createElement('div');
    // 设置 div 的样式、内容等
    div.className = 'bubble';
    div.textContent = text.value;

    const container = document.querySelector('.bubbles-container');
    container.appendChild(div);

    //向后端发数据
  axios({
    url: "http://localhost:8080/authentication/login",               // 请求地址
    method: "post",                       // 请求方法
    headers: {                            // 请求头
      "Content-Type": "application/json",
    },
    data: ({                             // 请求参数
      text:text.value
    }),
  }).then((res) => {
    //等待返回消息，创建div，加到气泡里
    const div2 = document.createElement('div');
    // 设置 div 的样式、内容等
    div2.className = 'left-bubble';
    div2.textContent = res.value;
    container.appendChild(div2);
  });
}*/

</script>

<style>
#pre-btn-container{
  margin-top: 2vh;
  height: 5vh;
  width: 100%;
  background-color: transparent;
}
#predict-btn{
  height: 5vh;
  width: 8vh;
  margin-right: 1vh;
  float: right;
  background-color: transparent;
  border-color: transparent;
  color: #383838;
  font-size: 20px;
}


.bubbles-container {
  display: flex;
  flex-direction: column;
  margin-top: 10px;
}

.bubble {
  align-self: flex-end;
  background-color: #e0e0e0;
  padding: 10px;
  margin-bottom: 5px;
  border-radius: 5px;
}

.left-bubble {
  align-self: flex-start;
  background-color: #e0e0e0;
  padding: 10px;
  margin-bottom: 5px;
  border-radius: 5px;
}

.send-content{
  position: absolute;
  bottom: 0;
  display: flex;
  flex-direction: row;
  align-items: center;
  padding: 10px;
  width: 100%;
}

.text-input {
  flex: 8;
  margin-right: 10px;
}

.send-button {
  flex: 2;
}

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
