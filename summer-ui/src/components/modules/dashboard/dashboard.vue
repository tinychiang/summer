<template>
  <el-row>
    <el-col :span="24">dashborad</el-col>
  </el-row>
</template>
<script>
import { defineComponent, ref } from "vue";
import store from "@/store";

export default defineComponent({
  setup() {
    store.commit("remove");
  },
});
</script>
