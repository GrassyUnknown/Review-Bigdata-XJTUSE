
<template>
    <div>
        <el-form ref="form" :model="form" label-width="80px" :rules="rules" size="mini">
            <el-form-item label="设施编号" prop="facilityId">
                <el-input v-model="form.facilityId" placeholder="请输入设施编号"></el-input>
            </el-form-item>
            <el-form-item label="设施名" prop="facilityName">
                <el-input v-model="form.facilityName" placeholder="请输入设施名"></el-input>
            </el-form-item>
            <el-form-item label="设施容量" prop="facilityCapacity">
                <el-input v-model="form.facilityCapacity" placeholder="请输入设施容量"></el-input>
            </el-form-item>
            <el-form-item label="设施状态" prop="facilityNow">
                <el-input v-model="form.facilityNow" placeholder="请输入设施状态"></el-input>
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
                    facilityId: '',
                    facilityName: '',
                    facilityCapacity: '',
                    facilityNow: ''
                },
            }
        },
        methods: {
            onSubmit() {
                axios.post('/facility', this.form).then(res => {
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

                this.$router.push("/admin/facility").then(() => {
                    location.reload();
                });
            },
            reset() {
                this.$refs.form.resetFields();
                this.form = {
                    facilityId: '',
                    facilityName: '',
                    facilityCapacity: '',
                    facilityNow: ''
                }
            }
        }
    }
    
</script>