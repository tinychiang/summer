<template>
  <el-aside>
    <el-menu default-active="/dashboard" :router=true background-color="#545c64" text-color="#eeeeee" active-text-color="#ffd04b" :collapse="collapse">
      <div class="logo" align="center">
        <el-image src="/src/assets/logo.png"></el-image>
      </div>
      <el-menu-item index="/dashboard">
        <i class="el-icon-data-analysis"></i>
        <template #title>综合概览</template>
      </el-menu-item>
      <el-submenu index="user">
        <template #title>
          <i class="el-icon-user"></i>
          <span>系统用户</span>
        </template>
        <el-menu-item index="/role">
          <i class="el-icon-orange"></i>
          <template #title>权限管理</template>
        </el-menu-item>
        <el-menu-item index="/user">
          <i class="el-icon-ice-cream"></i>
          <template #title>用户管理</template>
        </el-menu-item>
      </el-submenu>
      <el-menu-item index="/system">
        <i class="el-icon-setting"></i>
        <template #title>系统设置</template>
      </el-menu-item>
      <el-button type="text" class="collapse-button" :icon="collapseIcon" @click="collapseHandle"></el-button>
    </el-menu>
  </el-aside>
</template>

<script>
import { defineComponent, ref } from "vue";
import { uuid } from "vue-uuid";

export default defineComponent({
  setup() {
    return {
      collapse: ref(true),
      collapseIcon: ref("el-icon-arrow-right"),
    };
  },
  methods: {
    collapseHandle() {
      this.collapse = !this.collapse;
      this.collapseIcon = this.collapse
        ? "el-icon-arrow-right"
        : "el-icon-arrow-left";
      this.$store.commit("collapse", this.collapse);
    },
  },
});
</script>

<style scoped>
.el-aside,
.el-menu {
  height: 100%;
}
.el-aside {
  width: auto !important;
}
.el-menu:not(.el-menu--collapse) {
  width: 215px;
}
.el-image {
  height: 100%;
}
.logo {
  height: 34px;
  padding: 8px;
  background-color: #909399;
}
.collapse-button {
  bottom: 0;
  width: 100%;
  border-radius: 0;
  position: absolute;
  background-color: #545c64;
}
</style>