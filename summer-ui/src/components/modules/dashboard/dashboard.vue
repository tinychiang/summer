<template>
  <el-container>
    <el-aside>
      <el-menu default-active="1" background-color="#545c64" text-color="#eeeeee" active-text-color="#ffd04b" :collapse="collapse">
        <el-menu-item index="1">
          <i class="el-icon-data-board"></i>
          <template #title>仪表盘</template>
        </el-menu-item>
        <!-- <el-submenu index="2">
          <template #title>
            <i class="el-icon-location"></i>
            <span>导航二</span>
          </template>
          <el-menu-item-group title="分组1">
            <el-menu-item index="2-1">2-G1-1</el-menu-item>
            <el-menu-item index="2-2">2-G1-2</el-menu-item>
          </el-menu-item-group>
          <el-menu-item-group title="分组2">
            <el-menu-item index="2-3">2-G2</el-menu-item>
          </el-menu-item-group>
          <el-submenu index="2-4">
            <template #title>2-4</template>
            <el-menu-item index="2-4-1">2-4-1</el-menu-item>
          </el-submenu>
        </el-submenu> -->
        <el-menu-item index="3">
          <i class="el-icon-setting"></i>
          <template #title>设置</template>
        </el-menu-item>
        <el-affix target=".el-container" position="bottom">
          <el-button type="text" class="collapse-button" icon="el-icon-arrow-right" @click="collapse = !collapse"></el-button>
        </el-affix>
      </el-menu>
    </el-aside>
    <el-container>
      <el-header height="30px">Header</el-header>
      <el-main>
        Main
      </el-main>
      <el-footer height="30px">Footer</el-footer>
    </el-container>
  </el-container>
</template>
<script>
import { defineComponent, ref } from "vue";

export default defineComponent({
  setup() {
    return {
      collapse: ref(true),
    };
  },
});
</script>
<style scoped>
.el-container,
.el-menu {
  height: 100%;
}
.el-aside {
  width: auto !important;
}
.el-menu:not(.el-menu--collapse) {
  width: 215px;
}
.collapse-button {
  width: 100%;
  border-radius: 0;
  background-color: #545c64;
}
</style>