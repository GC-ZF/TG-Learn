"""
@Author:张时贰
@Date:2022年05月13日
@CSDN:张时贰
@Blog:zhangshier.vip
"""
x = input ( "please enter three number:(中间用空格隔开)" )
a, b, c = x.split ( " " )
if a < b:
    a,b=b,a
if a < c:
    a,c=c,a
if b < c:
    b,c=c,b
print ( a, b, c )
