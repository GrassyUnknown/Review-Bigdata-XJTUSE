<template>
  <p>关键词:</p>  <el-input v-model="keyword" placeholder="请输入关键词"></el-input>
  <p>纬度：</p><el-input v-model="latitude" placeholder="请输入纬度"></el-input>
  <p>经度:</p><el-input v-model="longitude" placeholder="请输入经度"></el-input>
<!--  <el-input v-model="userid" placeholder="请输入userId"></el-input>-->
  <el-button type="primary" @click="handleSearch">搜索</el-button>

  <el-table :data="searchBusinessList" style="width: 100%">
    <el-table-column prop="businessId" label="ID"></el-table-column>
    <el-table-column prop="businessName" label="NAME"></el-table-column>
    <el-table-column prop="stars" label="STARS"></el-table-column>
<!--    <el-table-column prop="longitude" label="Longitude"></el-table-column>-->
    <el-table-column prop="latitude" label="distance(km)"></el-table-column>
    <el-table-column prop="reviewCount" label="reviewCount"></el-table-column>
  </el-table>
</template>

<script>
import axios from 'axios';
import PlayList from "@/components/PlayList.vue";
import {computed, ref} from "vue";
import store from "@/store";
const userId = computed(() => store.getters.userId);
export default {
  data() {
    return {
      keyword: '',
      latitude: '',
      longitude: '',
      // userid: '',
      searchBusinessList: [],
    }
  },
  methods: {
    handleSearch() {
      this.loadData();
    },
    async loadData() {
      try {
        // 构建请求URL
        const url = `http://localhost:8888/business/searchBusinessList/keyword=${this.keyword}&latitude=${this.latitude}&longitude=${this.longitude}&userid=${userId.value}`;

        // 发送异步请求
        const response = await axios.get(url);

        // 请求成功，将返回的数据存储到表格数据中
        this.searchBusinessList = response.data.data;

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