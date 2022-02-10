# -*-coding:utf-8-*-
# 数据分析与处理
import numpy as np
import pandas as pd

class Handler:
    def __init__(self):
        self.path=r"E:\Program\office学习\excel\handler.xlsx"

    def writeInto(self):
        list1=[[1,2],[2,4],[3,6]]
        index=['中国','美国','俄罗斯']
        columns = ['2020', '2021']
        data=pd.DataFrame(list1,index=index,columns=columns)
        # sheet_name用于指定工作表名称
        data.to_excel(self.path,sheet_name="Country")

    def readFrom(self):
        # xlrd更新到了2.0.1版本，只支持.xls文件,可以用openpyxl代替xlrd打开.xlsx文件
        data=pd.read_excel(self.path,sheet_name=0,engine="openpyxl")
        print(data)

if __name__ == '__main__':
    handler=Handler()
    handler.writeInto()
    handler.readFrom()