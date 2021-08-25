<template>
  <el-container>
    <Aside />
    <el-container direction="vertical">
      <Header />
      <el-main>
        <router-view />
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import { defineComponent } from "vue";
import Aside from "@/components/layout/aside.vue";
import Header from "@/components/layout/header.vue";

export default defineComponent({
  components: {
    Aside,
    Header,
  },
});
</script>

<style scoped>
.el-container {
  height: 100%;
}
</style>