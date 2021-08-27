export default [
  {
    icon: 'el-icon-data-analysis',
    name: '综合概览',
    path: '/dashboard',
  },
  {
    icon: 'el-icon-user',
    name: '系统用户',
    index: '1a2b3c4d',
    children: [
      {
        icon: 'el-icon-orange',
        name: '权限管理',
        path: '/role',
      },
      {
        icon: 'el-icon-ice-cream',
        name: '用户管理',
        path: '/user',
      },
    ],
  },
  {
    icon: 'el-icon-menu',
    name: '导航管理',
    path: '/navigate',
  },
];
