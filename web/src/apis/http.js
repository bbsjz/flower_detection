import service from "./request";

const http = {
    get(url, headers, params) {
        const token = sessionStorage.getItem('token'); // 从 sessionStorage 中获取 token
        const config = {
            method: 'GET',
            url: url,
            headers: {
                ...headers,
                Authorization: `Bearer ${token}`
            },
            params: params ? params : {}
        }
        return service(config);
    },
    post(url, headers, data) {
        const token = sessionStorage.getItem('token'); // 从 sessionStorage 中获取 token
        const config = {
            method: 'POST',
            url: url,
            headers: {
                ...headers,
                Authorization: `Bearer ${token}`
            },
            data: data ? data : {}
        }
        return service(config);

    },
    put(url, headers, params) {
        const token = sessionStorage.getItem('token'); // 从 sessionStorage 中获取 token
        const config = {
            method: 'PUT',
            url: url,
            headers: {
                ...headers,
                Authorization: `Bearer ${token}`
            },
            params: params ? params : {}
        }
        return service(config);
    },
    delete(url, headers, params) {
        const token = sessionStorage.getItem('token'); // 从 sessionStorage 中获取 token
        const config = {
            method: 'DELETE',
            url: url,
            headers: {
                ...headers,
                Authorization: `Bearer ${token}`
            },
            params: params ? params : {}
        }
        return service(config);

    }
}

export default http;