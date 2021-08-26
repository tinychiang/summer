/**
 * 账户路由配置
 * name      组件名称, 全局唯一
 * path      访问路径
 * component 组件位置
 */
 const route = {
    name: 'account',
    path: '/account',
    component: () => import('@/components/modules/account/account.vue'),
  };
  
  export default route;
  