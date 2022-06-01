"""
@Author:张时贰
@Date:2022年06月01日
@CSDN:张时贰
@Blog:zhangshier.vip
"""
# 需要的库
# pip install wordcloud
# pip install jieba
# pip install imageio
# 在代码目录下新建一个txt文本，Experiment 5.4_open：党的十八大提出，倡导富强、民主、文明、和谐，倡导自由、平等、公正、法治，倡导爱国、敬业、诚信、友善，积极培育和践行社会主义核心价值观。富强、民主、文明、和谐是国家层面的价值目标，自由、平等、公正、法治是社会层面的价值取向，爱国、敬业、诚信、友善是公民个人层面的价值准则，这24个字是社会主义核心价值观的基本内容。
# 准备一张背景图，修改38行，WordCloud会去除白色部分作为轮廓

import jieba
import imageio.v2 as imageio
from wordcloud import WordCloud

with open("Experiment 5.4_open.txt", "r",encoding='UTF-8') as f:
    allSentence = f.read()

print(allSentence)

re_move = ['，', '。', '\n', '\xa0', '-', '(', ')']  # 无效数据

# 去除无关数据
for i in re_move:
    allSentence = allSentence.replace(i, "")

pureWord = jieba.lcut(allSentence)
# Experiment 5.4_out.txt保存分词结果
with open("Experiment 5.4_out.txt", "w") as f:
     for i in pureWord:
         f.write(str(i)+" ")

with open("Experiment 5.4_out.txt", "r") as f:
    pureWord = f.read()

mask = imageio.imread("Experiment 5.4_bg.png")
word = WordCloud(background_color="white",
                 width=800,height=800,
                 font_path='simhei.ttf',
                 mask=mask,).generate(pureWord)
# 生成云图 Experiment 5.4_outphoto.png
word.to_file('Experiment 5.4_outphoto.png')

