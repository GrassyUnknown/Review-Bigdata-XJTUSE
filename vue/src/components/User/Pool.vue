<!-- FoodReservation.vue -->
<template>
    <div>
      <header>
        <h1 class="header-title">泳池预定</h1>
      </header>
  
      <section class="booking-container">
        <div class="booking-info">
          <h2 class="food-name">{{ foodName }}</h2>
          <div class="image-container">
            <img :src="foodImage" :alt="foodName" class="food-image">
          </div>
        </div>
  
        <div class="booking-form">
          <form @submit.prevent="confirmReservation">
            <div class="form-group">
              <label for="reservationDate">预定日期:</label>
              <input type="date" id="reservationDate" v-model="reservationDate" required>
            </div>
  
            <div class="form-group">
              <label for="reservationTime">预定时间:</label>
              <input type="time" id="reservationTime" v-model="reservationTime" required>
            </div>
  
            <button type="submit" class="confirm-button">确认预定</button>
          </form>
        </div>
      </section>
  
      <footer>
        <!-- 底部内容，可根据需要添加 -->
      </footer>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        foodName: '标准泳池',
        foodImage: require('@/assets/swimming.png'), // 你的餐饮图片路径
        reservationDate: '',
        reservationTime: '',
      };
    },
    methods: {
      confirmReservation() {
        const reservationDetails = {
          food: { name: this.foodName, image: this.foodImage },
          reservationDate: this.reservationDate,
          reservationTime: this.reservationTime,
        };
        // 处理预定逻辑，可以将 reservationDetails 发送到服务器等
        console.log('预定详情:', reservationDetails);
        // 跳转到预定成功页面或其他页面
        this.$router.push('/reservation-success');
      },
    },
  };
  </script>
  
  <style scoped>
  header {
    background-color: #333;
    color: #fff;
    text-align: center;
    padding: 1em;
  }
  
  .food-image {
    width: 100%; /* 让图片宽度占满父容器 */
    max-width: 100%; /* 图片最大宽度为父容器宽度，不超过原图宽度 */
    height: auto; /* 根据宽度自适应高度 */
    border: 2px solid #ddd;
    border-radius: 10px;
    overflow: hidden;
  }
  
  .booking-form {
    width: 100%;
    margin-top: 20px;
  }
  
  .form-group {
    margin-bottom: 15px;
  }
  
  label {
    display: block;
    margin-bottom: 5px;
    justify-content: right;
  }
  
  input {
    width: 100%;
    padding: 10px;
    box-sizing: border-box;
    border: 1px solid #ccc;
    border-radius: 4px;
    margin-top: 5px;
  }
  
  .confirm-button {
    background-color: #4CAF50;
    color: white;
    border: none;
    padding: 10px 20px;
    text-align: right;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    cursor: pointer;
    border-radius: 4px;
    transition-duration: 0.4s;
  }
  
  .confirm-button:hover {
    background-color: white;
    color: black;
    border: 2px solid #4CAF50;
  }
  </style>
  