"""
@Author:张时贰
@Date:2022年05月28日
@CSDN:张时贰
@Blog:zhsher.cn
"""
content = input ( '输入字符串' )
def func(s):
    num = 0
    alpha = 0
    space = 0
    others = 0
    dic = {
        'num': 0,
        'alpha': 0,
        'space': 0,
        'other': 0
    }
    for i in s:
        if i.isdigit ():
            dic[ 'num' ] += 1
        elif i.isalpha ():
            dic[ 'alpha' ] += 1
        elif i.isspace ():
            dic[ 'space' ] += 1
        else:
            dic[ 'other' ] += 1
    return dic
print ( func ( content ) )