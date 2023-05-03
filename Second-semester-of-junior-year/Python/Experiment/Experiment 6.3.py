"""
@Author:张时贰
@Date:2022年06月08日
@CSDN:张时贰
@Blog:zhsher.cn
"""
import pickle

num = 1  # 数字
string1 = "zhsher.vip"  # 字符串
list1 = [ 1.25, 21.06, 0.3, 4.7, 58.1 ]  # 列表
tuple1 = (1, 8, 27, 64, 125)  # 元组
dict1 = dict ( name="Mary", height=165, weight=51 )  # 字典
set1 = {1, 4, 9, 16, 25}  # 集合

data = [ string1, list1, tuple1, dict1, set1 ]  # 数据

with open ( "pickle_file.dat", "wb" ) as pickle_file:  # 打开的二进制文件
    for i in data:
        pickle.dump ( i, pickle_file )  # 向文件中写入序列化内容
    print ( "写入数据成功！" )

with open ( "pickle_file.dat", "rb" ) as pickle_file:
    while 1:
        try:
            y = pickle.load ( pickle_file )
            print ( y )
        except EOFError:
            break
