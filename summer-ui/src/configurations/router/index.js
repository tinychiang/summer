import { createRouter, createWebHistory } from 'vue-router';
import store from '@/configurations/store';

/**
 * 路由跳转配置
 */
const routes = [
  {
    path: '/',
    redirect: '/dashboard',
  },
  {
    path: '/login',
    name: 'login',
    component: () => import('@/components/login.vue'),
  },
  {
    path: '/dashboard',
    name: 'dashboard',
    component: () => import('@/components/modules/dashboard/dashboard.vue'),
  },
  {
    path: '/user',
    name: 'user',
    component: () => import('@/components/modules/user/user.vue'),
  },
];

/**
 * Router配置
 * history: 1.createWebHistory(), 2.createWebHashHistory()
 * routes: 路由跳转配置
 */
const router = createRouter({
  history: createWebHistory(),
  routes,
});

/**
 * 全局前置守卫
 */
router.beforeEach((to, from, next) => {
  let token = store.state.token;
  if ((token === null || token === '') && to.name != 'login') {
    next({ name: 'login' });
  } else {
    next();
  }
});

export default router;
