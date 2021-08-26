<template>
  <el-header>
    <el-row justify="end" :gutter="20">
      <el-col :xs="6" :sm="4" :md="2">
        <el-row :gutter="20">
          <el-col :span="8">
            <el-badge :is-dot="true"><i class="el-icon-bell" /></el-badge>
          </el-col>
          <el-col :span="8">
            <el-badge :is-dot="true"><i class="el-icon-message" /></el-badge>
          </el-col>
          <el-col :span="8">
            <el-badge :is-dot="false" class="el-icon-open" />
          </el-col>
        </el-row>
      </el-col>
      <el-col :xs="6" :sm="4" :md="2" :lg="1" class="user">Admin</el-col>
      <el-col :xs="6" :sm="4" :md="2" :lg="1">
        <el-dropdown trigger="click">
          <el-avatar shape="square" size="large" src="/src/assets/logo.png"></el-avatar>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item>
                <el-button type="text" icon="el-icon-s-custom" class="dark" @click="account">账号</el-button>
              </el-dropdown-item>
              <el-dropdown-item>
                <el-button type="text" icon="el-icon-switch-button" class="dark" @click="logout">退出</el-button>
              </el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </el-col>
    </el-row>
  </el-header>
</template>

<script>
import { defineComponent, ref } from "vue";

export default defineComponent({
  methods: {
    account() {
      this.$router.push("/account");
    },
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
  text-align: center;
  color: #606266;
  background-color: #c0c4cc;
}
.el-avatar,
.el-badge {
  cursor: pointer;
}
.el-avatar {
  margin-top: 5px;
}
.el-badge {
  margin-top: 15px;
  font-size: 20px;
}
.user {
  line-height: 50px;
  overflow: hidden;
}
</style>