//#include <iostream>
//using namespace std;
//#include <string>
//class Student
//{
//public:
//	Student();
//	Student(string stu_id,string stu_name,int stu_age,string stu_sex,string stu_job)
//	{
//		this->stu_id = stu_id;
//		this->stu_name = stu_name;
//		this->stu_age = stu_age;
//		this->stu_sex = stu_sex;
//		this->stu_job = stu_job;
//	}
//	void addscore();//职务加分
//	void show();//输出函数
//private:
//	string stu_id;
//	string stu_name;
//	int stu_age;
//	string stu_sex="男";
//	string stu_job="学生";
//	int stu_score=0;
//};
//void Student::addscore()
//{
//	string job = stu_job;
//	if (job == "学生")
//		this->stu_score += 0;
//	else if (job == "班长")
//		this->stu_score += 5;
//	else if (job == "学习委员")
//		this->stu_score += 3;
//	else if (job == "团支书")
//		this->stu_score += 2;
//}
//
//void Student::show()
//{
//	cout << "学号\t" << stu_id << "\t姓名  " << stu_name << "\t年龄  " << stu_age << "\t性别  " << stu_sex << "\t职务  " << stu_job << "\t分数  " << stu_score << endl;
//}
//
//int main()
//{
//	Student s1( "192062116","张帆", 22, "男", "班长");
//	Student s2("192062117", "张凡", 21, "女", "学生");
//
//	s1.addscore();
//	s1.show();
//
//	s2.addscore();
//	s2.show();
//
//	system("pause");
//	return 0;
//}