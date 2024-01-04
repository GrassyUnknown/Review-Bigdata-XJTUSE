
<template>
    <div>
        <el-form ref="form" :model="form" label-width="80px" :rules="rules" size="mini">
            <el-form-item label="房间编号" prop="roomId">
                <el-input v-model="form.roomId" placeholder="请输入房间编号"></el-input>
            </el-form-item>
            <el-form-item label="房间名" prop="roomName">
                <el-input v-model="form.roomName" placeholder="请输入房间名"></el-input>
            </el-form-item>
            <el-form-item label="房间金额" prop="roomCost">
                <el-input v-model="form.roomCost" placeholder="请输入房间金额"></el-input>
            </el-form-item>
            <el-form-item label="房间描述" prop="roomDescription">
                <el-input v-model="form.roomDescription" placeholder="请输入房间描述"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="onSubmit">提交</el-button>
                <el-button @click="reset">重置</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>
<script>
import axios from 'axios';
    export default {
        data() {
            return {
                form: {
                    roomId: '',
                    roomName: '',
                    roomCost: '',
                    roomDescription: ''
                },
            }
        },
        methods: {
            onSubmit() {
                axios.post('/room', this.form).then(res => {
                    if(res.data.code==1){
                        this.$message({
                        type:'success',
                        message: '添加成功'
                    })}
                    else{
                        if(res.data.code==0){
                        this.$message({
                        type:'fail',
                        message: '添加失败'
                    })
                    }}
                }).catch(err => {
                console.log(err);
             });

                this.$router.push("/admin/room").then(() => {
                    location.reload();
                });
            },
            reset() {
                this.$refs.form.resetFields();
                this.form = {
                    roomId: '',
                    roomName: '',
                    roomCost: '',
                    roomDescription: ''
                }
            }
        }
    }
    
</script>