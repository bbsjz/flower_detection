<template>
    <div style="width: 100vw;height: 10vh;background-color: rgba(3,3,41,0.5);display: block;position: static">
        <img src="../assets/logo.png" id="logo">
        <router-link to="/plants">
            <img src="../assets/infoDic_btn.png" width="80px" alt="图片" class="back_btn" v-if="route.path == '/'">
        </router-link>
        <router-link to="/">
            <img src="../assets/home_btn.png" width="80px" alt="图片" class="back_btn" v-if="route.path == '/plants'">
        </router-link>
        <!--  <img src="../assets/back_btn.png" id="back_btn">-->
    </div>
</template>

<script>
export default {
    // eslint-disable-next-line vue/multi-word-component-names
    name: 'Header'
}
</script>
<script setup>
import { useRoute } from "vue-router"

const route = useRoute()
</script>
<style scoped>
#logo {
    height: 115%;
    width: auto;
}

.back_btn {
    float: right;
    height: 92%;
    width: auto;
    margin: 15px 50px;
    cursor: pointer;
}

.back_btn:hover {
    height: 93%;
}
</style>
