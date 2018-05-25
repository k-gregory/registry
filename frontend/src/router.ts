import Vue from 'vue';
import Router from 'vue-router';
import Home from './views/Home.vue';
import Admin from './admin/views/Admin.vue';


import EnforcementForm from '@/components/EnforcementForm.vue';

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
    },
    {
      path: '/add',
      name: 'add-enforcement',
      component: EnforcementForm,
    },
    {
      path: '/admin',
      name: 'admin',
      component: Admin,
    },
  ],
  mode: 'history',
});
