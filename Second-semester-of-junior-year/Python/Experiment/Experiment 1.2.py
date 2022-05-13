"""
@Author:张时贰
@Date:2022年05月13日
@CSDN:张时贰
@Blog:zhangshier.vip
"""
# x = (int ( input ( 'Input a number:' ) ))
# a = x//10000
# b = x%10000//1000
# c = x%1000//100
# d = x%100//10
# e = x%10
# if a != 0:
#     print ( '五位数：' + str ( e ) + str ( d ) + str ( c ) + str ( b ) + str ( a ) )
# elif b != 0:
#     print ( '四位数：' + str ( e ) + str ( d ) + str ( c ) + str ( b ) )
# elif c != 0:
#     print ( '三位数：' + str ( e ) + str ( d ) + str ( c ) )
# elif d != 0:
#     print ( '二位数：' + str ( e ) + str ( d ) )
# else:
#     print ( '个位数：' + str ( e ) )

num = input ( 'Input a number:' )
n=num[::-1] #切片逆序打印
print(n)