/**
 * 权限路由配置
 * name      组件名称, 全局唯一
 * path      访问路径
 * component 组件位置
 */
 const route = {
    name: 'role',
    path: '/role',
    component: () => import('@/components/modules/role/role.vue'),
  };
  
  export default route;