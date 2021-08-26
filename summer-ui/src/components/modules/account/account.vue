<template>
  <el-breadcrumb separator="/">
    <el-breadcrumb-item class="el-icon-s-custom">&nbsp;账号</el-breadcrumb-item>
  </el-breadcrumb>
  <el-row justify="center">
    <el-col :span="20">
      <el-tabs tab-position="left">
        <el-tab-pane label="基础信息">
          <el-descriptions :column="1" border>
            <el-descriptions-item>
              <template #label><i class="el-icon-user" />&nbsp;姓名</template>
              <el-input v-model="baseInfo.realName" clearable />
            </el-descriptions-item>
            <el-descriptions-item>
              <template #label><i class="el-icon-mobile-phone" />&nbsp;联系方式</template>
              <el-input v-model="baseInfo.phone" clearable />
            </el-descriptions-item>
            <el-descriptions-item>
              <template #label><i class="el-icon-location-outline" />&nbsp;居住地</template>
              <el-input v-model="baseInfo.residence" clearable />
            </el-descriptions-item>
            <el-descriptions-item>
              <template #label> <i class="el-icon-office-building" />&nbsp;联系地址</template>
              <el-input v-model="baseInfo.address" clearable />
            </el-descriptions-item>
            <el-descriptions-item>
              <template #label> <i class="el-icon-tickets" />&nbsp;备注</template>
              <el-input v-model="baseInfo.memo" clearable />
            </el-descriptions-item>
          </el-descriptions>
          <el-button type="primary" size="small">保存</el-button>
          <el-button type="danger" size="small">重置</el-button>
        </el-tab-pane>
        <el-tab-pane label="账户信息" align="center">
          <el-avatar :size="150" src="/src/assets/logo.png" />
          <el-form ref="form" :model="user" label-width="80px">
            <el-form-item label="用户名">
              <el-input placeholder="请输入用户名" v-model="user.username" clearable />
            </el-form-item>
            <el-form-item label="密码">
              <el-input placeholder="请输入密码" type="password" v-model="user.password" clearable />
            </el-form-item>
            <el-form-item label="确认密码">
              <el-input placeholder="请再次输入密码" type="password" v-model="user.check" clearable />
            </el-form-item>
            <el-form-item align="left">
              <el-button type="primary" size="small">保存</el-button>
              <el-button type="danger" size="small">重置</el-button>
            </el-form-item>
          </el-form>
        </el-tab-pane>
      </el-tabs>
    </el-col>
  </el-row>
</template>

<script>
import { defineComponent } from "@vue/runtime-core";

export default defineComponent({
  data() {
    return {
      dialogImageUrl: "",
      dialogVisible: false,
      baseInfo: {
        realName: "Michael Jordon",
        phone: "18898765432",
        residence: "沈阳市",
        address: "辽宁省沈阳市创新路28号",
        memo: "",
      },
      user: {
        username: "",
        password: "",
        check: "",
      },
    };
  },
  methods: {
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },
  },
});
</script>

<style scoped>
.el-tabs button,
.el-form {
  margin-top: 10px;
}
</style>