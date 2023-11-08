const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
    publicPath: '/',
    transpileDependencies: true,
    devServer: {
        host: '0.0.0.0', // 默认是localhost
        port: 8080, // 前端项目编译后使用的端口号，跟webpack配置的port同理
        proxy: {
            '/api': {
                target: "http://10.242.53.185:5000",   // 实际跨域请求的API地址
                secure: false,   // https请求则使用true
                ws: true,
                changeOrigin: true,  // 跨域
                // 请求地址重写
                pathRewrite: {
                    '^/api': 'http://10.242.53.185:5000',
                }
            }
        }
    }
})
