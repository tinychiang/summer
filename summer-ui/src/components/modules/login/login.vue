<template>
  <div class="backgroud">
    <el-row class="box">
      <el-col :span="24">
        <el-input v-model="username" prefix-icon="el-icon-user" placeholder="请输入账号..." />
      </el-col>
      <el-col :span="24" class="margin-top">
        <el-input v-model="password" prefix-icon="el-icon-lock" placeholder="请输入密码..." show-password />
      </el-col>
      <el-col :span="24" class="margin-top">
        <el-button class="button" type="primary" @click="login">登&emsp;录</el-button>
      </el-col>
    </el-row>
  </div>
</template>
<script>
import { defineComponent, ref } from "vue";

export default defineComponent({
  setup() {
    return {
      username: ref(""),
      password: ref(""),
    };
  },
  methods: {
    login: function () {
      console.log(this.username, this.password);
      this.$store.commit("change", "123456");
      this.$router.push({ name: "dashboard" });
    },
  },
});
</script>

<style scoped>
.backgroud {
  width: 100%;
  height: 100%;
  position: absolute;
  background: url("@/assets/summer.jpg") no-repeat center/100%;
}
.box {
  width: 35%;
  padding: 35px;
  margin: 17% auto;
  border-radius: 7px;
  background: rgba(255, 255, 255, 0.5);
}
.margin-top {
  margin-top: 10px;
}
.button {
  width: 100%;
}
</style>
