"""
@Author:张时贰
@Date:2022年05月18日
@CSDN:张时贰
@Blog:zhsher.cn
"""
score = {'001': 96, '002': 98, '003': 92, '004': 93, '005': 94}
print ( "最初的学生成绩为:", score )

score[ '006' ] = 100  # 添加
print ( "向字典中添加006号学生成绩为:", score )

m_num = input ( "修改成绩的学生学号" )
m_score = int ( input ( "修改为" ) )
score[ m_num ] = m_score  # 修改
print ( "修改字典中学生成绩后:", score )

delete = input ( "输入删除学生学号" )
score.pop ( delete )  # 删除
print ( "删除学生成绩后:", score )

query = input ( "输入查询学生的学号" )
print ( "编号为 %s 的分数为: %d "%(query, score.get ( query )) )  # 查询

print ( "最高分为:", max ( score.values () ) )
print ( "最低分为:", min ( score.values () ) )
print ( "平均分为:", sum ( score.values () )/len ( score ) )
