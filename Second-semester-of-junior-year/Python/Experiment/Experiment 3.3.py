"""
@Author:张时贰
@Date:2022年05月28日
@CSDN:张时贰
@Blog:zhsher.cn
"""
import math
#定义带参数的装饰器
def DECO(Shape):
  #定义内部装饰器
  def deco(func):
    #定义内函数
    def call_func(a,b=0,c=0):
      if(Shape=='三角形'):
          # print('测试')
          if ((a + b > c) & (a + c > b) & (b + c > a)):
            return func ( a, b, c )
          else:
              return '输入无效'
      if(Shape=='矩形'):
          if((a>0)&(b>0)):
            return func(a,b)
          else:
              return '输入无效'
      if(Shape=='圆形'):
          if(a>0):
            return func(a)
          else:
              return '输入无效'
    return call_func
  return deco
#传递装饰器参数
@DECO('三角形')
def Triangle(a,b,c):
  return a+b+c

@DECO('矩形')
def Cube(a,b):
    return (a+b)*2

@DECO('圆形')
def Circle(a):
    return 2*math.pi*a

if __name__ == "__main__":
    a,b,c=map(int,input('输入三角形三边').split(" "))
    print(Triangle(a,b,c))
    a,b=map(int,input('输入矩形两边').split(" "))
    print(Cube(a,b))
    a=int(input('输入圆半径'))
    print(Circle(a))