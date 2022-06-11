<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form-model ref="form" :model="model" :rules="validatorRules" slot="detail">
        <a-row>
          <a-col :span="24">
            <a-form-model-item label="教师ID" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="teaId">
              <a-input v-model="model.teaId" placeholder="请输入教师ID"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="教师姓名" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="teaName">
              <a-input v-model="model.teaName" placeholder="请输入教师姓名"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="教师所属学院" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="teaDepart">
              <a-input v-model="model.teaDepart" placeholder="请输入教师所属学院"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="手机号码" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="teaPhone">
              <a-input v-model="model.teaPhone" placeholder="请输入手机号码"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="电子邮件" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="teaEmail">
              <a-input v-model="model.teaEmail" placeholder="请输入电子邮件"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="专业" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="teaMajor">
              <a-input v-model="model.teaMajor" placeholder="请输入专业"  ></a-input>
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
    name: 'TeainforForm',
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
           teaId: [
              { required: true, message: '请输入教师ID!'},
           ],
           teaName: [
              { required: true, message: '请输入教师姓名!'},
           ],
           teaPhone: [
              { required: true, message: '请输入手机号码!'},
              { pattern: /^1[3456789]\d{9}$/, message: '请输入正确的手机号码!'},
           ],
           teaEmail: [
              { required: false},
              { pattern: /^([\w]+\.*)([\w]+)@[\w]+\.\w{3}(\.\w{2}|)$/, message: '请输入正确的电子邮件!'},
           ],
           teaMajor: [
              { required: true, message: '请输入专业!'},
           ],
        },
        url: {
          add: "/manageSystem/teainfor/add",
          edit: "/manageSystem/teainfor/edit",
          queryById: "/manageSystem/teainfor/queryById"
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