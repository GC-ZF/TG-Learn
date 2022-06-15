"""
@Author:张时贰
@Date:2022年06月15日
@CSDN:张时贰
@Blog:zhangshier.vip
"""
import socket
serverSocket = socket.socket(socket.AF_INET,socket.SOCK_DGRAM)	#创建套接字.
serverSocket.bind(('127.0.0.1',12345))     				#绑定套接字.
print("在12345端口绑定UDP...")
answers = ["伽利略, 哥白尼","爱迪生, 特斯拉","诺贝尔, 门捷列夫"]	#回答列表.
i = 0
while True:
  question,addr = serverSocket.recvfrom(1024)   	#接收客户端信息.
  print("客户端问:",question.decode())	              #输出接收的问题.
  #发送数据给客户端.
  serverSocket.sendto(bytes("服务器答:" + answers[i],encoding='utf8'),addr)
  i = i + 1