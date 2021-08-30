<template>
  <el-affix position="bottom" :offset="20">
    <el-button type="primary" class="extra-large" icon="el-icon-search" plain circle @click="drawer=true" />
  </el-affix>
  <el-drawer v-model="drawer" direction="rtl" :with-header="false" destroy-on-close>
  </el-drawer>
</template>

<script>
import { defineComponent, ref } from "vue";

export default defineComponent({
  setup() {
    return {
      drawer: ref(false),
    };
  },
});
</script>

<style scoped>
.el-affix .el-button {
  float: right;
}
</style>