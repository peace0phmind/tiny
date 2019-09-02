import axios from 'axios'
import { API_URL } from '../../config/proton'
import { Message } from 'iview';

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
