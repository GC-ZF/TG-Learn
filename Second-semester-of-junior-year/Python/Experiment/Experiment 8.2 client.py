"""
@Author:张时贰
@Date:2022年06月15日
@CSDN:张时贰
@Blog:zhangshier.vip
"""
import socket
clientSocket = socket.socket(socket.AF_INET,socket.SOCK_DGRAM)        #创建套接字对象
questions = ['世界上最伟大的天文学家是谁？','世界上最伟大的发明家是谁？',
 		'世界上最伟大的化学家是谁？']     		                  #问题列表
#遍历发送列表questions中的问题.
for question in questions:
  #发送问题给服务器.
  clientSocket.sendto(bytes(question,encoding='utf8'),('127.0.0.1',12345))
  print("客户端问:" + question) 	            #输出发送的问题
  answer,addr = clientSocket.recvfrom(1024)	#接收服务器回答
  print(str(answer.decode()))   	        #输出回答
clientSocket.close()     	                #关闭套接字
