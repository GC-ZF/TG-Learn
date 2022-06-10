"""
@Author:张时贰
@Date:2022年06月08日
@CSDN:张时贰
@Blog:zhangshier.vip
"""
'''先运行服务端代码，然后运行客户端'''

import socket
from os.path import commonprefix

# 建立聊天回复字典
words = {'What is your name': 'Zhangshier',
         'how are you?': 'Fine,thank you!',
         'how old are you?': '18 forever',
         'bye': 'Bye!'}
# 服务端主机IP地址和端口号，空字符串表示本机任何可用IP地址
HOST = "192.168.43.214"
PORT = 50007
# 使用IPV4协议，使用tcp协议传输数据
s = socket.socket ( socket.AF_INET, socket.SOCK_STREAM )
# 绑定端口和端口号
s.bind ( (HOST, PORT) )
# 开始监听，规定最多支持1个客户端连接
s.listen ( 1 )
print ( '目前监听的端口号是：', PORT )
conn, addr = s.accept ()
print ( '目前连接的IP地址是：', addr )
# 开始聊天
while True:
    # 最多可以接收1024比特大小的内容,并解码
    data = conn.recv ( 1024 ).decode ()
    # 如果是空，退出
    if not data:
        break
    print ( '接收到的内容：', data )
    # 尽可能猜测对方的意思
    m = 0
    key = ''
    for k in words.keys ():
        # 删除多余的空白字符
        data = ' '.join ( data.split () )
        # 与某个键非常接近，就直接返回
        if len ( commonprefix ( [ k, data ] ) ) > len ( k )*0.7:
            key = k
            break
        # 使用选择法，选择一个重合度较高的键
        length = len ( set ( data.split () ) & set ( k.split () ) )
        if length > m:
            m = length
            key = k
    # 选择合适的信息进行回复
    conn.sendall ( words.get ( key, 'Sorry,can\'t find your problem! ' ).encode () )
conn.close ()