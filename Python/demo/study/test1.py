# -*- coding:utf-8 -*-
# 日期时间模块
import calendar
import time

def testDate():
    # 格式化成2000-01-01 00:00:00形式
    print("现在是:",time.strftime("%Y-%m-%d %H:%M:%S", time.localtime()))

    # 格式化成Sat Mar 01 00:00:00 2000形式
    print("现在是:",time.strftime("%a %b %d %H:%M:%S %Y", time.localtime()))
    cal = calendar.month(2022, 1)
    print("2022年1月份的日历:")
    print(cal)

if __name__ == '__main__':
    testDate()