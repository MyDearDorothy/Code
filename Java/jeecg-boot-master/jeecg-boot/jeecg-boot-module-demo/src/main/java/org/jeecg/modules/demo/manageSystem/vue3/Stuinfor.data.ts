import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
//列表数据
export const columns: BasicColumn[] = [
    {
    title: '学生ID',
    align:"center",
    dataIndex: 'stuId'
   },
   {
    title: '学生姓名',
    align:"center",
    dataIndex: 'stuName'
   },
   {
    title: '出生日期',
    align:"center",
    dataIndex: 'stuBirth',
    customRender:({text}) =>{
      return !text?"":(text.length>10?text.substr(0,10):text)
    },
   },
   {
    title: '性别',
    align:"center",
    dataIndex: 'stuSex_dictText'
   },
   {
    title: '学院',
    align:"center",
    dataIndex: 'stuCollege'
   },
   {
    title: '专业',
    align:"center",
    dataIndex: 'stuMajor'
   },
   {
    title: '班级',
    align:"center",
    dataIndex: 'stuClass'
   },
   {
    title: '家庭地址',
    align:"center",
    dataIndex: 'stuAddress',
    slots: { customRender: 'pcaSlot' },//TODO 未翻译
   },
   {
    title: '手机号码',
    align:"center",
    dataIndex: 'stuPhone'
   },
];
//查询数据
export const searchFormSchema: FormSchema[] = [
];
//表单数据
export const formSchema: FormSchema[] = [
  {
    label: '学生ID',
    field: 'stuId',
    component: 'Input',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入学生ID!'},
          ];
     },
  },
  {
    label: '学生姓名',
    field: 'stuName',
    component: 'Input',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入学生姓名!'},
          ];
     },
  },
  {
    label: '出生日期',
    field: 'stuBirth',
    component: 'DatePicker',
  },
  {
    label: '性别',
    field: 'stuSex',
    component: 'JDictSelectTag',
    componentProps:{
        dictCode:"sex"
     }
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入性别!'},
          ];
     },
  },
  {
    label: '学院',
    field: 'stuCollege',
    component: 'Input',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入学院!'},
          ];
     },
  },
  {
    label: '专业',
    field: 'stuMajor',
    component: 'Input',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入专业!'},
          ];
     },
  },
  {
    label: '班级',
    field: 'stuClass',
    component: 'Input',
  },
  {
    label: '家庭地址',
    field: 'stuAddress',
    component: 'JAreaLinkage',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入家庭地址!'},
          ];
     },
  },
  {
    label: '手机号码',
    field: 'stuPhone',
    component: 'Input',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入手机号码!'},
                 { pattern: /^1[3456789]\d{9}$/, message: '请输入正确的手机号码!'},
          ];
     },
  },
];
