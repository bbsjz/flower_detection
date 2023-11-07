<template>
  <router-view></router-view>
</template>

<script>
export default {
    name: 'App',
}
</script>

<style>
#app ::-webkit-scrollbar {
/* 隐藏 Chrome、Safari 和 Opera 的滚动条 */
  display: none;
}

/* 隐藏 IE、Edge 和 Firefox 的滚动条 */
#app {
  -ms-overflow-style: none;  /* IE and Edge */
  scrollbar-width: none;  /* Firefox */
}

body {
    margin: 0;
    /* 外边距 */
    padding: 0;
    /* 内边距 */
    border: 0;
    /* 边框 */
}
</style>
