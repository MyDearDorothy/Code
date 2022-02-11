# -*-coding:utf-8-*-
# 数据分析与处理实例

import pandas as pd
import tushare as ts
import datetime as dt
from matplotlib import pyplot as plt

class Handler:
    def __init__(self):
        fileName=r"E:\Program\office学习\excel\股票数据.xlsx"
        token = '0e0a1dcd4e4592f14d18ec0c721841872198f89eefed2edcc6e7463e'  # 接口token
        pro = ts.pro_api(token)                                             # 初始化pro接口
        self.data = pro.daily(ts_code="000001.SZ", start_date='20210101', end_date='20211231')
        # 设置index参数为False，表示忽略原来的行索引，运行后会在生成一个名为“股价数据.xlsx”的Excel工作簿。
        self.data.to_excel(fileName,index=False)
        # print(self.data.columns.values)

    def predict(self):
        # 调整日期格式，使得显示更加美观
        self.data['trade_date'] = self.data['trade_date'].apply(lambda x: dt.datetime.strptime(x, '%Y%m%d'))
        plt.plot(self.data['trade_date'],self.data['close'])
        plt.rcParams['font.sans-serif']=['SimHei']  # 用来正常显示中文
        plt.title("平安银行2021年股价走势")
        plt.xlabel("交易日期")
        plt.ylabel("收盘价 ")
        plt.show()


if __name__ == '__main__':
    handler=Handler()
    handler.predict()
