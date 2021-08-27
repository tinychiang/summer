<template>
  <el-form :inline="true" :model="formInline" class="demo-form-inline">
    <el-form-item label="名称">
      <el-input v-model="formInline.name" placeholder="名称" />
    </el-form-item>
    <el-form-item label="路径">
      <el-input v-model="formInline.path" placeholder="路径" />
    </el-form-item>
    <el-form-item>
      <el-button type="primary" icon="el-icon-search">查询</el-button>
    </el-form-item>
  </el-form>
  <el-table :data="nav" row-key="id" :tree-props="{children: 'children', hasChildren: 'hasChildren'}" border stripe>
    <el-table-column prop="name" label="名称" />
    <el-table-column prop="path" label="路径" />
    <el-table-column prop="icon" label="图标" />
    <el-table-column label="操作">
      <template #default="scope">
        <el-button size="mini" icon="el-icon-edit" type="primary">编辑</el-button>
        <el-button size="mini" icon="el-icon-delete" type="danger" @click="delete(scope.$index, scope.row)">删除</el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
import { defineComponent } from "@vue/runtime-core";
import nav from "@/components/data/nav";

export default defineComponent({
  setup() {
    return {
      formInline: {
        name: "",
        path: "",
      },
      nav,
    };
  },
  methods: {
    delete(index, row) {
      console.log(index, row);
    },
  },
});
</script>

<style scoped></style>