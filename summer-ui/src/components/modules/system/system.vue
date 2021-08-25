<template>
  <el-breadcrumb separator="/">
    <el-breadcrumb-item class="el-icon-setting">&nbsp;系统设置</el-breadcrumb-item>
  </el-breadcrumb>
  <!-- timeline -->
  <el-row justify="center">
    <el-col :span="18">
      <el-card>
        <el-timeline>
          <el-timeline-item v-for="item in timeline" :key="item" :timestamp="item.time" placement="top">
            <el-card>
              <p>{{item.title}}</p>
            </el-card>
          </el-timeline-item>
        </el-timeline>
      </el-card>
    </el-col>
  </el-row>
</template>

<script>
import { defineComponent } from "@vue/runtime-core";

export default defineComponent({
  setup() {
    return {
      timeline: [
        {
          title: "流程A完结",
          time: "2021/08/05 10:27",
        },
        {
          title: "流程A转移",
          time: "2021/08/03 13:38",
        },
        {
          title: "流程A开启",
          time: "2021/08/01 08:00",
        },
      ],
    };
  },
});
</script>

<style scoped></style>