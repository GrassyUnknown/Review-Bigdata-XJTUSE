<template>
  <div>
    <header>
      <h1 class="header-title">房间预定</h1>
    </header>

    <section class="booking-container">
      <div class="booking-info">
        <h2 class="room-name">{{ selectedRoom.name }}</h2>
        <div class="image-container">
          <img :src="selectedRoom.image" :alt="selectedRoom.name" class="room-image">
        </div>
      </div>

      <div class="room-info">
        <h3 class="info-title">房间设施与条件</h3>
        <ul>
          <li>·免费 WiFi·</li>
          <li>·液晶电视·</li>
          <li>·独立卫浴·</li>
          <li>·吹风机·</li>
          <li>·迷你吧·</li>
          <li>·空调·</li>
        </ul>
      </div>

      <div class="booking-form">
        <form @submit.prevent="confirmReservation">
          <div class="form-group">
            <label for="checkInDate">入住日期:</label>
            <input type="date" id="checkInDate" v-model="checkInDate" required>
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
      selectedRoom: null,
      checkInDate: '',
      checkOutDate: '',
    };
  },
  mounted() {
    // 在这里获取传递过来的房间信息，可以通过路由参数等方式
    this.selectedRoom = this.$route.params.room;
  },
  methods: {
    confirmReservation() {
      const user = JSON.parse(localStorage.getItem('user'));
      const roomorder = {
        roomorderId: user.userId*10000000+this.selectedRoom.id*1000000+parseInt(this.checkInDate.split('-').join('')),
        userId: user.userId,
        roomId: this.selectedRoom.id,
        roomorderCost: this.selectedRoom.price,
        roomorderDescription: "未支付",
        roomorderReservedate: this.checkInDate.toString(),
      }
      this.$axios.post('/roomorder', roomorder).then(res => {
        if(res.data.code === 1) {
          this.$notify.success({
            title: '预约信息',
            message: '您已成功预定你想要的房间！',
            showClose: false
          });
          // 处理预定逻辑，可以将 reservationDetails 发送到服务器等
          // 跳转到预定成功页面或其他页面
          this.$router.push({
            name: 'roompay',
            params: {order : roomorder, room : this.selectedRoom , user : user}});
        } else {
          this.$notify.error({
            title: '预约信息',
            message: '预约失败，请重试！',
            showClose: false
          });
        }
      });
          
      
      
    },
  },
};
</script>

<style scoped>
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

.header-title {
  margin: 0;
}

.booking-container {
  display: flex;
  flex-wrap: wrap;
  justify-content: center; /* 水平居中对齐 */
  margin: 20px;
}

.booking-info {
  width: 40%;
}

.room-name {
  color: #333;
  font-size: 24px;
  margin-bottom: 10px;
}

.image-container {
  border: 2px solid #ddd;
  padding: 10px;
  border-radius: 10px;
  overflow: hidden;
}

.room-image {
  width: 100%;
  height: auto;
  border: 1px solid #ddd;
  border-radius: 8px;
}

.room-info {
width: 40%;
font-size: 24px; /* 调整字号 */
margin-bottom: 25px; /* 调整行间距 */

}

.info-title {
color: #333;
font-size: 24px; /* 调整字号 */
margin-bottom: 45px; /* 调整行间距 */
}

.room-info li {
margin-bottom: 40px; /* 调整行间距 */
}

.room-info ul {
  list-style-type: none;
  padding: 0;
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