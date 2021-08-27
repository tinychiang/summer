<template>
  <!-- card -->
  <el-row :gutter="20" justify="center">
    <el-col :span="8" align="center" v-for="item in hhiv" :key="item">
      <el-card>
        <div class="extra-large primary blod">{{item.name}}</div>
        <p class="info">{{item.code}}</p>
        <el-row justify="center">
          <el-col :sm="8" :lg="6" class="warning blod">{{item.count}}</el-col>
          <el-col :sm="8" :lg="6">
            <span :class="item.status==='up'?'success':'danger'">
              <i :class="item.status==='up'?'el-icon-caret-top':'el-icon-caret-bottom'" />{{item.quantity}}
            </span>
          </el-col>
          <el-col :sm="8" :lg="6">
            <span :class="item.status==='up'?'success':'danger'">
              <i :class="item.status==='up'?'el-icon-caret-top':'el-icon-caret-bottom'" />{{item.percent}}
            </span>
          </el-col>
        </el-row>
      </el-card>
    </el-col>
  </el-row>
  <!-- chart -->
  <el-row :gutter="20">
    <el-col :span="16">
      <el-card>
        <template #header>
          <span class="info"><i class="el-icon-data-line" /> 深指走势</span>
        </template>
        <Echarts :height="'300px'" :option="line" />
      </el-card>
    </el-col>
    <el-col :span="8">
      <el-card>
        <template #header>
          <span class="info"><i class="el-icon-bangzhu" /> 科创板</span>
        </template>
        <Echarts :height="'300px'" :option="pie" />
      </el-card>
    </el-col>
  </el-row>
</template>

<script>
import { defineComponent, ref } from "vue";
import Echarts from "@/components/plugins/echarts/echarts.vue";
import hhiv from "@/components/data/hhiv";
import line from "@/components/plugins/echarts/echarts.markLine";
import pie from "@/components/plugins/echarts/echarts.circlePie";

export default defineComponent({
  setup() {
    return {
      hhiv,
      line,
      pie,
    };
  },
  components: {
    Echarts,
  },
});
</script>

<style scoped>
.el-row{
  margin-bottom: 20px;
}
</style>