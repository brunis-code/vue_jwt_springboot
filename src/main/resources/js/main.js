import App from './App.vue';
import { router } from './router';
import store from './store';
import 'bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import VeeValidate from 'vee-validate';
import Vue from 'vue';
import vuetify from './plugins/vuetify' // path to vuetify export
import 'vuetify/dist/vuetify.min.css';
import { library } from '@fortawesome/fontawesome-svg-core';
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
import {
    faHome,
    faUser,
    faUserPlus,
    faSignInAlt,
    faSignOutAlt
} from '@fortawesome/free-solid-svg-icons';
import {Axios} from './services/axios'


library.add(faHome, faUser, faUserPlus, faSignInAlt, faSignOutAlt);

Vue.config.productionTip = false;
Vue.prototype.$http = Axios;
Vue.use(VeeValidate);

Vue.component('font-awesome-icon', FontAwesomeIcon);
// Vue.use(Vuetify)
new Vue({
    vuetify,
    router,
    store,
    render: h => h(App)
}).$mount('#app');