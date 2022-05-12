BGN:
MOV AH,1
INT 21H     ;调用DOS 1号输入功能
AND AL,15   ;与运算,将高位清零,假设输入3D=33H,将高位清零
MOV DH,AL   ;第一个数暂存到DH
MOV AH,1
INT 21H     ;调用DOS 1号输入第二个数
AND AL,15   ;高位清零
MOV AH,DH   ;第一个数做为高位给AH，第二个数在AL不变
AAD         ;AAD指令 AL=AH*0AH+AL AH=0.实质上是将AX寄存器中非压缩型BCD码转换成为真正的二进制数，并存放在AL寄存器中
CMP AL,N    ;若大于CF=0，小于CF=1，等于CF=1.ZF=1
JE JEND     ;零标志位ZF=1转移
MOV DL,'>'
JA J1       ;高于或者不低于转移,CF=0
MOV DL,'<' 

J1:    
MOV AH,2 
INT 21H     ;调用2号输出>
MOV DL,0AH
INT 21H     ;调用2号换行
MOV DL,0DH
INT 21H     ;调用2号回车
JMP BGN     ;猜的结果大/小，继续返回BGN

JEND:
MOV DL,'='
MOV AH,2
INT 21H 
MOV AH,4CH
INT 21H     ;调用DOC 4CH中断程序

N DB 35      ;赋初值，猜的数为十进制35