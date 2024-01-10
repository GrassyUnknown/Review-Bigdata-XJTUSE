<template>
  <div class="personal">
    <div class="personal-info">
      <el-image class="personal-img" fit="contain" :src="attachImageUrl(userPic)" />
      <div class="personal-msg">
        <div class="username">{{ userName }}</div>
        <div class="introduction">UID:{{ userId }}</div>
      </div>
      <el-button class="edit-info" round :icon="Edit" @click="goPage()">修改个人信息</el-button>
    </div>
    <UserComment :current-list="currentSongList"  :type="1"></UserComment>
<!--    <p>{{personalInfo.userId}}</p>-->
<!--    <div class="personal-body">-->
<!--      <song-list :songList="collectSongList" :show="true" @changeData="changeData"></song-list>-->
<!--    </div>-->
<!--    <el-dialog v-model="dialogTableVisible" title="修改头像">-->
<!--      <upload></upload>-->
<!--    </el-dialog>-->
  </div>
</template>

<script lang="ts">
import {defineComponent, nextTick, ref, computed, watch, reactive, onMounted} from "vue";
import { useStore } from "vuex";
import { Edit } from "@element-plus/icons-vue";
import SongList from "@/components/SongList.vue";
import Upload from "../setting/Upload.vue";
import mixin from "@/mixins/mixin";
import { HttpManager } from "@/api";
import {NavName, RouterName} from "@/enums";
import UserComment from "@/components/UserComment.vue";

export default defineComponent({
  components: {
    UserComment
    // SongList,
    // Upload,
  },
  setup() {
    const store = useStore();

    const { routerManager } = mixin();

    const dialogTableVisible = ref(false);
    const collectSongList = ref([]); // 收藏的歌曲
    const personalInfo = ref([]);
    const userId = computed(() => store.getters.userId);
    const userName = computed(() => store.getters.username);
    const userPic = computed(() => store.getters.userPic);
    const currentSongList = ref([]);

    watch(userPic, () => {
      dialogTableVisible.value = false;
    });


    function goPage() {
      // routerManager(RouterName.Setting, { path: RouterName.Setting });
      // 魔改 lingchen
      alert("暂未完成该功能")
    }

    // 获取收藏的歌曲
    async function getCollection(userId) {
      collectSongList.value = []
      const result = (await HttpManager.getCollectionOfUser(userId)) as ResponseBody;
      const collectIDList = result.data || []; // 存放收藏的歌曲ID
      // 通过歌曲ID获取歌曲信息
      for (const item of collectIDList) {
        if (!item.songId) {
          console.error(`歌曲${item}异常`);
          continue;
        }

        const result = (await HttpManager.getSongOfId(item.songId)) as ResponseBody;
        collectSongList.value.push(result.data[0]);
      }
    }

    async function getUserInfo(userId: any){
      const result = (await HttpManager.getUserOfId(userId)) as ResponseBody;
      personalInfo.value = result.data;
    }

    async function getCommentInfo(userId: any){
      try {
        const result = (await HttpManager.getSongOfSingerId(userId)) as ResponseBody;
        currentSongList.value = result.data;
      } catch (error) {
        console.error(error);
      }
    }
    function changeData() {
      getCollection(userId.value);
    }

    nextTick(() => {
      getUserInfo(userId.value);
      getCommentInfo(userId.value);
      getCollection(userId.value);
    });

    return {
      Edit,
      userPic,
      dialogTableVisible,
      collectSongList,
      personalInfo,
      attachImageUrl: HttpManager.attachImageUrl,
      goPage,
      userName,
      userId,
      currentSongList,
      changeData,
    };
  },
});
</script>

<style lang="scss" scoped>
@import "@/assets/css/var.scss";

.personal {
  padding-top: $header-height + 150px;

  &::before {
    content: "";
    background-color: $color-blue-shallow;
    position: absolute;
    top: 0;
    width: 100%;
    height: $header-height + 150px;
  }
}

.personal-info {
  position: relative;
  margin-bottom: 60px;
  .personal-img {
    height: 200px;
    width: 200px;
    border-radius: 50%;
    border: 5px solid $color-white;
    position: absolute;
    top: -180px;
    left: 50px;
    cursor: pointer;
  }
  .personal-msg {
    margin-left: 300px;
    position: absolute;
    top: -120px;

    .username {
      font-size: 50px;
      font-weight: 600;
    }

    .introduction {
      font-size: 20px;
      font-weight: 500;
    }
  }
  .edit-info {
    position: absolute;
    right: 10vw;
    margin-top: -120px;
  }
}

@media screen and (min-width: $sm) {
  .personal-body {
    padding: 0px 100px;
  }
}

@media screen and (max-width: $sm) {
  .edit-info {
    display: none;
  }
}
</style>
