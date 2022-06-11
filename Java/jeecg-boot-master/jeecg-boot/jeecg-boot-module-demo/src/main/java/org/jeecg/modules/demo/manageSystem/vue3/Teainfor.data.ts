import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
//列表数据
export const columns: BasicColumn[] = [
    {
    title: '教师ID',
    align:"center",
    dataIndex: 'teaId'
   },
   {
    title: '教师姓名',
    align:"center",
    dataIndex: 'teaName'
   },
   {
    title: '教师所属学院',
    align:"center",
    dataIndex: 'teaDepart'
   },
   {
    title: '手机号码',
    align:"center",
    dataIndex: 'teaPhone'
   },
   {
    title: '电子邮件',
    align:"center",
    dataIndex: 'teaEmail'
   },
   {
    title: '专业',
    align:"center",
    dataIndex: 'teaMajor'
   },
];
//查询数据
export const searchFormSchema: FormSchema[] = [
];
//表单数据
export const formSchema: FormSchema[] = [
  {
    label: '教师ID',
    field: 'teaId',
    component: 'Input',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入教师ID!'},
          ];
     },
  },
  {
    label: '教师姓名',
    field: 'teaName',
    component: 'Input',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入教师姓名!'},
          ];
     },
  },
  {
    label: '教师所属学院',
    field: 'teaDepart',
    component: 'Input',
  },
  {
    label: '手机号码',
    field: 'teaPhone',
    component: 'Input',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入手机号码!'},
                 { pattern: /^1[3456789]\d{9}$/, message: '请输入正确的手机号码!'},
          ];
     },
  },
  {
    label: '电子邮件',
    field: 'teaEmail',
    component: 'Input',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: false},
                 { pattern: /^([\w]+\.*)([\w]+)@[\w]+\.\w{3}(\.\w{2}|)$/, message: '请输入正确的电子邮件!'},
          ];
     },
  },
  {
    label: '专业',
    field: 'teaMajor',
    component: 'Input',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入专业!'},
          ];
     },
  },
];
