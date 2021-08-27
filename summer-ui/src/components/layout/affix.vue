<template>
  <el-affix position="bottom" :offset="20">
    <el-button type="primary" class="extra-large" icon="el-icon-search" plain circle @click="drawer=true" />
  </el-affix>
  <el-drawer v-model="drawer" direction="rtl" :with-header="false" destroy-on-close>
    <el-card v-for="item in hhivs" :key="item">
      <el-row :gutter="20" justify="center" align="bottom">
        <el-col :span="10" :class="item.status==='up'?'success':'danger'">{{item.name}}&nbsp;[{{item.code}}]</el-col>
        <el-col :span="4" class="dark">{{item.count}}</el-col>
        <el-col :span="10" class="primary">
          <i :class="item.status==='up'?'el-icon-caret-top':'el-icon-caret-bottom'" />{{item.quantity}}&nbsp;
          <i :class="item.status==='up'?'el-icon-caret-top':'el-icon-caret-bottom'" />{{item.percent}}
        </el-col>
      </el-row>
    </el-card>
  </el-drawer>
</template>

<script>
import { defineComponent, ref } from "vue";
import hhivs from "@/components/data/hhivs";

export default defineComponent({
  setup() {
    return {
      drawer: ref(false),
      hhivs,
    };
  },
});
</script>

<style scoped>
.el-affix .el-button {
  float: right;
}
</style>