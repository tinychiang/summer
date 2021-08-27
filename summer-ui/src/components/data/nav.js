export default [
  {
    id: 1,
    icon: 'el-icon-data-analysis',
    name: '综合概览',
    path: '/dashboard',
  },
  {
    id: 2,
    icon: 'el-icon-user',
    name: '系统用户',
    path: '/system_user',
    children: [
      {
        id: 3,
        icon: 'el-icon-orange',
        name: '权限管理',
        path: '/role',
      },
      {
        id: 4,
        icon: 'el-icon-ice-cream',
        name: '用户管理',
        path: '/user',
      },
    ],
  },
  {
    id: 5,
    icon: 'el-icon-menu',
    name: '导航管理',
    path: '/navigate',
  },
];
