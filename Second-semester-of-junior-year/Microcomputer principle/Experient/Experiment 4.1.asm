;AX+BX
CALL P  ;跳转P
MOV AX,1
MOV BX,2
INT 40H     ;跳转中断服务程序
HLT ;JMP $

;子程序P 修改中断向量
P PROC NEAR
    MOV AX,0
    MOV ES,AX       ;段地址
    MOV DI,40H*4    ;偏移地址
    MOV AX,OFFSET INT40H
    MOV ES:[DI],AX
    MOV AX,CS
    MOV ES:[DI+2],AX
    RET
P ENDP

;中断服务子程序
INT40H PROC FAR
    ADD AX,BX
    IRET
INT40H ENDP    