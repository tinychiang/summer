<template>
  <el-container>
    <Aside />
    <el-container direction="vertical">
      <Header />
      <el-main>
        <el-breadcrumb separator="/">
          <el-breadcrumb-item class="el-icon-data-analysis">&nbsp;综合概览</el-breadcrumb-item>
        </el-breadcrumb>
        <el-row :gutter="20">
          <el-col :span="12">
            <Echarts :height="'300px'" :option="option" />
          </el-col>

          <el-col :span="12">
            <el-carousel trigger="click" height="300px">
              <el-carousel-item v-for="item in 4" :key="item">
                <h3 class="small">{{ item }}</h3>
              </el-carousel-item>
            </el-carousel>
          </el-col>
        </el-row>

        <el-divider><i class="el-icon-position"></i></el-divider>

        <el-row :gutter="20">
          <el-col :sm="6" :lg="3">
            <el-button type="success" @click="notify('成功','success')">成功</el-button>
          </el-col>
          <el-col :sm="6" :lg="3">
            <el-button plain type="warning" @click="notify('警告','warning')">警告</el-button>
          </el-col>
          <el-col :sm="6" :lg="3">
            <el-button round type="info" @click="notify('消息','info')">消息</el-button>
          </el-col>
          <el-col :sm="6" :lg="3">
            <el-button type="danger" @click="notify('错误','error')">错误</el-button>
          </el-col>
          <el-col :sm="6" :lg="3">
            <el-tooltip class="item" effect="dark" content="提示文字" placement="bottom">
              <el-button>提示</el-button>
            </el-tooltip>
          </el-col>
          <el-col :sm="6" :lg="3">
            <el-popconfirm confirmButtonText='Yes' cancelButtonText='No' icon="el-icon-info" iconColor="red" title="...？">
              <template #reference>
                <el-button type="text">气泡</el-button>
              </template>
            </el-popconfirm>
          </el-col>
          <el-col :sm="6" :lg="3">
            <el-popover placement="bottom" title="标题" :width="200" trigger="click" content="...">
              <template #reference>
                <el-button type="warning">弹出框</el-button>
              </template>
            </el-popover>
          </el-col>
          <el-col :sm="6" :lg="3">
            <el-button type="primary" icon="el-icon-share" @click="drawer = true">抽屉</el-button>
          </el-col>
          <el-drawer title="标题" v-model="drawer" direction="rtl" destroy-on-close></el-drawer>
        </el-row>

        <el-row :gutter="20">
          <el-col :sm="12" :lg="6">
            <el-result icon="success" title="成功提示" subTitle="请根据提示进行操作">
              <template #extra>
                <el-button type="primary" size="medium">返回</el-button>
              </template>
            </el-result>
          </el-col>
          <el-col :sm="12" :lg="6">
            <el-result icon="warning" title="警告提示" subTitle="请根据提示进行操作">
              <template #extra>
                <el-button type="primary" size="medium">返回</el-button>
              </template>
            </el-result>
          </el-col>
          <el-col :sm="12" :lg="6">
            <el-result icon="error" title="错误提示" subTitle="请根据提示进行操作">
              <template #extra>
                <el-button type="primary" size="medium">返回</el-button>
              </template>
            </el-result>
          </el-col>
          <el-col :sm="12" :lg="6">
            <el-result icon="info" title="信息提示" subTitle="请根据提示进行操作">
              <template #extra>
                <el-button type="primary" size="medium">返回</el-button>
              </template>
            </el-result>
          </el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="8">
            <el-card shadow="hover">悬浮A</el-card>
          </el-col>
          <el-col :span="8">
            <el-card shadow="hover">悬浮B</el-card>
          </el-col>
          <el-col :span="8">
            <el-card shadow="hover">悬浮C</el-card>
          </el-col>
        </el-row>

        <el-row :gutter="20">
          <el-col :span="12">
            <el-timeline>
              <el-timeline-item timestamp="2018/4/12" placement="top">
                <el-card>
                  <h4>事件A</h4>
                  <p>... 2021/08/24 23:59</p>
                </el-card>
              </el-timeline-item>
              <el-timeline-item timestamp="2018/4/3" placement="top">
                <el-card>
                  <h4>进行中</h4>
                  <p>... 2021/08/24 23:59</p>
                </el-card>
              </el-timeline-item>
              <el-timeline-item timestamp="2018/4/2" placement="top">
                <el-card>
                  <h4>完结</h4>
                  <p>... 2021/08/24 23:59</p>
                </el-card>
              </el-timeline-item>
            </el-timeline>
          </el-col>

          <el-col :span="12">
            <el-calendar v-model="date"></el-calendar>
          </el-col>
        </el-row>
      </el-main>
    </el-container>
  </el-container>
</template>
<script>
import { defineComponent, onMounted, ref, watch } from "vue";
import * as echarts from "echarts";
import Aside from "@/components/commons/aside.vue";
import Header from "@/components/commons/header.vue";
import Echarts from "@/components/plugins/echarts.vue";

export default defineComponent({
  setup() {
    return {
      drawer: ref(false),
      date: ref(new Date()),
      option: {
        color: ["#80FFA5", "#00DDFF", "#37A2FF", "#FF0087", "#FFBF00"],
        tooltip: {
          trigger: "axis",
          axisPointer: {
            type: "cross",
            label: {
              backgroundColor: "#6a7985",
            },
          },
        },
        legend: {
          padding: [45, 0, 0, 0],
          data: ["Line 1", "Line 2", "Line 3", "Line 4", "Line 5"],
        },
        grid: {
          top: "25%",
          right: "2%",
          left: "2%",
          bottom: "0",
          containLabel: true,
        },
        xAxis: [
          {
            type: "category",
            boundaryGap: false,
            data: ["周一", "周二", "周三", "周四", "周五", "周六", "周日"],
          },
        ],
        yAxis: [
          {
            type: "value",
          },
        ],
        series: [
          {
            name: "Line 1",
            type: "line",
            stack: "总量",
            smooth: true,
            lineStyle: {
              width: 0,
            },
            showSymbol: false,
            areaStyle: {
              opacity: 0.8,
              color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                {
                  offset: 0,
                  color: "rgba(128, 255, 165)",
                },
                {
                  offset: 1,
                  color: "rgba(1, 191, 236)",
                },
              ]),
            },
            emphasis: {
              focus: "series",
            },
            data: [140, 232, 101, 264, 90, 340, 250],
          },
          {
            name: "Line 2",
            type: "line",
            stack: "总量",
            smooth: true,
            lineStyle: {
              width: 0,
            },
            showSymbol: false,
            areaStyle: {
              opacity: 0.8,
              color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                {
                  offset: 0,
                  color: "rgba(0, 221, 255)",
                },
                {
                  offset: 1,
                  color: "rgba(77, 119, 255)",
                },
              ]),
            },
            emphasis: {
              focus: "series",
            },
            data: [120, 282, 111, 234, 220, 340, 310],
          },
          {
            name: "Line 3",
            type: "line",
            stack: "总量",
            smooth: true,
            lineStyle: {
              width: 0,
            },
            showSymbol: false,
            areaStyle: {
              opacity: 0.8,
              color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                {
                  offset: 0,
                  color: "rgba(55, 162, 255)",
                },
                {
                  offset: 1,
                  color: "rgba(116, 21, 219)",
                },
              ]),
            },
            emphasis: {
              focus: "series",
            },
            data: [320, 132, 201, 334, 190, 130, 220],
          },
          {
            name: "Line 4",
            type: "line",
            stack: "总量",
            smooth: true,
            lineStyle: {
              width: 0,
            },
            showSymbol: false,
            areaStyle: {
              opacity: 0.8,
              color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                {
                  offset: 0,
                  color: "rgba(255, 0, 135)",
                },
                {
                  offset: 1,
                  color: "rgba(135, 0, 157)",
                },
              ]),
            },
            emphasis: {
              focus: "series",
            },
            data: [220, 402, 231, 134, 190, 230, 120],
          },
          {
            name: "Line 5",
            type: "line",
            stack: "总量",
            smooth: true,
            lineStyle: {
              width: 0,
            },
            showSymbol: false,
            label: {
              show: true,
              position: "top",
            },
            areaStyle: {
              opacity: 0.8,
              color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                {
                  offset: 0,
                  color: "rgba(255, 191, 0)",
                },
                {
                  offset: 1,
                  color: "rgba(224, 62, 76)",
                },
              ]),
            },
            emphasis: {
              focus: "series",
            },
            data: [220, 302, 181, 234, 210, 290, 150],
          },
        ],
      },
    };
  },
  components: {
    Aside,
    Header,
    Echarts,
  },
  methods: {
    notify(name, type) {
      this.$notify({
        title: name,
        message: "这是一条" + name + "的提示消息",
        type: type,
      });
    },
  },
});
</script>
<style scoped>
.el-main button {
  width: 100%;
}
.el-carousel,
.el-timeline {
  margin-top: 20px;
}
.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 300px;
  margin: 0;
  text-align: center;
}
.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}
.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}
</style>