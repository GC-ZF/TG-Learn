MOV AH,1    ;AH=1控制台输入,返回值是AL=字符的ASCII
INT 21H     ;调用DOS
AND AL,15   ;与运算,将高位清零,假设输入3D=33H,将高位清零
MOV DH,AL   ;AL存到DH,Note:AL=DL,05行AL的值变为'+'所以要借用DH暂存
MOV DL,'+'  ;DL=要输出的ASCII
MOV AH,2    ;AH=2控制台输出
INT 21H
MOV AH,1
INT 21H     ;为什么第二次没有做ADN？因为如果这里与运算后，最后还要与15,避免重复操作
ADD DH,AL   ;为什么是DH+AL而不是AL+DH,原因和第四行相同,下一行DL的值变为'='
MOV DL,'='
MOV AH,2
INT 21H
MOV DL,DH
INT 21H     ;12行AH=2所以14行不需要重复定义 
HLT         ;停机指令 
;Note:AL=DL!!!