;IN 125,OUT 127指\emu8086\DEVICES\Thermometer.exe，从125读取数据，向127发送数据

MOV AL,1
OUT 127,AL  ;打开加热，然后在LP0循环一直到大于75
   
LP0:
IN AL,125
CMP AL,75
JL LP0      ;<75跳转LP0继续加热
   
MOV CX,120  ;一次1s,两分钟为120次
LP:
IN AL,125   ;125指\emu8086\DEVICES\Thermometer.exe，从125读取数据，向127发送数据
CMP AL,75
JL LOW      ;<75跳转LOW
CMP AL,80
JLE OK      ;<80跳转OK，否则顺序执行HIGH

HIGH:       
MOV AL，0
OUT 127,AL  ;温高关加热
JMP OK

LOW:
MOV AL,1
OUT 127,AL  ;温低开加热
JMP OK

OK:             ;1秒延时判断
PUSH CX         ;压栈保护记录两分钟的CX，因为延时程序也用CX
CALL DELAY5MS   ;75℃到80℃
POP CX
LOOP LP

DELAY5MS PROC NEAR  ;延时，15H中断的86H子功能
    ;wait 1 seconds (1 million microsecpnds)
    ;000F4240H = 1,000,000 = CX,DX 微秒
    MOV CX,15
    MOV DX,4240H
    MOV AH,86H
    INT 15H
    RET
DELAY5MS ENDP