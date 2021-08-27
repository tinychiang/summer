<template>
  <template v-for="nav in navigations" :key="nav">
    <el-menu-item v-if="!nav.children" :index="nav.path">
      <i :class="nav.icon" />
      <template #title>{{nav.name}}</template>
    </el-menu-item>
    <el-submenu v-else :index="nav.index">
      <template #title>
        <i :class="nav.icon" />
        <span>{{nav.name}}</span>
      </template>
      <Menu :navigations="nav.children" />
    </el-submenu>
  </template>
</template>

<script>
import { defineComponent } from "@vue/runtime-core";

export default defineComponent({
  name: "Menu",
  props: {
    navigations: {
      type: Array,
      required: true,
    },
  },
});
</script>

<style>
</style>