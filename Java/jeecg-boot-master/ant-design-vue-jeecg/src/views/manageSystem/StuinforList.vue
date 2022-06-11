<template>
  <a-card :bordered="false">
    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">
        </a-row>
      </a-form>
    </div>
    <!-- 查询区域-END -->

    <!-- 操作按钮区域 -->
    <div class="table-operator">
      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>
      <a-button type="primary" icon="download" @click="handleExportXls('学生信息')">导出</a-button>
      <a-upload name="file" :showUploadList="false" :multiple="false" :headers="tokenHeader" :action="importExcelUrl" @change="handleImportExcel">
        <a-button type="primary" icon="import">导入</a-button>
      </a-upload>
      <!-- 高级查询区域 -->
      <j-super-query :fieldList="superFieldList" ref="superQueryModal" @handleSuperQuery="handleSuperQuery"></j-super-query>
      <a-dropdown v-if="selectedRowKeys.length > 0">
        <a-menu slot="overlay">
          <a-menu-item key="1" @click="batchDel"><a-icon type="delete"/>删除</a-menu-item>
        </a-menu>
        <a-button style="margin-left: 8px"> 批量操作 <a-icon type="down" /></a-button>
      </a-dropdown>
    </div>

    <!-- table区域-begin -->
    <div>
      <div class="ant-alert ant-alert-info" style="margin-bottom: 16px;">
        <i class="anticon anticon-info-circle ant-alert-icon"></i> 已选择 <a style="font-weight: 600">{{ selectedRowKeys.length }}</a>项
        <a style="margin-left: 24px" @click="onClearSelected">清空</a>
      </div>

      <a-table
        ref="table"
        size="middle"
        :scroll="{x:true}"
        bordered
        rowKey="id"
        :columns="columns"
        :dataSource="dataSource"
        :pagination="ipagination"
        :loading="loading"
        :rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange}"
        class="j-table-force-nowrap"
        @change="handleTableChange">

        <template slot="htmlSlot" slot-scope="text">
          <div v-html="text"></div>
        </template>
        <template slot="imgSlot" slot-scope="text">
          <span v-if="!text" style="font-size: 12px;font-style: italic;">无图片</span>
          <img v-else :src="getImgView(text)" height="25px" alt="" style="max-width:80px;font-size: 12px;font-style: italic;"/>
        </template>
        <template slot="pcaSlot" slot-scope="text">
          <div>{{ getPcaText(text) }}</div>
        </template>
        <template slot="fileSlot" slot-scope="text">
          <span v-if="!text" style="font-size: 12px;font-style: italic;">无文件</span>
          <a-button
            v-else
            :ghost="true"
            type="primary"
            icon="download"
            size="small"
            @click="downloadFile(text)">
            下载
          </a-button>
        </template>

        <span slot="action" slot-scope="text, record">
          <a @click="handleEdit(record)">编辑</a>

          <a-divider type="vertical" />
          <a-dropdown>
            <a class="ant-dropdown-link">更多 <a-icon type="down" /></a>
            <a-menu slot="overlay">
              <a-menu-item>
                <a @click="handleDetail(record)">详情</a>
              </a-menu-item>
              <a-menu-item>
                <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.id)">
                  <a>删除</a>
                </a-popconfirm>
              </a-menu-item>
            </a-menu>
          </a-dropdown>
        </span>

      </a-table>
    </div>

    <stuinfor-modal ref="modalForm" @ok="modalFormOk"></stuinfor-modal>
  </a-card>
</template>

<script>

  import '@/assets/less/TableExpand.less'
  import { mixinDevice } from '@/utils/mixin'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import StuinforModal from './modules/StuinforModal'
  import {filterMultiDictText} from '@/components/dict/JDictSelectUtil'
  import Area from '@/components/_util/Area'

  export default {
    name: 'StuinforList',
    mixins:[JeecgListMixin, mixinDevice],
    components: {
      StuinforModal
    },
    data () {
      return {
        description: '学生信息管理页面',
        // 表头
        columns: [
          {
            title: '#',
            dataIndex: '',
            key:'rowIndex',
            width:60,
            align:"center",
            customRender:function (t,r,index) {
              return parseInt(index)+1;
            }
          },
          {
            title:'学生ID',
            align:"center",
            dataIndex: 'stuId'
          },
          {
            title:'学生姓名',
            align:"center",
            dataIndex: 'stuName'
          },
          {
            title:'出生日期',
            align:"center",
            dataIndex: 'stuBirth',
            customRender:function (text) {
              return !text?"":(text.length>10?text.substr(0,10):text)
            }
          },
          {
            title:'性别',
            align:"center",
            dataIndex: 'stuSex_dictText'
          },
          {
            title:'学院',
            align:"center",
            dataIndex: 'stuCollege'
          },
          {
            title:'专业',
            align:"center",
            dataIndex: 'stuMajor'
          },
          {
            title:'班级',
            align:"center",
            dataIndex: 'stuClass'
          },
          {
            title:'家庭地址',
            align:"center",
            dataIndex: 'stuAddress',
            scopedSlots: {customRender: 'pcaSlot'}
          },
          {
            title:'手机号码',
            align:"center",
            dataIndex: 'stuPhone'
          },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            fixed:"right",
            width:147,
            scopedSlots: { customRender: 'action' }
          }
        ],
        url: {
          list: "/manageSystem/stuinfor/list",
          delete: "/manageSystem/stuinfor/delete",
          deleteBatch: "/manageSystem/stuinfor/deleteBatch",
          exportXlsUrl: "/manageSystem/stuinfor/exportXls",
          importExcelUrl: "manageSystem/stuinfor/importExcel",
          
        },
        dictOptions:{},
        pcaData:'',
        superFieldList:[],
      }
    },
    created() {
      this.pcaData = new Area()
    this.getSuperFieldList();
    },
    computed: {
      importExcelUrl: function(){
        return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
      },
    },
    methods: {
      getPcaText(code){
        return this.pcaData.getText(code);
      },
      initDictConfig(){
      },
      getSuperFieldList(){
        let fieldList=[];
        fieldList.push({type:'string',value:'stuId',text:'学生ID',dictCode:''})
        fieldList.push({type:'string',value:'stuName',text:'学生姓名',dictCode:''})
        fieldList.push({type:'date',value:'stuBirth',text:'出生日期'})
        fieldList.push({type:'string',value:'stuSex',text:'性别',dictCode:'sex'})
        fieldList.push({type:'string',value:'stuCollege',text:'学院',dictCode:''})
        fieldList.push({type:'string',value:'stuMajor',text:'专业',dictCode:''})
        fieldList.push({type:'string',value:'stuClass',text:'班级',dictCode:''})
        fieldList.push({type:'pca',value:'stuAddress',text:'家庭地址'})
        fieldList.push({type:'string',value:'stuPhone',text:'手机号码',dictCode:''})
        this.superFieldList = fieldList
      }
    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less';
</style>