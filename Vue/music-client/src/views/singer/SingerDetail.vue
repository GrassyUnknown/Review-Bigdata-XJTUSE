<template>
  <el-container>
    <el-aside class="album-slide">
      <el-image class="singer-img" fit="contain" :src="attachImageUrl(songDetails.pic)" />
      <div class="album-info">
        <h2>基本资料</h2>
        <ul>
          <li >名字：{{ songDetails.userName }}</li>
          <li>入站时间：{{ formatDate(songDetails.userYelpingSince) }}</li>
          <li>平均得分：{{ songDetails.userAverageStars }}</li>
        </ul>
      </div>
    </el-aside>
    <el-main class="album-main">
      <h1>{{ songDetails.userName }}(UID:{{ songDetails.userId }})</h1><p></p>
      <UserComment :current-list="currentSongList" :playId="songDetails.userId" :type="1"></UserComment>
    </el-main>
  </el-container>
</template>

<script lang="ts">
import { defineComponent, ref, computed, onMounted } from "vue";
import { useStore } from "vuex";
import mixin from "@/mixins/mixin";
import SongList from "@/components/SongList.vue";
import { HttpManager } from "@/api";
import {formatDate, getBirth} from "@/utils";
import UserComment from "@/components/UserComment.vue";

export default defineComponent({
  methods: {formatDate},
  components: {
    UserComment
    // SongList,
  },
  setup() {
    const store = useStore();
    const { getUserSex } = mixin();

    const currentSongList = ref([]);
    const songDetails = computed(() => store.getters.songDetails) as any;
    // alert();


    onMounted(async () => {
      try {
        const result = (await HttpManager.getSongOfSingerId(songDetails.value.userId)) as ResponseBody;
        currentSongList.value = result.data;
      } catch (error) {
        console.error(error);
      }
    });

    return {
      songDetails,
      currentSongList,
      attachImageUrl: HttpManager.attachImageUrl,
      getBirth,
      getUserSex,
    };
  },
});
</script>

<style lang="scss" scoped>
@import "@/assets/css/var.scss";


.album-slide {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding-top: 20px;

  .singer-img {
    height: 250px;
    width: 250px;
    border-radius: 10%;
  }

  .album-info {
    width: 60%;
    padding-top: 2rem;
    li {
      width: 100%;
      height: 30px;
      line-height: 30px;
    }
  }
}

.album-main {
  p {
    color: rgba(0, 0, 0, 0.5);
    margin: 10px 0 20px 0px;
  }
}

@media screen and (min-width: $sm) {
  .album-slide {
    position: fixed;
    width: 400px;
  }
  .album-main {
    min-width: 600px;
    padding-right: 10vw;
    margin-left: 400px;
  }
}

@media screen and (max-width: $sm) {
  .album-slide {
    display: none;
  }
}
</style>
