<template>
    <div id="plantsCard">
        <Header />
        <Cards :plants="plants" @postSearchRequest="look_up_a_plant" />
    </div>
</template>

<script>
export default {
    name: "PlantCard"
}
</script>
<script setup>
import Header from "@/components/Header";
import Cards from "@/components/Cards";
import { getLabels } from "@/apis";

import { ref } from "vue";

import { useRouter } from 'vue-router'
let plants = ref()

const router = useRouter()
getLabels("search/getAll").then((response) => {
    if (response.status === 200) {
        for(var i=0;i<response.data.length;i++)
        {
            response.data[i].url="http://localhost:8080/"+response.data[i].url.substring(8)
        }
        plants.value = response.data
    }
    else {
        console.log("err", response)
    }
})

const look_up_a_plant = (msg) => {
    let index = msg - 1;
    let params = { name_zh: plants.value[index].name_zh, name_en: plants.value[index].name_en }
    router.push({ name: 'plant', query: { id: msg }, state: { params } })
}


</script>
<style scoped>
#plantsCard {
    width: 100vw;
    height: 100vh;
    overflow: hidden;
    background-image: url("../assets/bg4.jpg");
    /*linear-gradient(*/
    /*rgba(0, 0, 0, 0.9),*/
    /*rgba(0, 0, 0, 0.9) 60%,*/
    /*rgba(34, 34, 34, 0.1)*/
    /*),*/
    background-repeat: no-repeat;
    background-size: 100%;
}
</style>
