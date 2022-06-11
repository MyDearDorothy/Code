# -*- coding:utf-8 -*-
import csv

import pdfplumber
import pandas as pd
import PyPDF2
import re
class HandlerPDF:
    def __init__(self):
        self.readPath=r"D:\Download\南玻Ａ：2019年半年度报告.PDF"
        self.name=""
        self.savePath=r"E:\Program\Python爬虫\递延收益.csv"

# 定义函数，计算PDF页数
    def getPages(self):
        with open(self.readPath,'rb') as fb:
            pages=PyPDF2.PdfFileReader(fb).getNumPages()
        return pages

    def getContent(self,pages):
        with pdfplumber.open(self.readPath) as pdf:
            for i in range(0,pages-1):
                find1=re.findall("、递延收益",pdf.pages[i].extract_text())
                find2=re.findall("、其他收益",pdf.pages[i].extract_text())
                find3=re.findall("、营业外收入",pdf.pages[i].extract_text())
                if find1:
                    print("递延收益找到了，在第",i)
                    tables=pdf.pages[i].extract_tables()
                    for j in range(len(tables)):
                        for row in tables[j]:
                            if row==['项目','期初余额','本期增加','本期减少','期末余额','形成原因']:
                                print(j)
                    tables_next=pdf.pages[i+1].extract_tables()
                    for row in tables_next[0]:
                        print(row)
                if find2:
                    print("其他收益找到了在第",i)
                if find3:
                    print("营业外收入找到了在第", i)

    def save(self):
        with open(self.savePath,'a',encoding="utf-8") as f:
            write=csv.writer(f)
            write.writerow()

if __name__ == "__main__":
    handler=HandlerPDF()
    handler.getContent(handler.getPages())

