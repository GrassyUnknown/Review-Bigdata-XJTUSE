<template>
  <div>
    <header>
      <h1>欢迎预定我们的酒店</h1>
    </header>

    <section id="rooms" class="room-container">
      <article v-for="room in rooms" :key="room.id" class="room">
        <img :src="room.image" :alt="room.name" class="room-image">
        <div class="room-info">
          <h2>{{ room.name }}</h2>
          <p>{{ room.description }}</p>
          <div class="price">￥{{ room.price }}</div>
        </div>
        <button @click="reserveRoom(room)" class="reserve-button">预定</button>
      </article>
    </section>

    <reservation-modal
      :is-modal-open="isModalOpen"
      :selected-room="selectedRoom"
      @close="closeModal"
      @confirm="confirmReservation"
    ></reservation-modal>


  </div>
</template>

<script>
import ReservationModal from '@/components/User/ReservationModal.vue';

export default {
  components: {
    ReservationModal,  // 注册 ReservationModal 组件
  },
  data() {
    return {
      isModalOpen: false,
      selectedRoom: null, // 初始化为一个空对象
      rooms: [
        {
          id: 1,
          name: '豪华套房',
          image: require('@/assets/room1.png'),
          description: '宽敞明亮的房间，设有独立卫浴和豪华设施。',
          price: 1888,
        },
        {
          id: 2,
          name: '家庭套房',
          image: require('@/assets/room2.png'),
          description: '适合家庭入住，设有舒适的休息区和便利的设施。',
          price: 666,
        },
        {
          id: 3,
          name: '商务房',
          image: require('@/assets/room3.png'),
          description: '专为商务人士设计，提供便捷工作和休息空间。',
          price: 400,
        },
        {
          id: 4,
          name: '海景套房',
          image: require('@/assets/room4.png'),
          description: '享受宽敞套房和壮丽海景，尽情沐浴在阳光中。',
          price: 988,
        },
        {
          id: 5,
          name: '情趣套房',
          image: require('@/assets/room5.png'),
          description: '独特设计主题套房，为您与爱人带来爱的入住体验。',
          price: 521,
        },
        {
          id: 6,
          name: '标准客房',
          image: require('@/assets/room6.png'),
          description: '舒适实惠的标准客房，满足您的基本需求。',
          price : 388,
        },
        {
          id: 7,
          name: '豪华别墅',
          image: require('@/assets/room7.png'),
          description: '私密豪华别墅，拥有独立花园和泳池。',
          price: 3888,
        },
        {
          id: 8,
          name: '电竞套房',
          image: require('@/assets/room8.png'),
          description: '完备电竞设施，舒适的房间，让您和朋友爽玩通宵',
          price: 488,
        },
        {
          id: 9,
          name: '动物套房',
          image: require('@/assets/room9.png'),
          description: '与动物亲密交流的机会，让您和朋友一起玩耍',
          price: 998,
        },
        // ... 其他房间
      ],
    };
  },
  methods: {
    reserveRoom(room) {
      // 将房间信息传递到 bookpage 页面
      this.$router.push({
        name: 'bookpage',
        params: { room: room },
      });
    },
    closeModal() {
      this.isModalOpen = false;
      this.selectedRoom = null;
    },

  },
};
</script>

<style scoped>
/* 添加你的样式，可以在这里覆盖全局样式 */
body {
  font-family: 'Arial', sans-serif;
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

header {
  background-color: #333;
  color: #fff;
  text-align: center;
  padding: 1em;
}

.room-container {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-around;
  padding: 2em;
}

.room {
  width: 400px;
  margin: 1em;
  border: 1px solid #ddd;
  position: relative;
}

.room-image-container {
  position: relative;
  overflow: hidden;
}

.room-image {
  width: 100%;
  height: 300px;
  object-fit: cover;
}

.room-info {
  padding: 1em;
  text-align: center;
}

.price {
  font-weight: bold;
  color: red; /* 价格的字体颜色设为红色 */
  font-size: 20px; /* 价格的字体大小设为20px，可以根据需要调整 */
}

.reserve-button {
  background-color: #4CAF50;
  color: white;
  border: none;
  padding: 10px 20px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  cursor: pointer;
  transition-duration: 0.4s;
}

.reserve-button:hover {
  background-color: white;
  color: black;
  border: 2px solid #4CAF50;
}

footer {
  background-color: #333;
  color: #fff;
  text-align: center;
  padding: 1em;
  position: fixed;
  bottom: 0;
  width: 100%;
}
</style>

