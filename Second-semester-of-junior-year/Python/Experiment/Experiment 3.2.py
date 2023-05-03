"""
@Author:张时贰
@Date:2022年05月28日
@CSDN:张时贰
@Blog:zhsher.cn
"""
#判断是否为素数
def isPrime(n):
    i=2
    while i<=n:
        if n%i==0:
            break
        i+=1
    if n==i:
        return True
num=int(input("输入偶数，6~100之间\n"))
if num<6:
    print("请输入大于6的偶数！")
    exit(0)
elif num>100:
    print("请输入小于100的偶数！")
    exit(0)
elif (num%2)>0:
     print("请输入偶数！")
     exit(0)
i=1
result = []
def isGDBH(n):
    global  i
    while(i<=num):
     i = i + 1
     if(isPrime(i)):
         j=1
         while(j<num):
          j = j + 1
          if(isPrime(j) and i<=j): #j<i防止重复
           if(j+i==num):
               elem = str(num)+'='+str(i)+'+'+str(j)
               result.append(elem)
isGDBH(num)
print(result)
