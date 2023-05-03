"""
@Author:张时贰
@Date:2022年06月01日
@CSDN:张时贰
@Blog:zhsher.cn
"""
# 可以输出结果即可，红色报错是库的问题
import matplotlib.pyplot as plt
import numpy as np
x = np.arange(1,50)
plt.plot(x,2*x+1,'red',lw=2)
plt.plot(x,x**2,'b',linestyle='dashed',lw=2)
plt.legend(['2*x+1', 'x**2'])     	         #设置图例
plt.show()
