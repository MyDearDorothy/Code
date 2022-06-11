<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form-model ref="form" :model="model" :rules="validatorRules" slot="detail">
        <a-row>
          <a-col :span="24">
            <a-form-model-item label="课程编号" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="courseId">
              <a-input v-model="model.courseId" placeholder="请输入课程编号"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="课程名称" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="courseName">
              <a-input v-model="model.courseName" placeholder="请输入课程名称"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="上课地点" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="courseLocation">
              <a-input v-model="model.courseLocation" placeholder="请输入上课地点"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="上课教师ID" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="courseTeaId">
              <a-input v-model="model.courseTeaId" placeholder="请输入上课教师ID"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="上课最大学生数目" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="courseNum">
              <a-input-number v-model="model.courseNum" placeholder="请输入上课最大学生数目" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="开课学院" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="courseDepart">
              <a-input v-model="model.courseDepart" placeholder="请输入开课学院"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="课程可选数目" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="courseLeft">
              <a-input-number v-model="model.courseLeft" placeholder="请输入课程可选数目" style="width: 100%" />
            </a-form-model-item>
          </a-col>
        </a-row>
      </a-form-model>
    </j-form-container>
  </a-spin>
</template>

<script>

  import { httpAction, getAction } from '@/api/manage'
  import { validateDuplicateValue } from '@/utils/util'

  export default {
    name: 'CourseinforForm',
    components: {
    },
    props: {
      //表单禁用
      disabled: {
        type: Boolean,
        default: false,
        required: false
      }
    },
    data () {
      return {
        model:{
         },
        labelCol: {
          xs: { span: 24 },
          sm: { span: 5 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 },
        },
        confirmLoading: false,
        validatorRules: {
           courseId: [
              { required: true, message: '请输入课程编号!'},
           ],
           courseName: [
              { required: true, message: '请输入课程名称!'},
           ],
           courseTeaId: [
              { required: true, message: '请输入上课教师ID!'},
           ],
           courseNum: [
              { required: true, message: '请输入上课最大学生数目!'},
           ],
           courseLeft: [
              { required: true, message: '请输入课程可选数目!'},
           ],
        },
        url: {
          add: "/manageSystem/courseinfor/add",
          edit: "/manageSystem/courseinfor/edit",
          queryById: "/manageSystem/courseinfor/queryById"
        }
      }
    },
    computed: {
      formDisabled(){
        return this.disabled
      },
    },
    created () {
       //备份model原始值
      this.modelDefault = JSON.parse(JSON.stringify(this.model));
    },
    methods: {
      add () {
        this.edit(this.modelDefault);
      },
      edit (record) {
        this.model = Object.assign({}, record);
        this.visible = true;
      },
      submitForm () {
        const that = this;
        // 触发表单验证
        this.$refs.form.validate(valid => {
          if (valid) {
            that.confirmLoading = true;
            let httpurl = '';
            let method = '';
            if(!this.model.id){
              httpurl+=this.url.add;
              method = 'post';
            }else{
              httpurl+=this.url.edit;
               method = 'put';
            }
            httpAction(httpurl,this.model,method).then((res)=>{
              if(res.success){
                that.$message.success(res.message);
                that.$emit('ok');
              }else{
                that.$message.warning(res.message);
              }
            }).finally(() => {
              that.confirmLoading = false;
            })
          }
         
        })
      },
    }
  }
</script>