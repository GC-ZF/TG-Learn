"""
@Author:张时贰
@Date:2022年06月08日
@CSDN:张时贰
@Blog:zhangshier.vip
"""
import csv


# 通过input()函数向文件中写入学生相关信息，格式为“姓名，性别，年龄，语文成绩，数学成绩，英语成绩”，当输入“-1”时结束输入
def Input():
    headers = [ 'Name', 'Sex', 'Age', 'chNum', 'maNum', 'egNum' ]
    list1 = [ ('李四', '男', 21, 80, 80, 80), ('王五', '男', 22, 95, 95, 95), ('张时叁', '女', 22, 85, 85, 85) ]

    tu = ()
    n = None
    while (n != '-1'):
        t1 = str ( input ( "输入名字：" ) )
        t2 = str ( input ( "输入性别：" ) )
        t3 = int ( input ( "输入年龄：" ) )
        t4 = float ( input ( "输入语文分数：" ) )
        t5 = float ( input ( "输入数学分数：" ) )
        t6 = float ( input ( "输入英语分数：" ) )
        tu = (t1, t2, t3, t4, t5, t6)
        list1.append ( tu )
        n = input ( '任意键回车继续，输入 -1 开始写入：' )

    try:
        with open ( "grade.csv", "w", encoding='ANSI', newline='' ) as file:
            fw = csv.writer ( file )
            fw.writerow ( headers )
            fw.writerows ( list1 )
            print ( "将文件写入grade.csv成功" )
    except Exception as ex:
        print ( ex )
        print ( "将文件写入grade.csv失败" )


# 统计所有学生的总成绩、排序，并写入新文件statistics.csv中
def Count():
    ch = [ ]
    ma = [ ]
    chn = 0
    man = 0
    list1 = [ ]
    try:
        with open ( "grade.csv", "r", encoding='ANSI', newline='' ) as file:
            fr = csv.reader ( file )
            list1 = [ li for li in fr ]

            print ( "读取文件grade.csv成功" )
    except Exception as ex:
        print ( ex )
        print ( "读取grade.csv失败" )
    try:
        with open ( "statistics.csv", "w", encoding='ANSI', newline='' ) as file:
            fw = csv.writer ( file )
            list1[ 0 ].append ( '总成绩' )
            fw.writerow ( list1[ 0 ] )
            # 按总成绩从小到大排名
            for x in range ( 1, len ( list1 ) ):
                list1[ x ].append ( float ( list1[ x ][ 3 ] ) + float ( list1[ x ][ 4 ] ) + float ( list1[ x ][ 5 ] ) )
                print ( list1[ x ] )
            list1 = sorted ( list1[ 1: ], key=lambda x: float ( x[ 6 ] ) )

            fw.writerows ( list1 )
            print ( "写入statisticx.csv成功" )

    except Exception as ex:
        print ( ex )
        print ( "写入statisticx.csv失败" )


Input ()
Count ()
