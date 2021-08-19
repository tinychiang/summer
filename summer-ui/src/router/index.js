import { createRouter, createWebHistory } from "vue-router";

const routes = [
  {
    path: "/",
    name: "index",
    component: () => import("../components/index/index.vue"),
  },
  {
    path: "/login",
    name: "login",
    component: () => import("../components/login/login.vue"),
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

const authorization = false;

router.beforeEach((to, from, next) => {
  if (to.name != "login" && !authorization) {
    next({ name: "login" });
  } else {
    next();
  }
});

export default router;
