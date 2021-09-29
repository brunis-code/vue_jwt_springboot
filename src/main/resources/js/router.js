import Vue from 'vue';
import Router from 'vue-router';
import Home from './views/Home.vue';
import Login from './views/Login.vue';
import Register from './views/Register.vue';
import BoardAdmin from './views/BoardAdmin.vue';
import BoardModerator from './views/BoardModerator.vue';
import BoardUser from './views/BoardUser.vue';
import Profile from './views/Profile.vue';

Vue.use(Router);

export const router = new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/home',
      component: Home
    },
    {
      path: '/login',
      component: Login
    },
    {
      path: '/register',
      component: Register
    },
    {
      path: '/profile',
      name: 'profile',
      component:Profile
    },
    {
      path: '/admin',
      name: 'admin',
      component: BoardAdmin
    },
    {
      path: '/mod',
      name: 'moderator',
      // lazy-loaded
      component:BoardModerator
    },
    {
      path: '/user',
      name: 'user',
      component:BoardUser
    }
  ]
});
