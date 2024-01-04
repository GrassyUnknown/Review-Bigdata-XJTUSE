<template>
    <div>
      <header>
        <h1>活动预订</h1>
      </header>
  
      <section id="activities" class="activity-container">
        <article v-for="activity in activities" :key="activity.id" class="activity">
          <img :src="activity.image" :alt="activity.name" class="activity-image">
          <div class="activity-info">
            <h2>{{ activity.name }}</h2>
            <p>{{ activity.description }}</p>
            <div class="price">￥{{ activity.price }}</div>
          </div>
          <button @click="reserveActivity(activity)" class="reserve-button">预定</button>
        </article>
      </section>
  
      <reservation-modal
        :is-modal-open="isModalOpen"
        :selected-activity="selectedActivity"
        @close="closeModal"
        @confirm="confirmReservation"
      ></reservation-modal>
    </div>
  </template>
  
  <script>
  import ReservationModal from '@/components/User/ReservationModal.vue';
  
  export default {
    components: {
      ReservationModal,
    },
    data() {
      return {
        isModalOpen: false,
        selectedActivity: null,
        activities: [
          {
            id: 1,
            name: '健身房',
            image: require('@/assets/gym.png'),
            description: '尽情锻炼身体，享受健康生活。',
            price: 50,
          },
          {
            id: 2,
            name: '游泳',
            image: require('@/assets/swimming.png'),
            description: '在清凉的泳池中畅游，放松身心。',
            price: 30,
          },
          {
            id: 3,
            name: '桑拿房',
            image: require('@/assets/sauna.png'),
            description: '舒适的桑拿体验，排除身体疲劳。',
            price: 40,
          },
          {
            id: 4,
            name: '温泉',
            image: require('@/assets/hot-spring.png'),
            description: '沐浴温泉，尽情享受温暖和宁静。',
            price: 60,
          },
        ],
      };
    },
    methods: {
      reserveActivity(activity) {
        this.$router.push({
          name: 'activitypage',
          params: { activity: activity },
        });
      },
      closeModal() {
        this.isModalOpen = false;
        this.selectedActivity = null;
      },
      confirmReservation(reservationDetails) {
        console.log('预定详情:', reservationDetails);
        // 在这里处理预定逻辑，可以将 reservationDetails 发送到服务器等
      },
    },
  };
  </script>
  
  <style scoped>
  /* 添加你的样式，可以在这里覆盖全局样式 */
  /* ... */
  </style>
  