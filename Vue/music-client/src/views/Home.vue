<template>
  <!--热门商户-->
  <play-list class="play-list-container" title="热门商户" path="song-sheet-detail" :playList="hotBusinessList"></play-list>
  <!--热门用户-->
  <play-list class="play-list-container" title="热门用户iii" path="singer-detail" :playList="hotUserList"></play-list>
</template>

<script lang="ts" setup>
import { ref, onMounted } from "vue";

import PlayList from "@/components/PlayList.vue";
import {  NavName } from "@/enums";
import { HttpManager } from "@/api";
import mixin from "@/mixins/mixin";

const { changeIndex } = mixin();

const hotBusinessList = ref([]);   // 商户列表
const hotUserList = ref([]);      //  用户列表

try {

  HttpManager.getHotBusinessList().then((res) => {
    hotBusinessList.value = (res as ResponseBody).data.slice(0, 10);
  });

  HttpManager.getHotUserList().then((res) => {
    hotUserList.value = (res as ResponseBody).data.slice(0, 10);
  });

  onMounted(() => {
    changeIndex(NavName.Home);
  });
} catch (error) {
  console.error(error);
}


</script>

<style lang="scss" scoped>
@import "@/assets/css/var.scss";



.swiper-container:deep(.el-carousel__indicators.el-carousel__indicators--outside) {
  display: inline-block;
  transform: translateX(30vw);
}

.el-slider__runway {
  background-color: $color-blue;
}
</style>
