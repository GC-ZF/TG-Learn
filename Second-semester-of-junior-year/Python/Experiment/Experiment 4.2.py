"""
@Author:张时贰
@Date:2022年05月27日
@CSDN:张时贰
@Blog:zhangshier.vip
"""


# 定义库存类
class Goods:
    # 有参构造初始化
    def __init__(self, name, num, cin, cout):
        self.name = name
        self.num = num
        self.cin = cin
        self.cout = cout

    def __str__(self):
        state = "已售罄"
        if self.num == 0:
            return '名称：%s , 数量：%d %s, 进货价格：%.2f ,售出价格：%.2f '%(self.name, self.num, state, self.cin, self.cout)
        else:
            return '名称：%s , 数量：%d , 进货价格：%.2f ,售出价格：%.2f '%(self.name, self.num, self.cin, self.cout)


# 已售出类
class gGoods:
    def __init__(self, name, gnum, gcin, gcout):
        self.name = name
        self.gnum = gnum
        self.gcin = gcin
        self.gcout = gcout

    def __str__(self):
        return '名称：%s , 卖出数量：%d , 进货价格：%.2f ,卖出价格：%.2f '%(self.name, self.gnum, self.gcin, self.gcout)


# 定义管理商品类
class GoodsManager:
    go = [ ]  # 库存
    js = [ ]  # 售出

    # 构造方法，初始化加三个商品
    def init(self):
        self.go.append ( Goods ( '牛奶', 5, 40, 60 ) )
        self.go.append ( Goods ( '盒饭', 5, 10, 60 ) )
        self.js.append ( gGoods ( '面条', 1, 30, 60 ) )

    # 菜单
    def Menu(self):
        self.init ()
        print ( '\"超市进销存管理系统\"菜单：' )
        print ( "1.显示所有商品" )
        print ( "2.添加新的商品" )
        print ( "3.修改商品信息" )
        print ( "4.删除商品" )
        print ( "5.卖出商品" )
        print ( "6.汇总" )
        print ( "-1.退出" )
        print ( "***********************************" )
        while True:
            SN = int ( input ( "请输入操作序号：" ) )
            if SN in [ -1, 1, 2, 3, 4, 5, 6 ]:
                if SN == -1:
                    print ( "已经退出" )
                    break;
                if SN == 1:
                    self.Show_all ()
                elif SN == 2:
                    self.Add ()
                elif SN == 3:
                    self.Modify ()
                elif SN == 4:
                    self.Delete ()
                elif SN == 5:
                    self.Shop ()
                elif SN == 6:
                    self.Summary ()
            else:
                print ( "输入有误！" )

    # 显示
    def Show_all(self):
        for goods in self.go:
            print ( str ( goods ) )

    # 添加
    def Add(self):
        goods_name = input ( "请输入商品名称：" )
        ret = self.check ( goods_name )
        if ret != None:
            print ( '商品已经存在' )
            print ( '是否增加商品数量：（y/n）' )
            while True:
                pd = input ()
                if pd == 'y':
                    goods_num = int ( input ( "请输入商品的数量：" ) )
                    old_goods = Goods ( goods_name, goods_num + ret.num, ret.cin, ret.cout )
                    self.go.remove ( ret )
                    self.go.append ( old_goods )
                    print ( "增加成功" )
                    break
                elif pd == 'n':
                    print ( "已经返回" )
                    break
                else:
                    print ( "输入有误，重新输入：" )

        else:
            goods_num = int ( input ( "请输入商品的数量：" ) )
            goods_cin = float ( input ( "请输入商品进货价格：" ) )
            goods_cout = float ( input ( "请输入商品出货价格：" ) )
            if goods_num > 0 and goods_cin > 0 and goods_cout > 0:
                new_goods = Goods ( goods_name, goods_num, goods_cin, goods_cout )
                self.go.append ( new_goods )
                print ( "添加成功" )
            else:
                print ( "输入错误！" )

    # 修改
    def Modify(self):
        goods_name = input ( "请输入需要修改的商品名称：" )
        ret = self.check ( goods_name )
        if ret != None:
            print ( ret )
            goods_name1 = input ( "请输入修改后商品的名称：" )
            goods_num = int ( input ( "请输入修改后商品的数量：" ) )
            goods_cin = float ( input ( "请输入修改后商品进货价格：" ) )
            goods_cout = float ( input ( "请输入修改后商品出货价格：" ) )
            old_goods = Goods ( goods_name1, goods_num, goods_cin, goods_cout )
            self.go.remove ( ret )
            self.go.append ( old_goods )
            print ( "修改成功" )
        else:
            print ( "没有此商品!" )

    # 检查，修改删除卖出之前先调用检查是否存在商品
    def check(self, goods_name):
        for goods in self.go:
            if goods.name == goods_name:
                return goods
        else:
            return None

    def checkjs(self, goods_name):
        for goods in self.js:
            if goods.name == goods_name:
                return goods
        else:
            return None

    # 删除
    def Delete(self):
        goods_name = input ( "请输入需要删除的商品名称：" )
        ret = self.check ( goods_name )
        if ret != None:
            print ( ret )
            print ( '是否删除商品：（y/n）' )
            while True:

                pd = input ()
                if pd == 'y':
                    self.go.remove ( ret )
                    print ( "删除成功" )
                    break
                elif pd == 'n':
                    print ( "已经返回" )
                    break
                else:
                    print ( "输入有误，重新输入：" )
        else:
            print ( "没有此商品！" )

    # 卖出
    def Shop(self):
        goods_name = input ( "请输入需要卖出的商品名称：" )
        ret = self.check ( goods_name )
        if ret != None:
            g_num = int ( input ( "卖出个数:" ) )
            if ret.num - g_num < 0:
                print ( "该商品数量不足！请补充" )
            else:
                old_goods = Goods ( ret.name, ret.num - g_num, ret.cin, ret.cout )
                self.go.remove ( ret )
                self.go.append ( old_goods )
                gret = self.checkjs ( goods_name )
                if gret == None:
                    shop_goods = gGoods ( ret.name, g_num, ret.cin*g_num, ret.cout*g_num )
                    self.js.append ( shop_goods )
                else:
                    shop_goods = gGoods ( gret.name, g_num + gret.gnum, gret.gcin + ret.cin*g_num,
                                          gret.gcout + ret.cout*g_num )
                    self.js.remove ( gret )
                    self.js.append ( shop_goods )
                print ( "卖出后：", end=' ' )
                old_goods = Goods ( ret.name, ret.num - g_num, ret.cin*g_num, ret.cout*g_num )
                print ( old_goods )
        else:
            print ( "没有此商品！" )

    # 汇总当天卖出商品，包括每种销售商品名称、数量、进货总价、销售总价等。
    def Summary(self):

        for goods in self.js:
            print ( goods )
        print ( "售出的物品进货总价：", end="" )
        x = 0
        for goods in self.js:
            x += float ( goods.gcin )

        print ( "售出的物品销售总价：", end="" )
        y = 0
        for goods in self.js:
            y += float ( goods.gcout )
        print ( y )
        print ( "利润：", y - x )


if __name__ == '__main__':
    manager = GoodsManager ()
    manager.Menu ()
