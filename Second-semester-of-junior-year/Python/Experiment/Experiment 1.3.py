"""
@Author:张时贰
@Date:2022年05月13日
@CSDN:张时贰
@Blog:zhangshier.vip
"""
year = int ( input ( '请输⼊年份:' ) )
month = int ( input ( '请输⼊⽉份:' ) )
if month == 1 or month == 3 or month == 5 or month == 7 or month == 8 or month == 10 or month == 12:
    print ( '该⽉有31天' )
elif month == 4 or month == 6 or month == 9 or month == 11:
    print ( '该⽉有30天' )
elif month == 2 and (year%400 == 0 or (year%4 == 0 and year%100 != 0)):
    print ( '该⽉有29天' )
else:
    print ( '该⽉有28天' )