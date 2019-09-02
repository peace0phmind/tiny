import axios from 'axios'
import {Message} from 'iview'

/**
 * Restful Http encapsulation
 */
export default class Rest {

    constructor(resource = '', baseUrl = 'api', timeout = 6000, headers = {}) {
        /**
         * Resource that you want to request
         * @type {string}
         */
        this.resource = resource

        /**
         * HTTP Request Headers
         * @type {Object}
         */
        this.headers = headers

        /* 创建 axios 实例 */
        const service = axios.create({
            baseURL: baseUrl, // api base_url
            timeout // 请求超时时间
        })

        const err = (error) => {
            if (error.response) {
                // const { data: { detail } } = error.response
                // Message.error(detail)
            }
            return Promise.reject(error)
        }

        service.interceptors.request.use(config => {
            return config
        }, err)


        service.interceptors.response.use((response) => {
            return response.data
        }, err)

        this.axios = service

        /**
         * Request method of support
         * @type {string[]}
         */
        const methods = ['GET', 'POST', 'PATCH', 'PUT', 'DELETE']

        methods.forEach((method) => {
            this[method] = options => this._request(method, options)
        })
    }

    /**
     * define these just for code hints, method implementation will be init when class create
     */
    GET() {
    }

    POST() {
    }

    PATCH() {
    }

    PUT() {
    }

    DELETE() {
    }

    /**
     * request
     *
     * @param {string} method='GET' Request method
     * @param {Object} [options={}] some options
     * @param {string} [options.uri='']
     * @param {Object} [options.params=null] GET params
     * @param {Object} [options.data=null] POST/PUT/PATCH data
     * @return {Object}
     */
    _request(method = 'GET', options = {}) {
        let id_token = localStorage.getItem('id_token')
        if (id_token) {
            this.addHeaders({Authorization: `Bearer ${id_token}`})
        }
        const {uri = '', params = null, data = null} = options
        let url = `/${this.resource}`

        if (uri) {
            url = `${url}/${uri}`
        }

        if (params) {
            url = url + this._objToUrlParam(params)
        }

        return this.axios({
            headers: this.headers,
            method,
            url,
            data
        })
    }

    /**
     * Object to UrlParam
     * @param {Object} obj srcObject
     * @return {string}
     */
    _objToUrlParam(obj) {
        if (!obj || !Object.keys(obj).length) {
            return ''
        }
        return '?' + Object.keys(obj).map((key) => {
            return `${key}=${encodeURIComponent(obj[key])}`
        }).join('&')
    }

    /**
     * add Headers
     * @param {Object} headers Headers
     */
    addHeaders(headers) {
        this.headers = {
            ...this.headers,
            ...headers
        }
        return this
    }

}
