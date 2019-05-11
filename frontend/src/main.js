import Vue from 'vue';
import store from './store';
import App from './App';

import './scss/style.scss'

Vue.config.productionTip = false;

new Vue({
    store,
    el: '#app',
    name: 'root',
    components: {App},
    template: '<App/>'
});
