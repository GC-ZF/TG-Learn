"""
@Author:张时贰
@Date:2022年05月27日
@CSDN:张时贰
@Blog:zhsher.cn
"""
import abc
import math


# 抽象类，用abc库，只需要声明函数，不需要写具体功能，不能够实例化.类似C+纯虚函数
class Shape ( metaclass=abc.ABCMeta ):
    # 面积
    @abc.abstractmethod
    def getArea(self):
        pass

    # 周长
    @abc.abstractmethod
    def getPerimeter(self):
        pass


# 三角形
class Triangle ( Shape ):
    def __init__(self, a, b, c):
        self.a = a
        self.b = b
        self.c = c

    def getArea(self):
        return 0.25*math.sqrt ( (self.a + self.b + self.c)*(self.a + self.b - self.c)*(self.b + self.c - self.a)*(
                self.a + self.c - self.b) )

    def getPerimeter(self):
        return self.a + self.b + self.c

    # 判断三边关系
    def judgeInput(self):
        a1 = self.a + self.b - self.c
        a2 = (self.a - self.b) - self.c
        b1 = self.b + self.c - self.a
        b2 = (self.b - self.c) - self.a
        c1 = self.a + self.c - self.b
        c2 = (self.a - self.c) - self.b
        if (a1 > 0 and a2 < 0) and (b1 > 0 and b2 < 0) and (c1 > 0 and c2 < 0):
            return True
        else:
            return False


# 矩形
class Rectangle ( Shape ):
    def __init__(self, a, b):
        self.a = a
        self.b = b

    def getArea(self):
        return self.a*self.b

    def getPerimeter(self):
        return (self.a + self.b)*2


# 圆
class Circle ( Shape ):
    def __init__(self, r):
        self.r = r

    def getArea(self):
        return 3.14*self.r ** 2

    def getPerimeter(self):
        return 2*math.pi*self.r


if __name__ == '__main__':
    a1, a2, a3 = map ( int, input ( "请输入三角形三边:" ).split ( " " ) )
    t1 = Triangle ( a1, a2, a3 )
    if t1.judgeInput ():
        print ( f"三角形面积={t1.getArea ()}" )
        print ( f"三角形周长={t1.getPerimeter ()}" )
    else:
        print ( "输入的三边长不能构成三角形" )
    b1, b2 = map ( int, input ( "请输入矩形两边:" ).split ( " " ) )
    r1 = Rectangle ( b1, b2 )
    print ( f"矩形面积={r1.getArea ()}" )
    print ( f"矩形周长={r1.getPerimeter ()}" )
    r1 = int ( input ( "请输入圆的半径r1:" ) )
    c1 = Circle ( r1 )
    print ( "圆面积%.2f"%c1.getArea () )
    print ( "圆周长%.2f"%c1.getPerimeter () )
