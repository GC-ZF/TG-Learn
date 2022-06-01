"""
@Author:张时贰
@Date:2022年06月01日
@CSDN:张时贰
@Blog:zhangshier.vip
"""
import numpy as np
a= np.random.randint(1,100,(3,4))    #1~100 三行四列数组
print(a)
sum=0
for i in range(3):
    for j in range(4):
        sum+=a[i][j]
average=sum/12
print("平均数为: %.2f" %average)


