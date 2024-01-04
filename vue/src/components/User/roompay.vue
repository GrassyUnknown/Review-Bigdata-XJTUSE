<template>


    <div>
        <header>
            <h1 class="header-title">房间预定成功！请您选择支付方式</h1>
        </header>
        <div class="order-details">
            <h2>请核对订单详情</h2>
            <p>订单号: {{ order.roomorderId }}</p>
            <p>入住日期: {{ order.roomorderReservedate }}</p>
            <p>房间类型: {{ room.name }}</p>
            <p>预定人: {{ user.userName }}</p>
            <p>总价: {{ order.roomorderCost }}</p>
        </div>
        <div class="payment-options">
            <h2>选择支付方式</h2>
            <button class="payment-button" @click="payWithStoredValueCard">储值卡支付</button>
            <button class="payment-button" @click="payWithAlipay">支付宝支付</button>
            <button class="payment-button" @click="payWithWeChat">微信支付</button>
            <button class="payment-button" @click="payWithBankCard">银行卡支付</button>
            <button class="payment-button" @click="payOffline">线下支付</button>
            <p>如您选择线下支付，房间将保留至您入住时间的18:00，请您在18:00之前到店付款。</p>
            <p>逾期未付款，可能导致会员账号冻结！</p>
            <p>线上支付后，房间将为您整晚保留。</p>
        </div>
        <el-dialog title="储值卡支付" class="dialog-options" :visible.sync="StoredValueCarddialogVisible">
            <p>请确认支付</p>
            <p>您的储值卡余额为：{{ user.userBalance }}</p>
            <p>本次支付金额为：{{ order.roomorderCost }}</p>
            <p>{{vipNotice}}</p>
            <span slot="footer" class="dialog-footer">
                <el-button @click="StoredValueCarddialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="valueCardPay">确 定</el-button>
            </span>
        </el-dialog>
        <el-dialog title="支付宝支付" class="dialog-options" :visible.sync="AlipaydialogVisible">
            <p>请使用支付宝扫描二维码完成支付</p>
            <img src="@/assets/alipay.png" alt="支付宝二维码" width="200px" height="200px">
        </el-dialog>
        <el-dialog title="微信支付" class="dialog-options" :visible.sync="WeChatdialogVisible">
            <p>请使用微信扫描二维码完成支付</p>
            <img src="@/assets/wechat.png" alt="微信二维码" width="200px" height="200px">
        </el-dialog>
        <el-dialog title="银行卡支付" class="dialog-options" :visible.sync="BankdialogVisible">
            <p>请输入银行卡信息</p>
            <el-form :model="Bankcard" :rules="rules" ref="Bankcard" label-width="100px">
                <el-form-item label="银行卡号" prop="BankcardNumber">
                    <el-input v-model="Bankcard.BankcardNumber"></el-input>
                </el-form-item>
                <el-form-item label="银行卡密码" prop="BankcardPassword">
                    <el-input v-model="Bankcard.BankcardPassword"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="BankdialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="bankHandle">确 定</el-button>
            </span>
        </el-dialog>

    
    </div>
    
</template>

<script>
export default {
    data() {
        return {
            order: null,
            room: null,
            user: null,
            StoredValueCarddialogVisible: false,
            AlipaydialogVisible: false,
            WeChatdialogVisible: false,
            BankdialogVisible: false,
            Bankcard:{},
            vipNotice:"您不是VIP会员，此次交易不能享受优惠！"
        }
    },
    mounted() {
        this.order = this.$route.params.order;
        this.room = this.$route.params.room;
        this.user = this.$route.params.user;
        if(this.user.userIsvip === 1) {
                this.vipNotice = "您是VIP会员，此次交易享受9折优惠！"
                this.order.roomorderCost *= 0.9;
        }
    },
    methods: {
        payWithStoredValueCard() {
            
            this.StoredValueCarddialogVisible=true;
        },
        valueCardPay() {

            if(this.user.userBalance < this.order.roomorderCost) {
                alert("储值卡余额不足，请选择其他支付方式");
                return;
            }
            else{
                this.user.userBalance -= this.order.roomorderCost;
                this.order.roomorderDescription = "已支付，支付方式：储值卡";
                this.$axios.put('/user', this.user).then(res => {
                    if(res.data.code === 1) {
                        this.$axios.put('/roomorder', this.order).then(res => {
                            if(res.data.code === 1) {
                                this.$notify.success({
                                    title: '支付信息',
                                    message: '您已成功支付！',
                                    showClose: false
                                });
                                this.$router.push("/")
                            } else {
                                this.$notify.error({
                                    title: '支付信息',
                                    message: '已支付，更新订单状态时出错，请联系管理员。管理员电话：029-82668888',
                                    showClose: false
                                });
                            }
                        });
                    } else {
                        this.$notify.error({
                            title: '支付信息',
                            message: '支付失败，请重试！',
                            showClose: false
                        });
                    }
                });
            }
            
        },
        payWithAlipay() {
            this.AlipaydialogVisible = true;
            this.order.roomorderDescription = "已支付，支付方式：支付宝";
            this.$axios.put('/roomorder', this.order);
        },
        payWithWeChat() {
            this.WeChatdialogVisible = true;
            this.order.roomorderDescription = "已支付，支付方式：微信";
            this.$axios.put('/roomorder', this.order);
        },
        payWithBankCard() {
            this.BankdialogVisible = true;
        },
        bankHandle(){
            this.order.roomorderDescription = "已支付，支付方式：银行卡";
            this.$axios.put('/roomorder', this.order).then(res => {
                if(res.data.code === 1) {
                    this.$notify.success({
                        title: '支付信息',
                        message: '您已成功支付！',
                        showClose: false
                    });
                    this.$router.push("/")
                } else {
                    this.$notify.error({
                        title: '支付信息',
                        message: '更新订单状态时出错，请联系管理员。管理员电话：029-82668888',
                        showClose: false
                    });
                }
            });
        },
        payOffline() {
            this.order.roomorderDescription = "待线下支付";
            this.$axios.put('/roomorder', this.order).then(res => {
                if(res.data.code === 1) {
                    this.$notify.success({
                        title: '支付信息',
                        message: '您已成功预定！',
                        showClose: false
                    });
                    this.$router.push("/")
                } else {
                    this.$notify.error({
                        title: '支付信息',
                        message: '更新订单状态时出错，请联系管理员。管理员电话：029-82668888',
                        showClose: false
                    });
                }
            });
        },
    }
}
</script>

<style scoped>


.header-title {
    color: #333;
    font-size: 35px;
    margin-bottom: 20px;
}

.order-details {
    margin-top: 20px;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
    background-color: #f5f5f5;
}

.order-details h2 {
    font-size: 25px;
    margin-bottom: 10px;
}

.order-details p {
    font-size: 16px;
    margin-bottom: 7px;
    margin-top: 13px;
}

.payment-options {
    margin-top: 20px;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
    background-color: #f5f5f5;
}

.payment-button {
    font-size: 16px;
    padding: 10px 20px;
    margin-right: 10px;
}

.payment-options p{
    font-size: 18px;
    margin-top: 13px;
    margin-bottom: 7px;
}

.dialog-options p{
    font-size: 18px;
    margin-top: 13px;
    margin-bottom: 7px;
}
</style>
