"""
@Author:张时贰
@Date:2022年05月11日
@CSDN:张时贰
@Blog:zhangshier.vip
"""
'''http://t.csdn.cn/DOI0V'''

import socket
import sys

# 服务端主机IP地址和端口号 192.168.43.214
HOST = "192.168.43.214"
PORT = 50007
# 使用IPV4协议，使用tcp协议传输数据
s = socket.socket ( socket.AF_INET, socket.SOCK_STREAM )
try:
    # 连接服务器
    s.connect ( (HOST, PORT) )
except Exception as e:
    print ( '找不到服务器，请稍后重试！' )
    sys.exit ()
while True:
    c = input ( '请输入你想发送的消息：' )
    # 发送数据，使用UTF-8编码成字节码
    s.sendall ( c.encode () )
    # 从服务端接收数据，大小最多为1024比特
    data = s.recv ( 1024 )
    # 解码
    data = data.decode ()
    print ( '收到回复：', data )
    if c.lower () == 'bye':
        break
# 关闭连接
s.close ()
