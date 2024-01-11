<template>
  <el-input v-model="keyword" placeholder="请输入关键词"></el-input>
  <el-input v-model="latitude" placeholder="请输入纬度"></el-input>
  <el-input v-model="longitude" placeholder="请输入经度"></el-input>
  <el-button type="primary" @click="handleSearch">搜索</el-button>

  <el-table :data="tableData">
    <el-table-column prop="businssId" label="商户ID"></el-table-column>
    <el-table-column prop="address" label="地址"></el-table-column>
    <el-table-column prop="stars" label="星级"></el-table-column>
  </el-table>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      keyword: '',
      latitude: '',
      longitude: '',
      tableData: [],
    }
  },
  methods: {
    handleSearch() {
      this.loadData();
    },
    async loadData() {
      try {
        // 构建请求URL
        const url = `http://localhost:8888/business/searchBusinessList/keyword=${this.keyword}&latitude=${this.latitude}&longitude=${this.longitude}&userid=${this.userid}`;

        // 发送异步请求
        const response = await axios.get(url);

        // 请求成功，将返回的数据存储到表格数据中
        this.tableData = response.data;
      } catch (error) {
        // 请求失败，处理错误
        console.error('请求数据失败:', error);
      }
    }
  }
}
</script>

<style scoped lang="scss">

</style>