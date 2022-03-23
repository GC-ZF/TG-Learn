"""
Author:张时贰
Date:2022年03月02
CSDN:张时贰
"""
total = 0;
ave = 0;
score=0
person = int(input("请输入班级人数"))
i=0
while i<person:
    score = int(input("请输入学生英语成绩："))
    flag=0
    while flag==0:
        if score>100 or score<0:
            score = int(input("输入有误,请重新输入"))
        if score<=100 and score>0:
            flag=1
    total = total + score
    i=i+1
ave = total / person
print("录入学生英语成绩 %d 份, 学生英语总成绩 %d, 平均成绩 %4.2f." % (person, total, ave))
