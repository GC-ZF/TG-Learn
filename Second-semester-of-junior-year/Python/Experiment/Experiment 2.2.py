"""
@Author:张时贰
@Date:2022年05月18日
@CSDN:张时贰
@Blog:zhsher.cn
"""
set1 = {2, 5, 9, 1, 3}
set2 = {3, 6, 8, 2, 5}
set1.add ( 7 )
print ( "添加元素7后的集合为:", set1 )
print ( "集合set1和set2的并集为:", set1 | set2 )
print ( "集合set1和set2的交集为:", set1 & set2 )
print ( "集合set1和set2的差集为:", set1 - set2 )
print ( "关键字key = 4 是否在集合中:", (4 in set1) or (4 in set2) )
