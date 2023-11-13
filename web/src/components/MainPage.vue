<template>
    <div>
        <img src="../assets/logoMain.png" id="logo_main">
        <div id="search_box">
            <a-input-search style="margin-left: 28vw;float: left;margin-right: 25px;" :style="{ width: '700px' }"
                size="large" placeholder="搜索图片" search-button v-on:search="search_by_keywords" v-model="key_words" />
            <div class="upload-container">
                <img src="../assets/camera.png" style="float: left;height: 65px;" title="请上传jpg或png格式的图片">
                <label for="pic" title="请上传jpg或png格式的图片">上传图片</label>
                <input ref="imgInput" type="file" name="pic" id="pic" v-on:input="showImg" accept="image/png, image/jpg"
                    style="display: none">
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'MainPage',
}
</script>

<script setup>
import { ref, defineEmits } from 'vue';
import { Notification } from '@arco-design/web-vue';
const emits = defineEmits(['postSearchRequest', 'postKeyWord', 'postOrder']);

// 轮播图url数组
// const props = defineProps({images:Array})

// 存储待搜索关键词的变量
const key_words = ref('')
// 存储搜索结果
// const plant_result = ref({ flower_name: '大王花对对对', flower_type: '霸王拿书', flower_intro: '1111', pics: ['flowers1.jpg', 'flowers1.jpg', 'flowers1.jpg'] })

const imgInput = ref()

//关键词，识别结果
const search_by_keywords = () => {
    console.log(key_words.value)
    let regu = "^[ ]+$";
    let re = new RegExp(regu);

    if (key_words.value != '' && !re.test(key_words.value)) {
        emits('postKeyWord', key_words.value)
    } else {
        handleNotification();
    }

}
// 传输n图片用于搜索
const showImg = () => {
    let file = imgInput.value.files[0];
    if (file != undefined) {
        emits('postSearchRequest', file);
    }
    imgInput.value.value = null;

};

const handleNotification = () => {
    Notification.warning({
        title: '错误提示',
        content: '请输入非空内容',
        closable: true,
        duration: 4000,
        style: { width: '500px' }
    })
}
</script>

<style scoped>
#logo_main {
    width: 30vw;
    height: 20vh;
    margin: 15vh 15vh 0;
}

#search_box {
    margin-left: -19vw;
}

.upload-container {
    text-align: center;
    float: left;
    background: rgba(7, 54, 36, 0.9);
    border-radius: 6px;
    padding: 0 10px;
}

.upload-container label {
    padding: 12px;
    color: white;
    cursor: pointer;
    font-size: 3.8vh;
    float: left;
}
</style>
<style>
.arco-input-wrapper .arco-input.arco-input-size-large {
    padding-top: 9px !important;
    padding-bottom: 9px !important;
    font-size: 28px !important;
}

.arco-btn-size-large.arco-btn-only-icon {
    width: 60px !important;
    height: 60px !important;
}

.arco-btn-primary,
.arco-btn-primary[type='button'],
.arco-btn-primary[type='submit'] {
    background-color: green !important;
    margin-right: 1.5px;
}

/*.arco-carousel-card .arco-carousel-item-prev {*/
/*    opacity: 1 !important;*/
/*}*/

/*.arco-carousel-card .arco-carousel-item-next {*/
/*    opacity: 1 !important;*/
/*}*/
</style>
