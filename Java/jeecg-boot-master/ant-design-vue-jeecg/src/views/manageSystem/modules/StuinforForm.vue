<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form-model ref="form" :model="model" :rules="validatorRules" slot="detail">
        <a-row>
          <a-col :span="24">
            <a-form-model-item label="学生ID" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="stuId">
              <a-input v-model="model.stuId" placeholder="请输入学生ID"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="学生姓名" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="stuName">
              <a-input v-model="model.stuName" placeholder="请输入学生姓名"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="出生日期" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="stuBirth">
              <j-date placeholder="请选择出生日期" v-model="model.stuBirth"  style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="性别" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="stuSex">
              <j-dict-select-tag type="list" v-model="model.stuSex" dictCode="sex" placeholder="请选择性别" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="学院" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="stuCollege">
              <a-input v-model="model.stuCollege" placeholder="请输入学院"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="专业" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="stuMajor">
              <a-input v-model="model.stuMajor" placeholder="请输入专业"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="班级" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="stuClass">
              <a-input v-model="model.stuClass" placeholder="请输入班级"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="家庭地址" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="stuAddress">
             <j-area-linkage type="cascader" v-model="model.stuAddress" placeholder="请输入省市区"  />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="手机号码" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="stuPhone">
              <a-input v-model="model.stuPhone" placeholder="请输入手机号码"  ></a-input>
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
    name: 'StuinforForm',
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
           stuId: [
              { required: true, message: '请输入学生ID!'},
           ],
           stuName: [
              { required: true, message: '请输入学生姓名!'},
           ],
           stuSex: [
              { required: true, message: '请输入性别!'},
           ],
           stuCollege: [
              { required: true, message: '请输入学院!'},
           ],
           stuMajor: [
              { required: true, message: '请输入专业!'},
           ],
           stuAddress: [
              { required: true, message: '请输入家庭地址!'},
           ],
           stuPhone: [
              { required: true, message: '请输入手机号码!'},
              { pattern: /^1[3456789]\d{9}$/, message: '请输入正确的手机号码!'},
           ],
        },
        url: {
          add: "/manageSystem/stuinfor/add",
          edit: "/manageSystem/stuinfor/edit",
          queryById: "/manageSystem/stuinfor/queryById"
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