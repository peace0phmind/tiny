import axios from 'axios'
import { Message } from 'iview'
const API_URL = 'api'

/* 创建 axios 实例 */
const service = axios.create({
  baseURL: API_URL, // api base_url
  timeout: 6000 // 请求超时时间
})

const err = (error) => {
  if (error.response) {
    // const { data: { detail } } = error.response
    // Message.error(detail)
  }
  return Promise.reject(error)
}

// request interceptor
service.interceptors.request.use(config => {
  return config
}, err)

// response interceptor
service.interceptors.response.use((response) => {
  return response.data
}, err)

export {
  service as axios
}
