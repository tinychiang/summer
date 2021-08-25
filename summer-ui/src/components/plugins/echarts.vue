<template>
  <div :style="{height: height}" :id="id"></div>
</template>
<script>
import { defineComponent, onMounted, watch } from "vue";
import * as echarts from "echarts";
import { uuid } from "vue-uuid";
import store from "~@/store";

export default defineComponent({
  props: {
    height: {
      type: String,
    },
    option: {
      type: Object,
    },
  },
  setup(context) {
    // 图表div唯一标志
    const id = uuid.v4();
    onMounted(() => {
      let dom = document.getElementById(id);
      let chart = echarts.init(dom);
      chart.setOption(context.option);
      // 监听导航折叠状态
      watch(
        () => store.state.collapse,
        () => {
          setTimeout(() => chart.resize(), 400);
        }
      );
      // 监听浏览器窗口变化
      window.onresize = () => {
        chart.resize();
      };
    });
    return {
      id,
    };
  },
});
</script>
<style scoped></style>