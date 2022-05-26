MOV CX,5-1      ;选择排序，外循环N-1次
MOV BX,OFFSET TAB

LP1:
MOV AX,[BX]     ;取当前TAB[i]给AX
PUSH CX         ;保存数据，CX压栈
MOV SI,BX       ;暂存BX给SI

LP2:
ADD SI,2        ;偏移地址+2转向下一个数
CMP AX,[SI]     ;相邻两个数比较
JAE J1          ;如果AX>[SI]跳转
XCHG AX,[SI]    ;如果AX<[SI]交换，最后一次内循环找到最大数AX

J1:
LOOP LP2        ;继续回到LP2内循环
MOV [BX],AX     ;找到的最大数给[BX]
POP CX
ADD BX,2
LOOP LP1        ;回到外层循环

HLT

TAB DW 9,3,6,4,2   ;段结构使用数据变量放在后面，定义五个数