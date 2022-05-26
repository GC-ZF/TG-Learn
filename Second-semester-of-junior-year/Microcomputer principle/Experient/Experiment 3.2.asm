MOV CX,5-1      ;冒泡排序，外循环N-1次 

LP1:
PUSH CX
MOV SI,OFFSET TAB   ;当前TAB指针偏移地址给SI

LP2:
MOV AX,[SI]
CMP AX,[SI+2]       ;比较TAB[i]和TAB[i+1]
JAE J1              ;TAB[i]>TAB[i+1]跳转
XCHG AX,[SI+2]      ;TAB[i]<TAB[i+1]交换
MOV [SI],AX

J1:
ADD SI,2            ;SI+2指向下一个数据
LOOP LP2            ;继续下一次内循环
POP CX
LOOP LP1            ;内循环完成，进行外循换
;JMP $

HLT

TAB DW 9,3,6,4,2   ;段结构使用数据变量放在后面，定义五个数