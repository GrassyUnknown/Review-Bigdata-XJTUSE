<template>
    <div v-if="isModalOpen" class="modal">
      <div class="modal-content">
        <span class="close" @click="closeModal">&times;</span>
        <h2>{{ selectedRoom.name }}</h2>
        <p>余量: {{ selectedRoom.availability }} 房间</p>
        <label for="checkInDate">入住日期:</label>
        <input type="date" id="checkInDate" v-model="checkInDate">
        <label for="stayDuration">入住天数:</label>
        <input type="number" id="stayDuration" v-model="stayDuration" min="1">
        <div class="modal-buttons">
          <button @click="confirmReservation">确定</button>
          <button @click="closeModal">取消</button>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    props: {
      isModalOpen: Boolean,
      selectedRoom: Object,
    },
    data() {
      return {
        checkInDate: '',
        stayDuration: 1,
      };
    },
    watch: {
  isModalOpen: {
    handler: function (newVal, oldVal) {
      console.log('isModalOpen changed:', newVal);
      if (newVal) {
        // 清空日期和入住天数
        this.checkInDate = '';
        this.stayDuration = 1;
      }
    },
    deep: true,
  },
},
    methods: {
      closeModal() {
        this.$emit('close');
        this.checkInDate = '';
        this.stayDuration = 1;
      },
      confirmReservation() {
        const reservationDetails = {
          room: this.selectedRoom.name,
          availability: this.selectedRoom.availability,
          checkInDate: this.checkInDate,
          stayDuration: this.stayDuration,
        };
        this.$emit('confirm', reservationDetails);
        this.closeModal();
      },
    },
  };
  </script>
  
  
  <style scoped>
  /* 样式可以根据你的需求进行调整 */
  .modal {
    display: none;
    position: fixed;
    z-index: 1;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    overflow: auto;
    background-color: rgb(0,0,0);
    background-color: rgba(0,0,0,0.4);
  }
  
  .modal-content {
    background-color: #fefefe;
    margin: 15% auto;
    padding: 20px;
    border: 1px solid #888;
    width: 80%;
  }
  
  .close {
    color: #aaa;
    float: right;
    font-size: 28px;
    font-weight: bold;
  }
  
  .close:hover,
  .close:focus {
    color: black;
    text-decoration: none;
    cursor: pointer;
  }
  
  .modal-buttons {
    margin-top: 15px;
  }
  
  button {
    background-color: #4CAF50;
    color: white;
    border: none;
    padding: 10px 20px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    cursor: pointer;
    margin: 0 5px;
  }
  
  button:hover {
    background-color: white;
    color: black;
    border: 2px solid #4CAF50;
  }
  </style>
  