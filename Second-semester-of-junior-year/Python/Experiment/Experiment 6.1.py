"""
@Author:张时贰
@Date:2022年06月08日
@CSDN:张时贰
@Blog:zhsher.cn
"""
listStr = [
"观沧海",
"曹操",
"东临碣石，以观沧海。",
"水何澹澹，山岛竦峙。",
"树木丛生，百草丰茂。",
"秋风萧瑟，洪波涌起。",
"日月之行，若出其中。",
"星汉灿烂，若出其里。",
"幸甚至哉，歌以咏志。"]
with open("gch.txt", "w") as file:
    for k in listStr:
        file.write(k+"\n")