"""
@Author:张时贰
@Date:2022年06月10日
@CSDN:张时贰
@Blog:zhsher.cn
"""
'''http://t.csdn.cn/zMGau'''


class List_Queue:
    # 初始化
    def __init__(self):
        self.list1 = [ ]
        print ( '初始化成功!' )

    # 入队
    def enqueue(self, item):
        self.list1.append ( item )
        print ( '添加成功!' )

    # 出队
    def dequeue(self):
        if len ( self.list1 ) > 0:
            print ( "出队列数据:", self.list1[ 0 ] )
            self.list1.pop ( 0 )
        else:
            raise List_Queue_Exception ()

    # 返回队列长度
    def lenqueue(self):
        return len ( self.list1 )

    # 输出队列
    def l_queue(self):
        print ( self.list1 )


class List_Queue_Exception ( BaseException ):
    def __init__(self):
        print ( "列表为空!" )


if __name__ == '__main__':
    list_queue = List_Queue ()
    print ( "-----------------" )
    print ( "*****1,入队*****" )
    print ( "*****2,出队*****" )
    print ( "*****3,队列长度*" )
    print ( "*****4,显示列表*" )
    print ( "*****0,退出*" )
    print ( "-----------------" )
    while True:

        x = int ( input ( "输入序号:" ) )
        try:
            if x in [ 0, 1, 2, 3, 4 ]:

                if x == 0:
                    print ( "已经退出" )
                    break;
                elif x == 1:
                    y = input ( "请输入输入的数据:" )
                    list_queue.enqueue ( y )
                elif x == 2:
                    list_queue.dequeue ()
                elif x == 3:
                    print ( "队列长度为:", list_queue.lenqueue () )
                elif x == 4:
                    list_queue.l_queue ()

            else:
                print ( "输入有误!" )
        except BaseException as ex:
            print ( ex )
