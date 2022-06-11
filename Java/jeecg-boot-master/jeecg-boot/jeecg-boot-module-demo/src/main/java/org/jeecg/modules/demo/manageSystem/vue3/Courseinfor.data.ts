import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
//列表数据
export const columns: BasicColumn[] = [
    {
    title: '课程编号',
    align:"center",
    dataIndex: 'courseId'
   },
   {
    title: '课程名称',
    align:"center",
    dataIndex: 'courseName'
   },
   {
    title: '上课地点',
    align:"center",
    dataIndex: 'courseLocation'
   },
   {
    title: '上课教师ID',
    align:"center",
    dataIndex: 'courseTeaId'
   },
   {
    title: '上课最大学生数目',
    align:"center",
    dataIndex: 'courseNum'
   },
   {
    title: '开课学院',
    align:"center",
    dataIndex: 'courseDepart'
   },
   {
    title: '课程可选数目',
    align:"center",
    dataIndex: 'courseLeft'
   },
];
//查询数据
export const searchFormSchema: FormSchema[] = [
];
//表单数据
export const formSchema: FormSchema[] = [
  {
    label: '课程编号',
    field: 'courseId',
    component: 'Input',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入课程编号!'},
          ];
     },
  },
  {
    label: '课程名称',
    field: 'courseName',
    component: 'Input',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入课程名称!'},
          ];
     },
  },
  {
    label: '上课地点',
    field: 'courseLocation',
    component: 'Input',
  },
  {
    label: '上课教师ID',
    field: 'courseTeaId',
    component: 'Input',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入上课教师ID!'},
          ];
     },
  },
  {
    label: '上课最大学生数目',
    field: 'courseNum',
    component: 'InputNumber',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入上课最大学生数目!'},
          ];
     },
  },
  {
    label: '开课学院',
    field: 'courseDepart',
    component: 'Input',
  },
  {
    label: '课程可选数目',
    field: 'courseLeft',
    component: 'InputNumber',
    dynamicRules: ({model,schema}) => {
          return [
                 { required: true, message: '请输入课程可选数目!'},
          ];
     },
  },
];
