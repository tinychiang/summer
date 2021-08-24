<template>
  <el-container>
    <el-aside>
      <Aside />
    </el-aside>
    <el-container>
      <el-header>
        <Header />
      </el-header>
      <el-main>
        <el-breadcrumb separator="/">
          <el-breadcrumb-item>仪表盘</el-breadcrumb-item>
        </el-breadcrumb>
        <div style="width:100%; height:1600px"></div>
      </el-main>
      <el-footer>
        <Footer />
      </el-footer>
    </el-container>
  </el-container>
</template>
<script>
import { defineComponent } from "vue";
import Aside from "@/components/commons/aside.vue";
import Header from "@/components/commons/header.vue";
import Footer from "@/components/commons/footer.vue";

export default defineComponent({
  setup() {},
  components: {
    Aside,
    Header,
    Footer,
  },
  methods: {},
});
</script>
<style></style>