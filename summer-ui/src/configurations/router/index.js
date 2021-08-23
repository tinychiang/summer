import { createRouter, createWebHistory } from 'vue-router';
import store from '~@/store';

/**
 * 路由文件加载
 */
const files = import.meta.globEager('/src/components/modules/**/*.router.js');
/**
 * 路由配置
 */
const routes = Object.keys(files).map((item) => files[item].default);

/**
 * 路由配置
 * history: 1.createWebHistory(), 2.createWebHashHistory()
 * routes: 路由跳转配置
 */
const router = createRouter({
  history: createWebHistory(),
  routes,
});

/**
 * 路由前置守卫
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
