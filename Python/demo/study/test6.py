# -*-coding:utf-8-*-
# 数据分析与处理
import matplotlib.pyplot as plt
import numpy as np
import pandas as pd


# Pyplot 是 Matplotlib 的子库， 是常用的绘图模块，能很方便让用户绘制 2D 图表。
# plot() 函数是绘制二维图形的最基本函数。
def test1():
    ypoints = np.array([1,3,4,5,8,9,6,1,3,4,5,2,4 ])  # 根据列表创建数组
    # 列表和数组有着相同的索引切片机制，唯一的区别就是数组中的元素通过空格分隔，而列表中的元素通过逗号分隔。
    # plot([x], y, [fmt], **kwargs)
    plt.plot(ypoints,marker='o',c='red')       # fmt = '[marker][linestyle][color]' ，其中分别定义标记、线条样式、颜色。
    # xlabel()和ylabel()方法来设置x轴和y轴的标签、title()方法来设置标题。
    # 这三个方法都有loc参数来设置显示的位置，默认值为'center'。
    plt.title("Title")
    plt.xlabel("x - label",loc="right")
    plt.ylabel("y - label",loc="top")
    plt.show()

# 列表和数组的区别于联系
def test2():
    a=[[1,2],[3,4],[5,6],[7,8]]
    b=np.array(a)     # 创建二维数组
    c=[1,2,3,4]
    d=np.array(c)     # 创建一维数组
    # 列表a虽然包含4个小列表，但其结构是一维的。而数组b则是4行2列的二维结构，这也是之后学习pandas库的核心内容，
    # 因为数据处理中经常用到二维数组，即二维的表格结构。
    print("a的类型为：",type(a))
    print(a)
    print("b的类型为：",type(b))
    print(b)

def createArray():
    print("一维数组的创建方法：")
    a = np.array([1,2,3,4,5])   # 创建一维数组
    b=np.arange(1,10,2)         # 前两个参数起始位置，左闭右开，第三个参数步长
    c=np.random.random(5)       # 使用np.random中的函数可以创建随机一维数组
    print(a)
    print(b)
    print(c)
    print("二维数组的创建方法：")
    d=np.array([[1,2,3],[2,4,6]])    # 创建二维数组
    e=np.arange(1,9).reshape(2,4)   # 可以利用创建一维数组的np.arange()函数和reshape()函数来创建二维数组。
    # np.random.randint()函数用于创建随机整数，括号里第1个参数为起始数，第2个参数为终止数，第3个参数（3,2）则表示创建一个3行2列的二维数组
    f=np.random.randint(1,10,(3,2))
    print(d)
    print(e)
    print(f)

def test3():
    print("1．通过列表创建DataFrame：")
    a= pd.DataFrame()
    A=[1,3,5,7]
    B=[2,4,6,8]
    a['A']=A
    a['B']=B
    # 用pandas库的DataFrame()函数创建的DataFrame更像Excel中的二维表格数据，它也有行索引和列索引。
    # 需要注意的是，这里的索引序号是从0开始的。
    print(a)
    b=pd.DataFrame([[1,2,3],[2,4,6],[3,6,9]],index=['A','B','C'],columns=['score1','score2','score3'])
    print(b)
    print(b.T)  # 转置表格
    print("表格b的行索引为：",b.index.values)
    print("表格b的列索引为：",b.columns.values)
    # 设置inplace参数为True来实现真正的重命名
    b.rename(index={'A':'row'},columns={'score1':'分数'},inplace=True)
    print("表格b的行索引为：", b.index.values)
    print("表格b的列索引为：", b.columns.values)
    print("2．通过字典创建DataFrame：")
    # orient参数用于指定字典键为列索引还是行索引，默认值为columns，即默认字典键为列索引。
    c=pd.DataFrame.from_dict({'A':[1,2,3],'B':[2,4,6]},orient="index")
    print(c)
    print("3．通过二维数组创建DataFrame：")
    # 在NumPy库创建的二维数组的基础上，也可以创建DataFrame
    d=pd.DataFrame(np.arange(1,9).reshape(2,4),columns=['A','B','C','D'])
    print(d)

if __name__ == '__main__':
    # test2()
    # createArray()
    test3()