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

<style>
.el-container {
  height: 100%;
}
.el-breadcrumb {
  margin-bottom: 10px;
}
.el-card {
  margin: 10px;
}
.primary {
  color: #409eff;
}
.warning {
  color: #e6a23c;
}
.success {
  color: #67c23a;
}
.danger {
  color: #f56c6c;
}
.info {
  color: #909399;
}
.black {
  color: #303133;
}
.blod {
  font-weight: bold;
}
.extra-large {
  font-size: 20px;
}
</style>