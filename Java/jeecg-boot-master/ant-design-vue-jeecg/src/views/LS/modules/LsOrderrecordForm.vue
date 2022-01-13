<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <!-- 主表单区域 -->
      <a-form-model ref="form" :model="model" :rules="validatorRules" slot="detail">
        <a-row>
          <a-col :span="24" >
            <a-form-model-item label="订单编码" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="orderCode">
              <a-input v-model="model.orderCode" placeholder="请输入订单编码" ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24" >
            <a-form-model-item label="总金额" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="totalCost">
              <a-input-number v-model="model.totalCost" placeholder="请输入总金额" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24" >
            <a-form-model-item label="下单时间" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="time">
              <j-date placeholder="请选择下单时间" v-model="model.time" style="width: 100%" />
            </a-form-model-item>
          </a-col>
        </a-row>
      </a-form-model>
    </j-form-container>
      <!-- 子表单区域 -->
    <a-tabs v-model="activeKey" @change="handleChangeTabs">
      <a-tab-pane tab="订单买家表" :key="refKeys[0]" :forceRender="true">
        <ls-orderbuyer-form ref="lsOrderbuyerForm" @validateError="validateError" :disabled="formDisabled"></ls-orderbuyer-form>
      </a-tab-pane>
      
      <a-tab-pane tab="订单商家表" :key="refKeys[1]" :forceRender="true">
        <j-editable-table
          :ref="refKeys[1]"
          :loading="lsOrdersellerTable.loading"
          :columns="lsOrdersellerTable.columns"
          :dataSource="lsOrdersellerTable.dataSource"
          :maxHeight="300"
          :disabled="formDisabled"
          :rowNumber="true"
          :rowSelection="true"
          :actionButton="true"/>
      </a-tab-pane>
    </a-tabs>
  </a-spin>
</template>

<script>

  import { getAction } from '@/api/manage'
  import { FormTypes,getRefPromise,VALIDATE_NO_PASSED } from '@/utils/JEditableTableUtil'
  import { JEditableTableModelMixin } from '@/mixins/JEditableTableModelMixin'
  import { validateDuplicateValue } from '@/utils/util'
  import LsOrderbuyerForm from './LsOrderbuyerForm.vue'

  export default {
    name: 'LsOrderrecordForm',
    mixins: [JEditableTableModelMixin],
    components: {
      LsOrderbuyerForm,
    },
    data() {
      return {
        labelCol: {
          xs: { span: 24 },
          sm: { span: 6 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 },
        },
        labelCol2: {
          xs: { span: 24 },
          sm: { span: 3 },
        },
        wrapperCol2: {
          xs: { span: 24 },
          sm: { span: 20 },
        },
        model:{
        },
        // 新增时子表默认添加几行空数据
        addDefaultRowNum: 1,
        validatorRules: {
           orderCode: [
              { required: true, message: '请输入订单编码!'},
           ],
        },
        refKeys: ['lsOrderbuyer', 'lsOrderseller', ],
        tableKeys:['lsOrderseller', ],
        activeKey: 'lsOrderbuyer',
        // 订单买家表
        lsOrderbuyerTable: {
          loading: false,
          dataSource: [],
          columns: [
          ]
        },
        // 订单商家表
        lsOrdersellerTable: {
          loading: false,
          dataSource: [],
          columns: [
            {
              title: '商家信息表',
              key: 'sellerInfor',
              type: FormTypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue:'',
              validateRules: [{ required: true, message: '${title}不能为空' }],
            },
          ]
        },
        url: {
          add: "/LS/lsOrderrecord/add",
          edit: "/LS/lsOrderrecord/edit",
          queryById: "/LS/lsOrderrecord/queryById",
          lsOrderbuyer: {
            list: '/LS/lsOrderrecord/queryLsOrderbuyerByMainId'
          },
          lsOrderseller: {
            list: '/LS/lsOrderrecord/queryLsOrdersellerByMainId'
          },
        }
      }
    },
    props: {
      //表单禁用
      disabled: {
        type: Boolean,
        default: false,
        required: false
      }
    },
    computed: {
      formDisabled(){
        return this.disabled
      },
    },
    created () {
    },
    methods: {
      addBefore(){
        this.$refs.lsOrderbuyerForm.clearFormData()
        this.lsOrdersellerTable.dataSource=[]
      },
      getAllTable() {
        let values = this.tableKeys.map(key => getRefPromise(this, key))
        return Promise.all(values)
      },
      /** 调用完edit()方法之后会自动调用此方法 */
      editAfter() {
        this.$nextTick(() => {
          this.$refs.lsOrderbuyerForm.initFormData(this.url.lsOrderbuyer.list,this.model.id)
        })
        // 加载子表数据
        if (this.model.id) {
          let params = { id: this.model.id }
          this.requestSubTableData(this.url.lsOrderseller.list, params, this.lsOrdersellerTable)
        }
      },
      //校验所有一对一子表表单
      validateSubForm(allValues){
          return new Promise((resolve,reject)=>{
            Promise.all([
                this.$refs.lsOrderbuyerForm.validate(0),
            ]).then(() => {
              resolve(allValues)
            }).catch(e => {
              if (e.error === VALIDATE_NO_PASSED) {
                // 如果有未通过表单验证的子表，就自动跳转到它所在的tab
                this.activeKey = e.index == null ? this.activeKey : this.refKeys[e.index]
              } else {
                console.error(e)
              }
            })
          })
      },
      /** 整理成formData */
      classifyIntoFormData(allValues) {
        let main = Object.assign(this.model, allValues.formValue)
        return {
          ...main, // 展开
          lsOrderbuyerList: this.$refs.lsOrderbuyerForm.getFormData(),
          lsOrdersellerList: allValues.tablesValue[0].values,
        }
      },
      validateError(msg){
        this.$message.error(msg)
      },

    }
  }
</script>

<style scoped>
</style>