<template>
  <el-aside>
    <el-menu default-active="1" background-color="#545c64" text-color="#eeeeee" active-text-color="#ffd04b" :collapse="collapse">
      <div class="logo" align="center">
        <el-image src="/src/assets/logo.png"></el-image>
      </div>
      <el-menu-item index="1">
        <i class="el-icon-data-analysis"></i>
        <template #title>综合概览</template>
      </el-menu-item>
      <el-submenu index="2">
        <template #title>
          <i class="el-icon-user"></i>
          <span>用户管理</span>
        </template>
        <el-submenu index="2-1">
          <template #title>部门</template>
          <el-menu-item index="2-1-1">销售部</el-menu-item>
          <el-menu-item index="2-1-2">研发部</el-menu-item>
        </el-submenu>
      </el-submenu>
      <el-menu-item index="3">
        <i class="el-icon-setting"></i>
        <template #title>系统设置</template>
      </el-menu-item>
      <el-button type="text" class="collapse-button" :icon="collapseIcon" @click="collapseHandle"></el-button>
    </el-menu>
  </el-aside>
</template>
<script>
import { defineComponent, ref } from "vue";

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
<style>
.el-container,
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
.logo {
  height: 35px;
  margin-top: 10px;
  margin-bottom: 5px;
  overflow: hidden;
}
.el-image {
  height: 100%;
}
.collapse-button {
  bottom: 0;
  width: 100%;
  border-radius: 0;
  position: absolute;
  background-color: #545c64;
}
</style>