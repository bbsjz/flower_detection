<template>
    <div>
        <div style="height: 10vh;background-color: rgba(3,3,41,0.9);display: block;position: static">
            <img src="../assets/logo.png" id="logo">
            <router-link to="/">
                <img src="../assets/back_btn.png" id="back_btn_of_info">
            </router-link>
            <!--  <img src="../assets/back_btn.png" id="back_btn">-->
        </div>
        <div id="tips">搜索结果：</div>
        <div v-if="!failed" id="listBox">
            <div v-for="item in result_list" :key="item.id" class="singleBox">
                <div class="upBox">
                    <img :src="item.url" @click="postSearch(item.id, item.name_en, item.name_zh)" title="查看详情">
                    <p1 @click="postSearch(item.id, item.name_en, item.name_zh)">{{ item.name_en }}</p1>
                    <p2 @click="postSearch(item.id, item.name_en, item.name_zh)">中文名：{{ item.name_zh }}</p2>
                </div>
                <div class="downBox">
                    {{ item.info }}
                </div>
            </div>
        </div>
        <p v-if="failed" id="failed_tips">未查询到结果</p>
    </div>
</template>

<script>
export default {
    name: "SearchList"
}
</script>
<script setup>
import { ref } from "vue";
import { useRoute, useRouter } from "vue-router";
import { getLabelFromText } from "@/apis"

const router = useRouter();
const route = useRoute();

let key_words = "";
let result_list = ref();
let failed = ref(false)

if (route.params.from == "/") {
    key_words = route.query.query
    sessionStorage.setItem("query", key_words)
}
else {
    key_words = sessionStorage.getItem("query")
}

getLabelFromText('http://localhost:8080/search/findByName', key_words).then((value) => {
    if (value.status == 200) {
        result_list.value = value.data["labels"]
        if (result_list.value == null) {
            failed.value = true
        }
    }

})


const postSearch = (id, name_en, name_zh) => {
    let params = { name_en: name_en, name_zh: name_zh };
    router.push({ name: 'plant', query: { id: id }, state: { params } })
}


/* let Result_list = ref([
  {name_en:"rose",name_zh:"玫瑰花",url:"flowers10.jpg",info:"玫瑰是温带树种，耐寒，耐旱，对土壤要求不严，在微碱性土地能生长，在富含腐殖质、排水良好的中性或微酸性轻壤土上生长和开花最好，最喜光，在庇荫下生长不良，开花稀少，不耐积水，受涝则下部叶片黄落，萌蘖性很强，生长迅速。 玫瑰在中国古代文学里象征刺客。 玫瑰是英国的国花。 花语：爱情、爱与美、容光焕发，勇敢，高贵。 "},
  {name_en:"rose",name_zh:"玫瑰花",url:"flowers10.jpg",info:"玫瑰是温带树种，耐寒，耐旱，对土壤要求不严，在微碱性土地能生长，在富含腐殖质、排水良好的中性或微酸性轻壤土上生长和开花最好，最喜光，在庇荫下生长不良，开花稀少，不耐积水，受涝则下部叶片黄落，萌蘖性很强，生长迅速。 玫瑰在中国古代文学里象征刺客。 玫瑰是英国的国花。 花语：爱情、爱与美、容光焕发，勇敢，高贵。 "},
  {name_en:"rose",name_zh:"玫瑰花",url:"flowers10.jpg",info:"玫瑰是温带树种，耐寒，耐旱，对土壤要求不严，在微碱性土地能生长，在富含腐殖质、排水良好的中性或微酸性轻壤土上生长和开花最好，最喜光，在庇荫下生长不良，开花稀少，不耐积水，受涝则下部叶片黄落，萌蘖性很强，生长迅速。 玫瑰在中国古代文学里象征刺客。 玫瑰是英国的国花。 花语：爱情、爱与美、容光焕发，勇敢，高贵。 "},
  {name_en:"rose",name_zh:"玫瑰花",url:"flowers10.jpg",info:"玫瑰是温带树种，耐寒，耐旱，对土壤要求不严，在微碱性土地能生长，在富含腐殖质、排水良好的中性或微酸性轻壤土上生长和开花最好，最喜光，在庇荫下生长不良，开花稀少，不耐积水，受涝则下部叶片黄落，萌蘖性很强，生长迅速。 玫瑰在中国古代文学里象征刺客。 玫瑰是英国的国花。 花语：爱情、爱与美、容光焕发，勇敢，高贵。 "},
  {name_en:"rose",name_zh:"玫瑰花",url:"flowers10.jpg",info:"玫瑰是温带树种，耐寒，耐旱，对土壤要求不严，在微碱性土地能生长，在富含腐殖质、排水良好的中性或微酸性轻壤土上生长和开花最好，最喜光，在庇荫下生长不良，开花稀少，不耐积水，受涝则下部叶片黄落，萌蘖性很强，生长迅速。 玫瑰在中国古代文学里象征刺客。 玫瑰是英国的国花。 花语：爱情、爱与美、容光焕发，勇敢，高贵。 "},
]); */

</script>
<style scoped>
#logo {
    height: 115%;
    width: auto;
}

#back_btn_of_info {
    float: right;
    height: 80%;
    width: auto;
    padding-top: 18px;
    cursor: pointer;
    padding-right: 30px;
}

#tips {
    padding: 20px;
    font-size: 30px;
    color: #cccccc;
    box-shadow: 0px 0px 10px 0px grey;
}

#listBox {
    overflow-x: hidden;
    overflow-y: scroll;
    height: 74vh;
    padding: 20px 50px 50px;
}

#failed_tips {
    padding: 20px;
    font-size: 30px;
    color: #cccccc;
}

.singleBox {
    height: 23%;
    width: 100%;
}

.upBox {
    display: flex;
    height: 55%;
    width: 100%;
    position: relative;
}

.upBox img {
    height: 90%;
    aspect-ratio: 1;
    object-fit: cover;
    float: left;
    border-radius: 20%;
    cursor: pointer;
}

.upBox img:hover {
    box-shadow: 2px 2px 2px 2px grey;
}

.upBox p1 {
    float: left;
    margin-left: 50px;
    font-size: 400%;
    color: rgb(10, 10, 10);
    margin-top: 5px;
}

.upBox p1:hover,
.upBox p2:hover {
    cursor: pointer;
    color: red;
    text-decoration: underline;
}

.upBox p2 {
    float: left;
    margin-left: 20px;
    font-size: 200%;
    color: rgb(10, 10, 10);
    margin-top: 28px;
}

.downBox {
    width: 100%;
    overflow: hidden;
    text-overflow: ellipsis;
    display: -webkit-box;
    -webkit-line-clamp: 2;
    -webkit-box-orient: vertical;
    font-size: 20px;
    line-height: 1.5;
    color: gray;
}
</style>
