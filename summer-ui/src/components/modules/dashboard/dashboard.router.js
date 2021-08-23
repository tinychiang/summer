/**
 * 组件路由配置
 * name      组件名称, 全局唯一
 * path      访问路径
 * component 组件位置
 */
const route = {
  name: 'dashboard',
  path: '/dashboard',
  component: () => import('@/components/modules/dashboard/dashboard.vue'),
};
export default route;
