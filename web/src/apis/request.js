import axios from "axios";

const service = axios.create({
    withCredentials: false,     // 跨越访问是否需要凭证
    timeout: 5000               // 超时时间
})

// 请求拦截器
service.interceptors.request.use(config => {
    return config
})

// 响应拦截器
service.interceptors.response.use(response => {
    return response
}, error => {
    return error
})

export default service