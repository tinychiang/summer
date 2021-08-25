/**
 * 系统设置路由配置
 * name      组件名称, 全局唯一
 * path      访问路径
 * component 组件位置
 */
 const route = {
    name: 'system',
    path: '/system',
    component: () => import('@/components/modules/system/system.vue'),
  };
  
  export default route;