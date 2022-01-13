# -*- coding:utf-8 -*-
# MySQL数据库操作模块
import mysql.connector


class DBHandler:
    def __init__(self):
        self.db=mysql.connector.Connect(host="localhost",
                                        user="root",
                                        password="12345",
                                        database="test",
                                        auth_plugin="mysql_native_password")
        self.cursor=self.db.cursor()

    def insert(self,sql,val):
        try:
            self.cursor.execute(sql,val)
            self.db.commit()
        except:
            print("记录插入失败！")
        else:
            print("记录插入成功！")

    def delete(self,sql):
        try:
            self.cursor.execute(sql)
            self.db.commit()
        except:
            print("记录删除失败！")
        else:
            print("记录删除成功！")

    def update(self,sql,val):
        self.cursor.execute(sql, val)
        self.db.commit()

    def query(self,sql):
        self.cursor.execute(sql)
        result=self.cursor.fetchall()
        for record in result:
            print(record)

    def close(self):
        self.cursor.close()
        self.db.close


if __name__ == '__main__':
    dbHandler=DBHandler();
    query_all="select * from user_Infor"
    userName = input("Input data you want to insert:\n用户名：")
    userPassword = input("密码：")
    userSex = input("性别：")
    userAge = input("年龄：")
    insertSql="insert into user_Infor(user_name,user_password,sex,age) values (%s,%s,%s,%s)"
    insertVal=(userName,userPassword,userSex,userAge)
    dbHandler.insert(insertSql,insertVal)
    dbHandler.query(query_all)