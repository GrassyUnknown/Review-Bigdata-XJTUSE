
<template>
    <div>
        <el-form ref="form" :model="form" label-width="80px" :rules="rules" size="mini">
            <el-form-item label="餐食编号" prop="mealId">
                <el-input v-model="form.mealId" placeholder="请输入餐食编号"></el-input>
            </el-form-item>
            <el-form-item label="餐食名" prop="mealName">
                <el-input v-model="form.mealName" placeholder="请输入餐食名"></el-input>
            </el-form-item>
            <el-form-item label="餐食金额" prop="mealCost">
                <el-input v-model="form.mealCost" placeholder="请输入餐食金额"></el-input>
            </el-form-item>
            <el-form-item label="餐食描述" prop="mealDescription">
                <el-input v-model="form.mealDescription" placeholder="请输入餐食描述"></el-input>
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
                    mealId: '',
                    mealName: '',
                    mealCost: '',
                    mealDescription: ''
                },
            }
        },
        methods: {
            onSubmit() {
                axios.post('/meal', this.form).then(res => {
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

                this.$router.push("/admin/meal").then(() => {
                    location.reload();
                })
            },
            reset() {
                this.$refs.form.resetFields();
                this.form = {
                    mealId: '',
                    mealName: '',
                    mealCost: '',
                    mealDescription: ''
                }
            }
        }
    }
    
</script>