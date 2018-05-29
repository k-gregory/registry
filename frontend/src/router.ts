import Vue from 'vue';
import Router from 'vue-router';
import Home from './views/Home.vue';
import Admin from './admin/views/Admin.vue';
import Register from './register/views/Register.vue';

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
    },
    {
      path: '/admin',
      name: 'admin',
      component: Admin,
    },
    {
      path: '/register',
      name: 'register',
      component: Register,
    },
  ],
  mode: 'history',
});
