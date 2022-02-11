# -*-coding:utf-8-*-
# 数据分析与处理（二）
import pandas as pd

class Handler:
    # 初始化数据
    def __init__(self):
        self.data = pd.DataFrame([[90, 89, 90], [92, 86, 95], [91, 92, 90]],
                                 index=['小明', '小红', '小王'],
                                 columns=['语文', '数学', '英语'])
    # 数据选取
    def test1(self):
        print("列选取：")
        print(self.data[['语文','英语']])
        print("行选取：")
        print(self.data[0:2])              # 选取第1、2行，左闭右开,
        print("行列选取：")
        # loc方法使用字符串作为索引，iloc方法使用数字作为索引
        # 使用iloc和loc方法同时选取行和列
        print(self.data.iloc[0:2,[0,2]])   # 选取第1、2行，左闭右开,选取第1、3列。
        print(self.data.loc[['小明','小王'],['语文','数学']])

    # 数据整体情况查看
    def test2(self):
        print("表格的数据量大小：",self.data.shape)
        # 查看表格每一列的数据个数、平均值、标准差、最小值、25分位数、50分位数、75分位数、最大值等信息
        print(self.data.describe())

    # 数据的运算、排序与删除
    def test3(self):
        # 从已有的列中，通过数据运算创建新的一列
        self.data['总分']=self.data['语文']+self.data['数学']+self.data['英语']
        # 使用sort_values()函数可以对表格按列排序。
        # by参数用于指定按哪一列来排序；ascending参数默认为True，表示升序排序，若设置为False则表示降序排序。
        print(self.data.sort_values(by='语文',ascending=False))
        # 使用drop()函数可以删除表格中的指定数据
        # index用于指定要删除的行；columns用于指定要删除的列；inplace默认为False，表示该删除操作不改变原表格，
        # 而是返回一个执行删除操作后的新表格，如果设置inplace为True，则会直接在原表格中执行删除操作。
        print(self.data.drop(index=['小红'],columns=['英语'],inplace=False))

    # 输出数据
    def printData(self):
        print(self.data)

if __name__ == '__main__':
    handler=Handler()
    # handler.test1()
    # handler.test2()
    handler.test3()
    handler.printData()