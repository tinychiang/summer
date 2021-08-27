<template>
  <el-container>
    <Aside />
    <el-container direction="vertical">
      <Header />
      <el-main>
        <router-view />
        <Affix />
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import { defineComponent } from "vue";
import Aside from "@/components/layout/aside.vue";
import Header from "@/components/layout/header.vue";
import Affix from "@/components/layout/affix.vue";

export default defineComponent({
  components: {
    Aside,
    Header,
    Affix,
  },
});
</script>

<style>
.el-container {
  height: 100%;
}
.el-breadcrumb {
  margin-bottom: 20px;
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
.dark {
  color: #606266;
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