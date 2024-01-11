<template>
  <div class="comment">
    <h2 class="comment-title">
      <span>用户的评论</span>
      <span class="comment-desc">共 {{ currentList.length }} 条评论</span>
    </h2>
<!--    <el-input class="comment-input" type="textarea" placeholder="期待您的精彩评论..." :rows="2" v-model="textarea" />-->
<!--    <el-button class="sub-btn" type="primary" @click="submitComment()">发表评论</el-button>-->
  </div>
  <ul class="popular">
    <li v-for="(item, index) in currentList" :key="index">
<!--      <el-image class="popular-img" fit="contain" :src="attachImageUrl(item.avator)" />-->
      <div class="popular-msg">
        <ul>
          <li class="name">{{item.userId}}->{{ item.businessId }}</li>
          <li class="time">{{ formatDate(item.revDate) }}</li>
          <li class="content">{{ item.revText }}</li>
        </ul>
      </div>
      <!--这特么是直接拿到了评论的id-->
      <div ref="up" class="comment-ctr" @click="setSupport(item.id, item.up, userId)">
        <div><yin-icon :icon="iconList.Support"></yin-icon> {{ item.up }}</div>
        <el-icon v-if="item.userId === userId" @click="deleteComment(item.id, index)"><delete /></el-icon>
      </div>
    </li>
  </ul>
</template>

<script lang="ts" setup>
import { defineProps, getCurrentInstance, ref, toRefs, computed, watch, reactive, onMounted } from "vue";
import { useStore } from "vuex";
import { Delete } from "@element-plus/icons-vue";

import YinIcon from "@/components/layouts/YinIcon.vue";
import mixin from "@/mixins/mixin";
import { HttpManager } from "@/api";
import {Icon, NavName} from "@/enums";
import { formatDate } from "@/utils";
import songList from "@/components/SongList.vue";

const { proxy } = getCurrentInstance();
const store = useStore();
const { checkStatus } = mixin();

const props = defineProps({
  playId: String, // 商家id
  type: Number, // 歌单 1 / 歌曲 0
  currentList:Array,
});

const { playId, type } = toRefs(props);
const textarea = ref(""); // 存放输入内容
const commentList = ref([]); // 存放评论内容
const iconList = reactive({
  Support: Icon.Support,
});

const userId = computed(() => store.getters.userId);
const songId = computed(() => store.getters.songId);

const songDetails = computed(() => store.getters.songDetails);
const businessId = ref("");


</script>

<style lang="scss" scoped>
@import "@/assets/css/var.scss";
@import "@/assets/css/global.scss";

/*评论*/
.comment {
  position: relative;
  margin-bottom: 60px;

  .comment-title {
    height: 50px;
    line-height: 50px;

    .comment-desc {
      font-size: 14px;
      font-weight: 400;
      color: $color-grey;
      margin-left: 10px;
    }
  }

  .comment-input {
    display: flex;
    margin-bottom: 20px;
  }

  .sub-btn {
    position: absolute;
    right: 0;
  }
}

/*热门评论*/
.popular {
  width: 100%;
  > li {
    border-bottom: solid 1px rgba(0, 0, 0, 0.1);
    padding: 15px 0;
    display: flex;
    .popular-img {
      width: 50px;
    }

    .popular-msg {
      padding: 0 20px;
      flex: 1;
      li {
        width: 100%;
      }
      .time {
        font-size: 0.6rem;
        color: rgba(0, 0, 0, 0.5);
      }
      .name {
        color: rgba(0, 0, 0, 0.5);
      }
      .content {
        font-size: 1rem;
      }
    }

    .comment-ctr {
      display: flex;
      align-items: center;
      width: 80px;
      font-size: 1rem;
      cursor: pointer;

      .el-icon {
        margin: 0 10px;
      }

      &:hover,
      :deep(.icon):hover {
        color: $color-grey;
      }
    }
  }
}

.icon {
  @include icon(1em);
}
</style>
