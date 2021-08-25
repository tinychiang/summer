<template>
  <el-header>
    <el-row justify="end">
      <el-col :xs="6" :sm="4" :md="2" :lg="1">
        <el-button type="text" class="black" @click="drawer=true">Admin</el-button>
      </el-col>
      <el-col :xs="6" :sm="4" :md="2" :lg="1">
        <el-dropdown trigger="click">
          <el-avatar shape="square" size="large" src="/src/assets/logo.png"></el-avatar>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item>
                <el-button type="text" icon="el-icon-user">账户</el-button>
              </el-dropdown-item>
              <el-dropdown-item>
                <el-button type="text" icon="el-icon-switch-button" @click="logout()">退出</el-button>
              </el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </el-col>
    </el-row>
  </el-header>
  <el-drawer v-model="drawer" direction="rtl" :with-header="false" destroy-on-close>
    <el-card v-for="item in hhiv" :key="item">
      <el-row :gutter="20" justify="center" align="bottom">
        <el-col :span="10" class="primary" align="center">{{item.name}}&nbsp;[{{item.code}}]</el-col>
        <el-col :span="4" class="warning" align="center">{{item.count}}</el-col>
        <el-col :span="10" class="success" align="center">
          <i :class="item.status==='up'?'el-icon-caret-top':'el-icon-caret-bottom'" />{{item.quantity}}&nbsp;
          <i :class="item.status==='up'?'el-icon-caret-top':'el-icon-caret-bottom'" />{{item.percent}}
        </el-col>
      </el-row>
    </el-card>
  </el-drawer>
</template>

<script>
import { defineComponent, ref } from "vue";

export default defineComponent({
  setup() {
    return {
      drawer: ref(false),
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
    };
  },
  methods: {
    logout() {
      // TODO
      this.$store.commit("remove");
      this.$router.push("/login");
    },
  },
});
</script>

<style scoped>
.el-header {
  height: 50px;
  line-height: 50px;
  text-align: center;
  background-color: #c0c4cc;
}
.el-avatar {
  margin-top: 5px;
}
.el-drawer__title {
  margin-bottom: 0 !important;
}
</style>