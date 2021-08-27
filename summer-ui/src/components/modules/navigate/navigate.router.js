/**
 * 导航管理路由配置
 * name      组件名称, 全局唯一
 * path      访问路径
 * component 组件位置
 */
 const route = {
    name: 'navigate',
    path: '/navigate',
    component: () => import('@/components/modules/navigate/navigate.vue'),
  };
  
  export default route;