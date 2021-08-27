<template>
  <el-aside>
    <el-menu :router="true" background-color="#545c64" text-color="#eeeeee" active-text-color="#ffd04b" :collapse="collapse">
      <div class="logo" align="center">
        <el-image src="/src/assets/logo.png"></el-image>
      </div>
      <Menu :navigations="nav" />
      <el-button type="text" class="collapse-button" :icon="collapseIcon" @click="collapseHandle"></el-button>
    </el-menu>
  </el-aside>
</template>

<script>
import { defineComponent, ref } from "vue";
import Menu from "@/components/layout/menu.vue";
import nav from "@/components/data/nav";

export default defineComponent({
  setup() {
    return {
      nav,
      collapse: ref(false),
      collapseIcon: ref("el-icon-arrow-right"),
    };
  },
  components: {
    Menu,
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