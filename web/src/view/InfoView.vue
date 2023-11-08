<template>
    <div style="height: 10vh;background-color: rgba(3,3,41,0.9);display: block;position: static">
        <img src="../assets/logo.png" id="logo">
        <router-link :to="from">
            <img src="../assets/back_btn.png" id="back_btn_of_info">
        </router-link>
        <!--  <img src="../assets/back_btn.png" id="back_btn">-->
    </div>
    <InfoCard v-if="load" :plant="plant" />
</template>

<script>
export default {
    name: "InfoView"
}
</script>

<script setup>
import InfoCard from '@/components/InfoCard.vue';
import { useRoute } from 'vue-router'
import { getLabel } from '@/apis';
import { ref } from "vue";

const route = useRoute();
let plant = ref(new Object());
let load = ref(false);
let from = route.params.from
// 测试用数据
/* const test_data = ref({
    flower_name: "向日葵",
    flower_type: "SunFlower",
    flower_intro: "向日葵一年生高大草本。茎直立，高1-3米，粗壮，被白色粗硬毛，不分枝或有时上部分枝。叶互生，心状卵圆形或卵圆形，顶端急尖或渐尖，有三基出脉，边缘有粗锯齿，两面被短糙毛，有长柄。头状花序极大，径约10-30厘米，单生于茎端或枝端，常下倾。总苞片多层，叶质，覆瓦状排列，卵形至卵状披针形，顶端尾状渐尖，被长硬毛或纤毛。原产北美，世界各国均有栽培，通过人工培育，在不同生境上形成许多品种，特别在头状花序的大小色泽及瘦果形态上有许多变异，并为综合利用的最好原料。[3]",
    pics: ['flowers9.jpg', 'flowers9.jpg', 'flowers9.jpg']
}); */

// 根据来时路由采取不同行为

// 从植物词典列表过来
let msg = route.query.id;
getLabel("http://localhost:8080/search/getFlowerById", msg).then((response) => {
    if (response.status === 200) {
        plant.value.flower_name = history.state.params.name_zh;
        plant.value.flower_type = history.state.params.name_en;
        plant.value.flower_intro = response.data.info;
        plant.value.pics = response.data.urls;
        load.value = true;
    }
    else {
        console.log("err", response);
    }
})



</script>

<style>
#logo {
    height: 115%;
    width: auto;
}

#back_btn_of_info {
    float: right;
    height: 82%;
    width: auto;
    padding-top: 18px;
    cursor: pointer;
    padding-right: 30px;
}
</style>
