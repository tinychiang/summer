<template>
  <el-breadcrumb separator="/">
    <el-breadcrumb-item class="el-icon-data-analysis">&nbsp;综合概览</el-breadcrumb-item>
  </el-breadcrumb>
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
        <Echarts :height="height" :option="line" />
      </el-card>
    </el-col>
    <el-col :span="8">
      <el-card>
        <template #header>
          <span class="info"><i class="el-icon-bangzhu" /> 科创板</span>
        </template>
        <Echarts :height="height" :option="pie" />
      </el-card>
    </el-col>
  </el-row>
</template>

<script>
import { defineComponent, ref } from "vue";
import Echarts from "@/components/plugins/echarts.vue";

export default defineComponent({
  setup() {
    const markLine = [];
    const positions = [
      "start",
      "middle",
      "end",
      "insideStart",
      "insideStartTop",
      "insideStartBottom",
      "insideMiddle",
      "insideMiddleTop",
      "insideMiddleBottom",
      "insideEnd",
      "insideEndTop",
      "insideEndBottom",
    ];
    for (var i = 0; i < positions.length; ++i) {
      markLine.push({
        name: positions[i],
        yAxis: 1.8 - 0.2 * Math.floor(i / 3),
        label: {
          formatter: "{b}",
          position: positions[i],
        },
      });

      if (positions[i] !== "middle") {
        const name =
          positions[i] === "insideMiddle"
            ? "insideMiddle / middle"
            : positions[i];

        markLine.push([
          {
            name: "start: " + positions[i],
            coord: [0, 0.3],
            label: {
              formatter: name,
              position: positions[i],
            },
          },
          {
            name: "end: " + positions[i],
            coord: [3, 1],
          },
        ]);
      }
    }
    const line = {
      animation: false,
      textStyle: {
        fontSize: 14,
      },
      xAxis: {
        data: ["A", "B", "C", "D", "E"],
        boundaryGap: true,
        splitArea: {
          show: true,
        },
      },
      yAxis: {
        max: 2,
      },
      series: [
        {
          name: "line",
          type: "line",
          stack: "all",
          symbolSize: 6,
          data: [0.3, 1.4, 1.2, 1, 0.6],
          markLine: {
            data: markLine,
            label: {
              distance: [20, 8],
            },
          },
        },
      ],
      grid: {
        top: 20,
        left: 50,
        right: 50,
        bottom: 30,
      },
    };
    const pie = {
      tooltip: {
        trigger: "item",
      },
      series: [
        {
          name: "科创板 T5",
          type: "pie",
          radius: ["50%", "100%"],
          avoidLabelOverlap: false,
          itemStyle: {
            borderRadius: 10,
            borderColor: "#fff",
            borderWidth: 2,
          },
          label: {
            show: false,
            position: "center",
          },
          emphasis: {
            label: {
              show: true,
              fontSize: "20",
              fontWeight: "bold",
            },
          },
          labelLine: {
            show: false,
          },
          data: [
            { value: 20.01, name: "正帆科技" },
            { value: 20.01, name: "九号公司-WD" },
            { value: 20, name: "键凯科技" },
            { value: 20, name: "金博股份" },
            { value: 19.71, name: "华峰测控" },
          ],
        },
      ],
    };
    return {
      hhiv: [
        {
          name: "上证指数",
          code: "000001",
          count: 3540.38,
          status: "up",
          quantity: 25.91,
          percent: "0.74%",
        },
        {
          name: "深证指数",
          code: "399001",
          count: 14697.5,
          status: "up",
          quantity: 33.95,
          percent: "0.23%",
        },
        {
          name: "恒生指数",
          code: "HSI",
          count: 25693.95,
          status: "down",
          quantity: -33.97,
          percent: "-0.13%",
        },
      ],
      height: ref("300px"),
      line,
      pie,
    };
  },
  components: {
    Echarts,
  },
});
</script>

<style scoped></style>