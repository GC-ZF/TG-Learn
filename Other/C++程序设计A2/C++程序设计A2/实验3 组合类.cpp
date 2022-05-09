#include <iostream>
#include <string>

using namespace std;
class Student
{
public:
	Student() {};
	Student(string stu_id, string stu_name)
	{
		this->stu_id = stu_id;
		this->stu_name = stu_name;
	}
	void print()
	{
		cout << "4.组合类" << endl;
		cout << "学号  " << stu_id << "  姓名  " << stu_name << endl;
	}
private:
	string stu_id;
	string stu_name;
};
class Score
{
public:
	Score() {};
	Score(double sc_math, double sc_english, double sc_politics, double sc_cpp)
	{
		this->sc_math = sc_math;
		this->sc_english = sc_english;
		this->sc_politics = sc_politics;
		this->sc_cpp = sc_cpp;
	}
	void print()
	{
		cout << "高数  " << sc_math << "  英语  " << sc_english << "  政治  " << sc_politics << "  C++  " << sc_cpp << endl << endl;
	}
protected:
	double sc_math;
	double sc_english;
	double sc_politics;
	double sc_cpp;
};

class Show4
{
public:
	Show4(Student stu,Score sc)
	{
		stu.print();
		sc.print();
	}
private:
	Student stu;
	Score sc;
};
int main()
{
	Student stu("192062116", "张帆");
	Score sc( 80, 81, 82, 83);
	Show4(stu, sc);

	system("pause");
	return 0;
}
