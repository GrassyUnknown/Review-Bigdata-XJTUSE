<template>
  <div class="play-list-container">
    <yin-nav :styleList="singerStyle" :activeName="activeName" @click="handleChangeView"></yin-nav>
    <play-list :playList="data" path="singer-detail"></play-list>
    <el-pagination
      class="pagination"
      background
      layout="total, prev, pager, next"
      :current-page="currentPage"
      :page-size="pageSize"
      :total="allPlayList.length"
      @current-change="handleCurrentChange"
    >
    </el-pagination>
  </div>
</template>

<script lang="ts" setup>
import { ref, computed } from "vue";
import YinNav from "@/components/layouts/YinNav.vue";
import PlayList from "@/components/PlayList.vue";
import { singerStyle } from "@/enums";
import { HttpManager } from "@/api";
import {useStore} from "vuex";
import mixin from "@/mixins/mixin";

// data
const activeName = ref("全部好友");
const pageSize = ref(15); // 页数
const currentPage = ref(1); // 当前页
const allPlayList = ref([]);
// computed

// 获得当前登录用户的ID
const data = computed(() => {
  return allPlayList.value.slice((currentPage.value - 1) * pageSize.value, currentPage.value * pageSize.value);
});
    const store = useStore();
    const userId = computed(() => store.getters.userId);


// 获取所有歌手
async function getAllSinger(userId:any) {
  const result = (await HttpManager.getAllSinger(userId)) as ResponseBody;
  currentPage.value = 1;
  allPlayList.value = result.data;
}

getAllSinger(userId.value);

// 获取当前页
function handleCurrentChange(val) {
  currentPage.value = val;
}

function handleChangeView(item) {
  activeName.value = item.name;
  allPlayList.value = [];
  if (item.name === "全部好友") {
    getAllSinger(userId.value);
  } else {
    getSingerSex(userId.value);
  }
}

// 通过性别对歌手分类
async function getSingerSex(sex) {
  const result = (await HttpManager.getSingerOfSex(sex)) as ResponseBody;
  currentPage.value = 1;
  allPlayList.value = result.data;
}
</script>
