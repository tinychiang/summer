<template>
  <el-header>
    <el-row justify="end">
      <el-col :xs="6" :sm="4" :md="2" :lg="1">Admin</el-col>
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
</template>
<script>
import { defineComponent } from "vue";

export default defineComponent({
  methods: {
    logout() {
      // TODO
      this.$store.commit("remove");
      this.$router.push("/login");
    },
  },
});
</script>
<style>
.el-header {
  height: 50px;
  line-height: 50px;
  text-align: center;
  background-color: #c0c4cc;
}
.el-avatar {
  margin-top: 5px;
}
</style>