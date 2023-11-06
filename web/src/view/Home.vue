<template >
    <div style="overflow-x: hidden">
        <div id="home">
            <Header />
            <MainPage @postSearchRequest="searchByPic" @postKeyWord="searchByKeyWords" @postOrder="search_in_carousel"
                :images="plants" v-show="displayType == 1" />
            <LoadView v-show="displayType == 0"></LoadView>
        </div>
        <div id="intro">
            <p id="title">
                简 介
            </p>
            <p class="details">
                植物宝典是武汉大学学生自主研发的专业花朵识别信息数据库，收录了102种常见现生生物物种。此网站包括植物的权威名称、分类地位、形态特征、分布、功用、理论知识等内容，每一种植物均配有多张彩色照片。
            </p>
            <p class="details">
                本网站支持以图搜图和文字搜索功能。我们使用先进的机器学习（Mechine Learning）方法训练我们的深度卷积神经网络。根据测试，对于超过80%的情况，我们都可以给出正确的结果。
            </p>
            <p class="details">
                植物宝典可供植物学、农林、医药、海洋、生态、检验检疫等科研人员、教师和学生查询和学习，也可供普通大众全面学习中国植物科学知识使用。
            </p>
        </div>
    </div>
</template>

<script>
export default {
    name: 'HomeVue',
}
</script>

<script setup>
import Header from "@/components/Header";
import MainPage from '@/components/MainPage.vue';
import LoadView from "@/components/LoadView.vue";
import { postImage } from "@/apis";
import { useRouter } from 'vue-router';
import { ref } from "vue";
import { Notification } from "@arco-design/web-vue";

const router = useRouter();
const displayType = ref(1)
const plants = ref([
    { order: 1, url: 'flowers1.jpg' },
    { order: 2, url: 'flowers2.jpg' },
    { order: 3, url: 'flowers3.jpg' },
    { order: 4, url: 'flowers4.jpg' },
    { order: 5, url: 'flowers5.jpg' },
    { order: 6, url: 'flowers6.jpg' }])
// 上传图片，预测返回多个结果
const searchByPic = (picFile) => {
    let reader = new FileReader();
    reader.readAsDataURL(picFile)
    reader.onloadend = () => {
        displayType.value = 0;
        postImage('api/image/search', picFile).then((value) => {
            if (value.status == 200) {
                let params = { message: value.data["message"], possibility: value.data["possibility"], img: reader.result }
                router.push({ name: 'predict', state: { params } })
            } else {
                displayType.value = 1;
                handleNotification();
            }
        })
    }
}
//根据关键词搜索唯一结果
const searchByKeyWords = (key_words) => {
    // 根据子组件传回植物名字搜索
    displayType.value = 0;
    router.push({ name: "key-search", query: { query: key_words } })

}

const search_in_carousel = (msg) => {
    // 根据序号读取轮播图数据数组中的信息并转给详细信息网页
    router.push({ name: 'plant' })
}

const handleNotification = () => {
    Notification.warning({
        title: '错误提示',
        content: '未识别到结果，请确认图片格式再次输入!',
        closable: true,
        duration: 6000,
        style: { width: '500px' }
    })
}
</script>

<style>
#home {
    width: 100vw;
    height: 100vh;
    overflow: hidden;
    background-image: url("../assets/bg1.jpg");
    background-repeat: no-repeat;
    background-size: cover;
}

#intro {
    width: 100vw;
    height: 38vh;
    background: url("../assets/introBg.png");
}

#title {
    font-size: 60px;
    margin-left: 60px;
    margin-bottom: -20px;
    font-weight: 600;
    color: cadetblue;
}

.details {
    font-size: 25px;
    line-height: 1.5;
    letter-spacing: 2px;
    padding: 30px;
    color: darkcyan;
    margin-bottom: -60px;
}
</style>
