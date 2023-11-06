import { createApp } from 'vue'
//import VueRouter from 'vue-router'
import { createRouter, createWebHistory } from "vue-router";
import ElementPlus from 'element-plus';
import 'element-plus/theme-chalk/index.css';
import App from './App.vue'
import ArcoVue from '@arco-design/web-vue';
import '@arco-design/web-vue/dist/arco.css';
import HomeVue from "@/views/Home";
import PlantCard from "@/views/PlantCard";
import InfoView from "@/views/InfoView";
import PredictView from "@/views/PredictView";
import SearchList from "@/views/SearchList";
import LoginView from "@/views/LoginView.vue";
import registerView from "@/views/RegisterView";

const routes = [
    { name: 'login', path: '/login', component: LoginView },
    { name: 'home', path: '/', component: HomeVue ,meta:{ requireAuth: true}},
    { name: 'plants', path: '/plants', component: PlantCard },
    { name: 'plant', path: '/plant', component: InfoView },
    { name: 'predict', path: '/predict', component: PredictView },
    { name: 'key-search', path: '/key-search', component: SearchList },
    { name: 'register', path: '/register', component: registerView }
]


const router = createRouter({
    history: createWebHistory(),
    routes: routes,
    base: process.env.BASE_URL
})

router.beforeEach((to, from, next) => {
    to.params.from = from.path;
    if (to.meta.requireAuth) {
        //判断store里是否有token
        if (sessionStorage.getItem('token')) {
            next();
        } else {
            next({
                path: 'login',
                query: { redirect: to.fullPath }
            })
        }
    } else {
        next()
    }
    //const isLoggedIn = sessionStorage.getItem('token');
    // if(isLoggedIn){
    //     next();
    // }else {
    //     next('/login');
    // }
})

const app = createApp(App);

app.use(ElementPlus);

app.use(ArcoVue).use(router);
app.mount('#app');
