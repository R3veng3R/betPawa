import axios from 'axios';
import store from '../store';

export default class Http {
    constructor() {
        this.instance = axios.create();
        return this.init();
    }

    init() {
        this.instance.interceptors.request.use(request => {
            store.dispatch('setLoading', true);
            return request
        }, error => {return Promise.reject(error)});

        this.instance.interceptors.response.use(response => {
            store.dispatch('setLoading', false);
            return response
        });

        return this.instance
    }
}