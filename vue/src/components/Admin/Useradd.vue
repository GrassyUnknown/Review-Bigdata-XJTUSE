
<template>
    <div>
        <el-form ref="form" :model="form" label-width="80px" :rules="rules" size="mini">
            <el-form-item label="用户编号" prop="userId">
                <el-input v-model="form.userId" placeholder="请输入用户编号"></el-input>
            </el-form-item>
            <el-form-item label="姓名" prop="userName">
                <el-input v-model="form.userName" placeholder="请输入姓名"></el-input>
            </el-form-item>
            <el-form-item label="角色" prop="userBalance">
                <el-input v-model="form.userBalance" placeholder="请输入角色"></el-input>
            </el-form-item>
            <el-form-item label="电话" prop="userTel">
                <el-input v-model="form.userTel" placeholder="请输入电话号码"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="userPwd">
                <el-input v-model="form.userPwd" placeholder="请输入密码"></el-input>
            </el-form-item>
            <el-form-item label="描述" prop="userDescription">
                <el-input v-model="form.userDescription" placeholder="请输入描述"></el-input>
            </el-form-item>
            <el-form-item label="VIP状态" prop="userIsvip">
                <el-input v-model="form.userIsvip" placeholder="请输入VIP状态"></el-input>
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
                    userId: 0,
                    userName: '姓名',
                    userBalance: 3,
                    userTel: 1,
                    userPwd: 'pwd',
                    userDescription: 'description',
                    userIsvip: 1
                },
            }
        },
        methods: {
            onSubmit() {
                axios.post('/user', this.form).then(res => {
                    if(res.data.code==1){
                        this.$message({
                        type:'success',
                        message: '添加成功'
                    })}
                    else{
                        if(res.data.code==0){
                        this.$message({
                        type:'warning',
                        message: '添加失败'
                    })
                    }}
                }).catch(err => {
                console.log(err);
             });

                this.$router.push("/admin/user").then(() => {
                    location.reload();
                });;
            },
            reset() {
                this.$refs.form.resetFields();
                this.form = {
                    userId: '',
                    userName: '',
                    userBalance: '',
                    userTel: '',
                    userPwd: '',
                    userDescription: '',
                    userIsvip: ''
                }
            }
        }
    }
    
</script>