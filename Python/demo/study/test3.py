# -*- coding:utf-8 -*-
# 文件操作
import os


class fileHandler:

    def __init__(self):
        self.path= "../file/Readme.txt"

    # 注意，使用open()函数打开的文件对象，必须手动进行关闭（后续章节会详细讲解），Python垃圾回收机制无法自动回收打开文件所占用的资源。
    def openFile(self):
        with open(self.path,"r",encoding="utf-8") as f:
            for line in f:
                print(line,end="")

if __name__ == "__main__":
    filehandler=fileHandler()
    filehandler.openFile()
