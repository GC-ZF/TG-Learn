"""
@Author:张时贰
@Date:2022年03月26日
CSDN:张时贰
"""
import math
# 1.判断一个数是否是素数并输出
# num=int(input("请输入一个数"))
# if (num == 1):
#     print ("不是素数")
# else:
#     k=int(math.sqrt(num))   #假设num=a*b,开根号a=b,如果在小于a的时候还有约数，就不考虑b
#     for j in range(2,k+1):
#         if num%j == 0:
#             print ("不是素数")
#             exit()
#     print ("是素数")

# 2.输入两个正整数，找出最大公约数并输出
# num1=int(input("请输入第一个数"))
# num2=int(input("请输入第二个数"))
# #判断大小
# if num2>num1:
#     num1=num2
#     num2=num1
# if num1%num2==0:
#     num3 = num2
#     print ("%d和%d的最大公约数是%d"%(num1, num2,num3))
# else:
#     while num1%num2 != 0:
#         num3 = num1%num2
#         num1 = num2
#         num2 = num3
#     print ("%d和%d的最大公约数是%d"%(num1, num2, num3))

# 3.计算出Π的近似值︰Π/4≈1-1/3+1/5-1/7+1/9-......(计算到最后一项的值小于10-6为止)
# num=1
# i=1
# sum=0
# while True:
#     if 1/num>0.0000001:
#         sum = sum + (1/num)*i
#         num = num + 2
#         i = i*-1
#     else:
#         break
# print ("Π/4=", sum)

# 4.打印九九乘法表
# for i in range(1,10):
#     for j in range(1,i+1):
#         print(j,"*",i,"=",j*i,end="\t")
#     print()

# 5.找出所有的水仙花数（水仙花数为一个三位数,并且每一位上的数字的立方和等于该数)
# for i in range(100,1000):
#     a = i//100%10     # 计算百位数字
#     b = i//10%10      # 计算十位数字
#     c = i%10          # 计算个位数字
#     if a*a*a+b*b*b+c*c*c==i:
#         print(i,end=" ")

# 6.找出100以内的完全数(比如6=1+2+3，6就是一个完全数，一个数刚好等于它的因子和)
# for i in range(1,101):
#     sum=0
#     for j in range(1,i):
#         if i%j==0:
#             sum=sum+j
#     if sum==i:
#         print(i,end=" ")
