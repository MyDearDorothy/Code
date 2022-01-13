<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form-model ref="form" :model="model" :rules="validatorRules" slot="detail">
        <a-row>
          <a-col :span="24">
            <a-form-model-item label="商家名" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="sellerName">
              <a-input v-model="model.sellerName" placeholder="请输入商家名"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="营业时间" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="beginTime">
              <j-date placeholder="请选择营业时间" v-model="model.beginTime"  style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="头像" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="displayPic">
              <j-image-upload isMultiple  v-model="model.displayPic" ></j-image-upload>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="地址" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="address">
             <j-area-linkage type="cascader" v-model="model.address" placeholder="请输入省市区"  />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="简介" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="brief">
              <a-textarea v-model="model.brief" rows="4" placeholder="请输入简介" />
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
    name: 'LsSellerForm',
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
           sellerName: [
              { required: true, message: '请输入商家名!'},
              { validator: (rule, value, callback) => validateDuplicateValue('ls_seller', 'seller_name', value, this.model.id, callback)},
           ],
           beginTime: [
              { required: true, message: '请输入营业时间!'},
           ],
        },
        url: {
          add: "/LS/lsSeller/add",
          edit: "/LS/lsSeller/edit",
          queryById: "/LS/lsSeller/queryById"
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