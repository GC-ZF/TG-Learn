"""
@Author:张时贰
@Date:2022年06月01日
@CSDN:张时贰
@Blog:zhangshier.vip
"""
from datetime import datetime
now_time = datetime.now ()  #当前时间
one_time = now_time.replace(day=1, hour=0, minute=0, second=0, microsecond=0)   #置当月一号
week_num=int(now_time.strftime('%W')) - int(one_time.strftime('%W'))+1  # now-本月第一周+1=当前周数 strftime('%W')本年第几周
print(f"第{week_num}周")
print (f"{now_time.year}年{now_time.month}月{now_time.day}日")


print(f"该周的第{datetime.now().weekday()+1}天")     # weekday返回0~6所以+1
print(f"该周的第{datetime.now().strftime('%w')}天")  # strftime返回在本周的天数
