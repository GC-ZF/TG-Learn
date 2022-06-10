"""
@Author:张时贰
@Date:2022年06月10日
@CSDN:张时贰
@Blog:zhangshier.vip
"""
'''https://blog.csdn.net/weixin_45168704/article/details/109820049'''
# （1）定义一个实现算术运算的类Arithmetic_Operation，可以实现两个整数的加法、减法、乘法和除法运算。
# （2）定义一个测试类Test_Arithmetic_Operation对Arithmetic_Operation中的功能进行测试。
import unittest


class Arithmatic_Operation:
    def add(self):
        return self.x + self.y

    def sub(self):
        return self.x - self.y

    def mul(self):
        return self.x*self.y

    def div(self):
        return self.x/self.y

    def __init__(self, x, y):
        self.x = x
        self.y = y


class Test_Arithmetic_Operation ( unittest.TestCase ):
    def setUp(self):
        self.op = Arithmatic_Operation ( 3, 5 )

    def test_add(self):
        if self.assertEqual ( self.op.add (), 8 ):
            print ( "正确" )

    def test_sub(self):
        self.assertEqual ( self.op.sub (), -2 )

    def test_mul(self):
        self.assertEqual ( self.op.mul (), 15 )

    def test_div(self):
        self.assertEqual ( self.op.div (), 0.6 )


if __name__ == '__main__':
    unittest.main ()
