import Vue from 'vue';
import store from './store';
import App from './App';
import VueCookies from 'vue-cookies';
import VueMoment from 'vue-moment';

import './scss/style.scss'

Vue.config.productionTip = false;
Vue.use(VueCookies);
Vue.use(VueMoment);

new Vue({
    store,
    el: '#app',
    name: 'root',
    components: {App},
    template: '<App/>'
});
