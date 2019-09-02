// 当前 host
const HOST = window.location.host

// 开发
const DEV = 'DEV'

// 生产
const PROD = 'PROD'

// 当前环境
const ENV = HOST === 'localhost:8000' ? DEV : PROD

// 基础地址
const BASE_URL = ENV === DEV ? 'http://127.0.0.1:8080'
  : `http://${HOST.replace('8000', '8080')}`

// 接口地址
const API_URL = BASE_URL + '/api'

export {BASE_URL, API_URL}
