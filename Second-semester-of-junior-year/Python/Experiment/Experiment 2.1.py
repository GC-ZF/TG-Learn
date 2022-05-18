"""
@Author:张时贰
@Date:2022年05月18日
@CSDN:张时贰
@Blog:zhangshier.vip
"""
leaders_1 = [ 1, 2 ]
leaders_2 = [ 3, 4 ]
full_leaders_list = leaders_1 + leaders_2
print ( full_leaders_list )

leaders_1.extend ( leaders_2 )
print ( leaders_1 )
