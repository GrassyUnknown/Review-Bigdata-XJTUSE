<template>
<!--  <div class="content">-->
<!--    <el-table highlight-current-row :data="dataList" @row-click="handleClick">-->
<!--      <el-table-column prop="songName" label="歌曲" />-->
<!--      <el-table-column prop="singerName" label="歌手" />-->
<!--      <el-table-column prop="introduction" label="专辑" />-->
<!--      <el-table-column label="编辑" width="80" align="center">-->
<!--        <template #default="scope">-->
<!--          <el-dropdown>-->
<!--            <el-icon @click="handleEdit(scope.row)"><MoreFilled /></el-icon>-->
<!--            <template #dropdown>-->
<!--              <el-dropdown-menu>-->
<!--                <el-dropdown-item-->
<!--                  :icon="Download"-->
<!--                  @click="-->
<!--                    downloadMusic({-->
<!--                      songUrl: scope.row.url,-->
<!--                      songName: scope.row.name,-->
<!--                    })-->
<!--                  ">下载</el-dropdown-item>-->
<!--                <el-dropdown-item :icon="Delete" v-if="show" @click="deleteCollection({ id: scope.row.id })">删除</el-dropdown-item>-->
<!--              </el-dropdown-menu>-->
<!--            </template>-->
<!--          </el-dropdown>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--    </el-table>-->
<!--  </div>-->
  <div class="comment">
    <h2 class="comment-title">
      <span>评论</span>
      <span class="comment-desc">共 {{ songList.length }} 条评论</span>
    </h2>
  </div>
  <ul class="popular">
    <li v-for="(item, index) in songList" :key="index">
<!--      <el-image class="popular-img" fit="contain" :src="attachImageUrl(item.avator)" />-->
      <div class="popular-msg">
        <ul>
          <li class="name">{{ item.businessId }}</li>
          <li class="time">{{ formatDate(item.revDate) }}</li>
          <li class="content">{{ item.revText }}</li>
        </ul>
      </div>
    </li>
  </ul>
</template>

<script lang="ts">
import { defineComponent, getCurrentInstance, toRefs, computed, reactive } from "vue";
import { useStore } from "vuex";
import { MoreFilled, Delete, Download } from "@element-plus/icons-vue";

import mixin from "@/mixins/mixin";
import { HttpManager } from "@/api";
import { Icon } from "@/enums";
import YinIcon from "@/components/layouts/YinIcon.vue";
import {formatDate} from "@/utils";

export default defineComponent({
  methods: {formatDate},
  components: {
    // YinIcon,
    // MoreFilled,
  },
  props: {
    songList: Array,
    show: {
      default: false
    }
  },
  emits: ["changeData"],
  setup(props) {
    const { getSongTitle, getSingerName, playMusic, checkStatus, downloadMusic } = mixin();
    const { proxy } = getCurrentInstance();
    const store = useStore();

    const { songList } = toRefs(props);

    const iconList = reactive({
      dislike: Icon.Dislike,
      like: Icon.Like,
    });

    const songUrl = computed(() => store.getters.songUrl);
    const singerName = computed(() => store.getters.singerName);
    const songTitle = computed(() => store.getters.songTitle);
    const dataList = computed(() => {
      const list = [];
      songList.value.forEach((item: any, index) => {
        item["songName"] = getSongTitle(item.name);
        item["singerName"] = getSingerName(item.name);
        item["index"] = index;
        list.push(item);
      });
      return list;
    });

    function handleClick(row) {
      playMusic({
        id: row.id,
        url: row.url,
        pic: row.pic,
        index: row.index,
        name: row.name,
        lyric: row.lyric,
        currentSongList: songList.value,
      });
    }

    function handleEdit(row) {
      console.log("row", row);
    }

    const userId = computed(() => store.getters.userId);

    // async function deleteCollection({ id }) {
    //   if (!checkStatus()) return;
    //
    //   const result = (await HttpManager.deleteCollection(userId.value, id)) as ResponseBody;
    //   (proxy as any).$message({
    //     message: result.message,
    //     type: result.type,
    //   });
    //
    //   if (result.data === false) proxy.$emit("changeData", result.data);
    // }

    return {
      dataList,
      iconList,
      Delete,
      Download,
      songUrl,
      singerName,
      songTitle,
      handleClick,
      handleEdit,
      downloadMusic,
      // deleteCollection,
    };
  },
});
</script>

<!--<style lang="scss" scoped>-->
<!--@import "@/assets/css/var.scss";-->
<!--@import "@/assets/css/global.scss";-->

<!--.content {-->
<!--  background-color: $color-white;-->
<!--  border-radius: $border-radius-songlist;-->
<!--  padding: 10px;-->
<!--}-->

<!--.content:deep(.el-table__row.current-row) {-->
<!--  color: $color-black;-->
<!--  font-weight: bold;-->
<!--}-->

<!--.content:deep(.el-table__row) {-->
<!--  cursor: pointer;-->
<!--}-->

<!--.icon {-->
<!--  @include icon(1.2em, $color-black);-->
<!--}-->
<!--</style>-->
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