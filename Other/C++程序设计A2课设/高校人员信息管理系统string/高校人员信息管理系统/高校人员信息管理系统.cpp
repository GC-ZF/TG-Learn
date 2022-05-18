#include <iostream>
using namespace std;
#include<string>
#include <fstream>
#define MAX 200	//每一类人员上限100

//声明全局函数
void Statistics();//统计函数
void Statisticstotal();//统计总人数
void Statisticman();//统计男员工
void Statisticwomen();//统计女员工
void Statisticage();//统计年龄段
//人 类
class Person
{
public:
	string id;	//编号
	string name;//姓名
	string sex;//性别
	int age;  //年龄
};

//教师类
class Teacher :virtual public Person
{
	friend class Tea_Po; //声明友元，教师兼行政人员类访问教师私有变量
public:
	void Add();//添加
	void Seach();//查询
	void Show();//显示
	void Edit();//编辑
	void Delete();//删除
	void Save();//保存
	void Read();//读取
	void Seachid();//编号查询
	void Seachname();//姓名查询
	void Showone();
private:
	string t_dept;//所在系部
	string t_major;//专业
	string t_title;//职称
}; Teacher Tea[MAX]; int Teatop = 0;

void Teacher::Add()
{
	Teacher t_temp;//临时变量
	if (Teatop < MAX)
	{
		cout << "编号:"; cin >> id;
		for (int i = 0; i < Teatop; i++)
		{
			if (Tea[i].id == id)
			{
				cout << "id已存在" << endl;
				return;
			}
		}
		t_temp.id = id;
		cout << "姓名:"; cin >> t_temp.name;
		cout << "性别:"; cin >> t_temp.sex;
		cout << "年龄:"; cin >> t_temp.age;
		cout << "系部:"; cin >> t_temp.t_dept;
		cout << "专业:"; cin >> t_temp.t_major;
		cout << "职称:"; cin >> t_temp.t_title;
		Tea[Teatop] = t_temp;
		Teatop++;
	}
	else
	{
		cout << "当前教师已招满" << endl;
	}
}
void Teacher::Seach()
{
	while (true)
	{
		int c;
		cout << endl;
		cout << "  |￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣|" << endl;
		cout << "  |      教师管理平台-查询       |" << endl;
		cout << "  |   1.编号查询                 |" << endl;
		cout << "  |   2.姓名查询                 |" << endl;
		cout << "  |   0.退出                     |" << endl;
		cout << "  |______________________________|" << endl << endl;
		cout << "请选择"; cin >> c;
		switch (c)
		{
		case 1:
			Seachid();
			break;
		case 2:
			Seachname();
			break;
		case 0:
			return;
			break;
		default:
			cout << "输入错误请重新输入" << endl;
			break;
		}
	}
}
void Teacher::Show()
{
	if (Teatop == 0)
	{
		cout << "当前教师系统内为空" << endl;
	}
	else
	{
		for (int i = 0; i < Teatop; i++)
		{
			Tea[i].Showone();
		}
	}
}
void Teacher::Edit()
{
	Teacher t_temp;
	while (true)
	{
		int c;
		cout << endl;
		cout << "  |￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣|" << endl;
		cout << "  |      教师管理平台-编辑       |" << endl;
		cout << "  |   1.根据编号查询并修改       |" << endl;
		cout << "  |   2.根据姓名查询并修改       |" << endl;
		cout << "  |   0.退出                     |" << endl;
		cout << "  |______________________________|" << endl << endl;
		cout << "请选择"; cin >> c;
		if (c == 1)
		{
			string sid;
			int i;
			cout << "请输入编号"; cin >> sid;
			for (i = 0; i < Teatop; i++)
			{
				if (Tea[i].id == sid)
					break;
			}
			if (i == Teatop)
				cout << "输入编号无效，系统内不存在" << endl;
			else
			{
				cout << "编号:"; cin >> t_temp.id;
				cout << "姓名:"; cin >> t_temp.name;
				cout << "性别:"; cin >> t_temp.sex;
				cout << "年龄:"; cin >> t_temp.age;
				cout << "系部:"; cin >> t_temp.t_dept;
				cout << "专业:"; cin >> t_temp.t_major;
				cout << "职称:"; cin >> t_temp.t_title;
				Tea[i] = t_temp;
			}
			break;
		}
		else if (c == 2)
		{
			string sname;
			int i;
			cout << "请输入姓名"; cin >> sname;
			for (i = 0; i < Teatop; i++)
			{
				if (Tea[i].name == sname)
					break;
			}
			if (i == Teatop)
				cout << "输入编号无效，系统内不存在" << endl;
			else
			{
				cout << "编号:"; cin >> t_temp.id;
				cout << "姓名:"; cin >> t_temp.name;
				cout << "性别:"; cin >> t_temp.sex;
				cout << "年龄:"; cin >> t_temp.age;
				cout << "系部:"; cin >> t_temp.t_dept;
				cout << "专业:"; cin >> t_temp.t_major;
				cout << "职称:"; cin >> t_temp.t_title;
				Tea[i] = t_temp;
			}
			break;
		}
		else if (c == 0)
		{
			break;
		}
		else
		{
			cout << "输入错误请重新选择" << endl;
		}
	}
}
void Teacher::Delete()
{
	Teacher t_temp;
	if (Teatop == 0)
	{
		cout << "当前记录为空" << endl;
		return;
	}
	while (true)
	{
		int c;
		cout << endl;
		cout << "  |￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣|" << endl;
		cout << "  |      教师管理平台-删除       |" << endl;
		cout << "  |   1.根据编号查询并删除       |" << endl;
		cout << "  |   2.根据姓名查询并删除       |" << endl;
		cout << "  |   0.退出                     |" << endl;
		cout << "  |______________________________|" << endl << endl;
		cout << "请选择"; cin >> c;
		if (c == 1)
		{
			string sid;
			int i;
			cout << "请输入编号"; cin >> sid;
			for (i = 0; i < Teatop; i++)
			{
				if (Tea[i].id == sid)
					break;
			}
			if (i == Teatop)
				cout << "输入编号无效，系统内不存在" << endl;
			else
			{
				for (i; i < Teatop; i++)
				{
					Tea[i] = Tea[i + 1];
				}
				cout << "删除成功" << endl;
				Teatop--;
			}
			break;
		}
		else if (c == 2)
		{
			string sname;
			int i;
			cout << "请输入姓名"; cin >> sname;
			for (i = 0; i < Teatop; i++)
			{
				if (Tea[i].name == sname)
					break;
			}
			if (i == Teatop)
				cout << "输入姓名无效，系统内不存在" << endl;
			else
			{
				for (i; i < Teatop; i++)
				{
					Tea[i] = Tea[i + 1];
				}
				Teatop--;
			}
			break;
		}
		else if (c == 0)
		{
			break;
		}
		else
		{
			cout << "输入错误请重新选择" << endl;
		}
	}
}

void Teacher::Save()
{
	int i;
	ofstream outfile, outfile1;
	outfile1.open("Teatop.dat", ios::out);	//写文件Teatop.dat
	outfile1 << Teatop;
	outfile.open("Tea_data.dat", ios::binary);
	if (!outfile)
	{
		cerr << "open error!" << endl; return;
	}
	for (i = 0; i < Teatop; i++)
		outfile.write((char *)&Tea[i], sizeof(Tea[i]));
	outfile.close();
	cout << "保存成功！" << endl;
}
void Teacher::Read()
{
	int i;
	ifstream infile, infile1;
	infile1.open("Teatop.dat", ios::in);
	infile1 >> Teatop;
	infile.open("Tea_data.dat", ios::binary);
	if (!infile)
	{
		cerr << "open error!" << endl; return;
	}
	for (i = 0; i < Teatop; i++)
		infile.read((char *)&Tea[i], sizeof(Tea[i]));
	infile.close();
	cout << "读取成功！" << endl;
}
void Teacher::Seachid()
{
	string sid;
	cout << "请输入编号" << endl; cin >> sid;
	int i;
	for (i = 0; i < Teatop; i++)
	{
		if (Tea[i].id == sid)
		{
			Tea[i].Showone();
			break;
		}
	}
	if (i == Teatop)
	{
		cout << "该编号不存在" << endl;
	}
}
void Teacher::Seachname()
{
	string sname;
	cout << "请输入姓名" << endl; cin >> sname;
	int i;
	for (i = 0; i < Teatop; i++)
	{
		if (Tea[i].name == sname)
		{
			Tea[i].Showone();
			break;
		}
	}
	if (i == Teatop)
	{
		cout << "该姓名不存在" << endl;
	}
}
void Teacher::Showone()
{
	cout << "编号\t" << id << "\t姓名\t" << name << "\t性别\t" << sex << "\t年龄\t" << age << "\t系部\t" << t_dept << "\t专业\t" << t_major << "\t职称\t" << t_title << endl;
}

//实验人员类
class Experiment :public Person
{
public:
	void Add();//添加
	void Seach();//查询
	void Show();//显示
	void Edit();//编辑
	void Delete();//删除
	void Save();//保存
	void Read();//读取
	void Seachid();//编号查询
	void Seachname();//姓名查询
	void Showone();
private:
	string e_location;//所在实验室
	string e_job;//职务
}; Experiment Exper[MAX]; int Expertop;

void Experiment::Add()
{
	Experiment e_temp;//临时变量
	if (Expertop < MAX)
	{
		cout << "编号:"; cin >> id;
		for (int i = 0; i < Expertop; i++)
		{
			if (Exper[i].id == id)
			{
				cout << "id已存在" << endl;
				return;
			}
		}
		e_temp.id = id;
		cout << "姓名:"; cin >> e_temp.name;
		cout << "性别:"; cin >> e_temp.sex;
		cout << "年龄:"; cin >> e_temp.age;
		cout << "实验室:"; cin >> e_temp.e_location;
		cout << "职务:"; cin >> e_temp.e_job;
		Exper[Expertop] = e_temp;
		Expertop++;
	}
	else
	{
		cout << "当前实验人员已招满" << endl;
	}
}
void Experiment::Seach()
{
	while (true)
	{
		int c;
		cout << endl;
		cout << "  |￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣|" << endl;
		cout << "  |  实验人员管理平台-查询       |" << endl;
		cout << "  |   1.编号查询                 |" << endl;
		cout << "  |   2.姓名查询                 |" << endl;
		cout << "  |   0.退出                     |" << endl;
		cout << "  |______________________________|" << endl << endl;
		cout << "请选择"; cin >> c;
		switch (c)
		{
		case 1:
			Seachid();
			break;
		case 2:
			Seachname();
			break;
		case 0:
			return;
			break;
		default:
			cout << "输入错误请重新输入" << endl;
			break;
		}
	}
}
void Experiment::Show()
{
	if (Expertop == 0)
	{
		cout << "当前实验人员系统内为空" << endl;
	}
	else
	{
		for (int i = 0; i < Expertop; i++)
		{
			Exper[i].Showone();
		}
	}
}
void Experiment::Edit()
{
	Experiment e_temp;
	while (true)
	{
		int c;
		cout << endl;
		cout << "  |￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣|" << endl;
		cout << "  |  实验人员管理平台-编辑       |" << endl;
		cout << "  |   1.根据编号查询并修改       |" << endl;
		cout << "  |   2.根据姓名查询并修改       |" << endl;
		cout << "  |   0.退出                     |" << endl;
		cout << "  |______________________________|" << endl << endl;
		cout << "请选择"; cin >> c;
		if (c == 1)
		{
			string sid;
			int i;
			cout << "请输入编号"; cin >> sid;
			for (i = 0; i < Expertop; i++)
			{
				if (Exper[i].id == sid)
					break;
			}
			if (i == Expertop)
				cout << "输入编号无效，系统内不存在" << endl;
			else
			{
				cout << "编号:"; cin >> e_temp.id;
				cout << "姓名:"; cin >> e_temp.name;
				cout << "性别:"; cin >> e_temp.sex;
				cout << "年龄:"; cin >> e_temp.age;
				cout << "实验室:"; cin >> e_temp.e_location;
				cout << "职务:"; cin >> e_temp.e_job;
				Exper[i] = e_temp;
			}
			break;
		}
		else if (c == 2)
		{
			string sname;
			int i;
			cout << "请输入姓名"; cin >> sname;
			for (i = 0; i < Expertop; i++)
			{
				if (Exper[i].name == sname)
					break;
			}
			if (i == Expertop)
				cout << "输入编号无效，系统内不存在" << endl;
			else
			{
				cout << "编号:"; cin >> e_temp.id;
				cout << "姓名:"; cin >> e_temp.name;
				cout << "性别:"; cin >> e_temp.sex;
				cout << "年龄:"; cin >> e_temp.age;
				cout << "实验室:"; cin >> e_temp.e_location;
				cout << "职务:"; cin >> e_temp.e_job;
				Exper[i] = e_temp;
			}
			break;
		}
		else if (c == 0)
		{
			break;
		}
		else
		{
			cout << "输入错误请重新选择" << endl;
		}
	}
}
void Experiment::Delete()
{
	Experiment e_temp;
	if (Expertop == 0)
	{
		cout << "当前记录为空" << endl;
		return;
	}
	while (true)
	{
		int c;
		cout << endl;
		cout << "  |￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣|" << endl;
		cout << "  |   实验人员管理平台-删除      |" << endl;
		cout << "  |   1.根据编号查询并删除       |" << endl;
		cout << "  |   2.根据姓名查询并删除       |" << endl;
		cout << "  |   0.退出                     |" << endl;
		cout << "  |______________________________|" << endl << endl;
		cout << "请选择"; cin >> c;
		if (c == 1)
		{
			string sid;
			int i;
			cout << "请输入编号"; cin >> sid;
			for (i = 0; i < Expertop; i++)
			{
				if (Exper[i].id == sid)
					break;
			}
			if (i == Expertop)
				cout << "输入编号无效，系统内不存在" << endl;
			else
			{
				for (i; i < Expertop; i++)
				{
					Exper[i] = Exper[i + 1];
				}
				cout << "删除成功" << endl;
				Expertop--;
			}
			break;
		}
		else if (c == 2)
		{
			string sname;
			int i;
			cout << "请输入姓名"; cin >> sname;
			for (i = 0; i < Expertop; i++)
			{
				if (Exper[i].name == sname)
					break;
			}
			if (i == Expertop)
				cout << "输入姓名无效，系统内不存在" << endl;
			else
			{
				for (i; i < Expertop; i++)
				{
					Exper[i] = Exper[i + 1];
				}
				Expertop--;
			}
			break;
		}
		else if (c == 0)
		{
			break;
		}
		else
		{
			cout << "输入错误请重新选择" << endl;
		}
	}
}

void Experiment::Save()
{
	int i;
	ofstream outfile, outfile1;
	outfile1.open("Expertop.dat", ios::out);	//写文件Expertop.dat
	outfile1 << Expertop;
	outfile.open("Exper_data.dat", ios::binary);
	if (!outfile)
	{
		cerr << "open error!" << endl; return;
	}
	for (i = 0; i < Expertop; i++)
		outfile.write((char *)&Exper[i], sizeof(Exper[i]));
	outfile.close();
	cout << "保存成功！" << endl;
}
void Experiment::Read()
{
	int i;
	ifstream infile, infile1;
	infile1.open("Expertop.dat", ios::in);
	infile1 >> Expertop;
	infile.open("Exper_data.dat", ios::binary);
	if (!infile)
	{
		cerr << "open error!" << endl; return;
	}
	for (i = 0; i < Expertop; i++)
		infile.read((char *)&Exper[i], sizeof(Exper[i]));
	infile.close();
	cout << "读取成功！" << endl;
}
void Experiment::Seachid()
{
	string sid;
	cout << "请输入编号" << endl; cin >> sid;
	int i;
	for (i = 0; i < Expertop; i++)
	{
		if (Exper[i].id == sid)
		{
			Exper[i].Showone();
			break;
		}
	}
	if (i == Expertop)
	{
		cout << "该编号不存在" << endl;
	}
}
void Experiment::Seachname()
{
	string sname;
	cout << "请输入姓名" << endl; cin >> sname;
	int i;
	for (i = 0; i < Expertop; i++)
	{
		if (Exper[i].name == sname)
		{
			Exper[i].Showone();
			break;
		}
	}
	if (i == Expertop)
	{
		cout << "该姓名不存在" << endl;
	}
}
void Experiment::Showone()
{
	cout << "编号\t" << id << "\t姓名\t" << name << "\t性别\t" << sex << "\t年龄\t" << age << "\t实验室\t" << e_location << "\t职务\t" << e_job << endl;
}

//行政人员类
class Politician :virtual public Person
{
	friend class Tea_Po;//声明友元，教师兼行政人员类访问行政人员私有变量
public:
	void Add();//添加
	void Seach();//查询
	void Show();//显示
	void Edit();//编辑
	void Delete();//删除
	void Save();//保存
	void Read();//读取
	void Seachid();//编号查询
	void Seachname();//姓名查询
	void Showone();
private:
	string p_look;//政治面貌
	string p_post;//职称
}; Politician Po[MAX]; int Potop;

void Politician::Add()
{
	Politician p_temp;//临时变量
	if (Potop < MAX)
	{
		cout << "编号:"; cin >> id;
		for (int i = 0; i < Potop; i++)
		{
			if (Po[i].id == id)
			{
				cout << "id已存在" << endl;
				return;
			}
		}
		p_temp.id = id;
		cout << "姓名:"; cin >> p_temp.name;
		cout << "性别:"; cin >> p_temp.sex;
		cout << "年龄:"; cin >> p_temp.age;
		cout << "政治面貌:"; cin >> p_temp.p_look;
		cout << "职称:"; cin >> p_temp.p_post;
		Po[Potop] = p_temp;
		Potop++;
	}
	else
	{
		cout << "当前行政人员已招满" << endl;
	}
}
void Politician::Seach()
{
	while (true)
	{
		int c;
		cout << endl;
		cout << "  |￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣|" << endl;
		cout << "  |  行政人员管理平台-查询       |" << endl;
		cout << "  |   1.编号查询                 |" << endl;
		cout << "  |   2.姓名查询                 |" << endl;
		cout << "  |   0.退出                     |" << endl;
		cout << "  |______________________________|" << endl << endl;
		cout << "请选择"; cin >> c;
		switch (c)
		{
		case 1:
			Seachid();
			break;
		case 2:
			Seachname();
			break;
		case 0:
			return;
			break;
		default:
			cout << "输入错误请重新输入" << endl;
			break;
		}
	}
}
void Politician::Show()
{
	if (Potop == 0)
	{
		cout << "当前行政人员系统内为空" << endl;
	}
	else
	{
		for (int i = 0; i < Potop; i++)
		{
			Po[i].Showone();
		}
	}
}
void Politician::Edit()
{
	Politician p_temp;
	while (true)
	{
		int c;
		cout << endl;
		cout << "  |￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣|" << endl;
		cout << "  |  行政人员管理平台-编辑       |" << endl;
		cout << "  |   1.根据编号查询并修改       |" << endl;
		cout << "  |   2.根据姓名查询并修改       |" << endl;
		cout << "  |   0.退出                     |" << endl;
		cout << "  |______________________________|" << endl << endl;
		cout << "请选择"; cin >> c;
		if (c == 1)
		{
			string sid;
			int i;
			cout << "请输入编号"; cin >> sid;
			for (i = 0; i < Potop; i++)
			{
				if (Po[i].id == sid)
					break;
			}
			if (i == Potop)
				cout << "输入编号无效，系统内不存在" << endl;
			else
			{
				cout << "编号:"; cin >> p_temp.id;
				cout << "姓名:"; cin >> p_temp.name;
				cout << "性别:"; cin >> p_temp.sex;
				cout << "年龄:"; cin >> p_temp.age;
				cout << "政治面貌:"; cin >> p_temp.p_look;
				cout << "职称:"; cin >> p_temp.p_post;
				Po[i] = p_temp;
			}
			break;
		}
		else if (c == 2)
		{
			string sname;
			int i;
			cout << "请输入姓名"; cin >> sname;
			for (i = 0; i < Potop; i++)
			{
				if (Po[i].name == sname)
					break;
			}
			if (i == Potop)
				cout << "输入编号无效，系统内不存在" << endl;
			else
			{
				cout << "编号:"; cin >> p_temp.id;
				cout << "姓名:"; cin >> p_temp.name;
				cout << "性别:"; cin >> p_temp.sex;
				cout << "年龄:"; cin >> p_temp.age;
				cout << "政治面貌:"; cin >> p_temp.p_look;
				cout << "职称:"; cin >> p_temp.p_post;
				Po[i] = p_temp;
			}
			break;
		}
		else if (c == 0)
		{
			break;
		}
		else
		{
			cout << "输入错误请重新选择" << endl;
		}
	}
}
void Politician::Delete()
{
	Politician p_temp;
	if (Potop == 0)
	{
		cout << "当前记录为空" << endl;
		return;
	}
	while (true)
	{
		int c;
		cout << endl;
		cout << "  |￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣|" << endl;
		cout << "  |   行政人员管理平台-删除      |" << endl;
		cout << "  |   1.根据编号查询并删除       |" << endl;
		cout << "  |   2.根据姓名查询并删除       |" << endl;
		cout << "  |   0.退出                     |" << endl;
		cout << "  |______________________________|" << endl << endl;
		cout << "请选择"; cin >> c;
		if (c == 1)
		{
			string sid;
			int i;
			cout << "请输入编号"; cin >> sid;
			for (i = 0; i < Potop; i++)
			{
				if (Po[i].id == sid)
					break;
			}
			if (i == Potop)
				cout << "输入编号无效，系统内不存在" << endl;
			else
			{
				for (i; i < Potop; i++)
				{
					Po[i] = Po[i + 1];
				}
				cout << "删除成功" << endl;
				Potop--;
			}
			break;
		}
		else if (c == 2)
		{
			string sname;
			int i;
			cout << "请输入姓名"; cin >> sname;
			for (i = 0; i < Potop; i++)
			{
				if (Po[i].name == sname)
					break;
			}
			if (i == Potop)
				cout << "输入姓名无效，系统内不存在" << endl;
			else
			{
				for (i; i < Potop; i++)
				{
					Po[i] = Po[i + 1];
				}
				Potop--;
			}
			break;
		}
		else if (c == 0)
		{
			break;
		}
		else
		{
			cout << "输入错误请重新选择" << endl;
		}
	}
}

void Politician::Save()
{
	int i;
	ofstream outfile, outfile1;
	outfile1.open("Potop.dat", ios::out);	//写文件Potop.dat
	outfile1 << Potop;
	outfile.open("Po_data.dat", ios::binary);
	if (!outfile)
	{
		cerr << "open error!" << endl; return;
	}
	for (i = 0; i < Potop; i++)
		outfile.write((char *)&Po[i], sizeof(Po[i]));
	outfile.close();
	cout << "保存成功！" << endl;
}
void Politician::Read()
{
	int i;
	ifstream infile, infile1;
	infile1.open("Potop.dat", ios::in);
	infile1 >> Potop;
	infile.open("Po_data.dat", ios::binary);
	if (!infile)
	{
		cerr << "open error!" << endl; return;
	}
	for (i = 0; i < Potop; i++)
		infile.read((char *)&Po[i], sizeof(Po[i]));
	infile.close();
	cout << "读取成功！" << endl;
}
void Politician::Seachid()
{
	string sid;
	cout << "请输入编号" << endl; cin >> sid;
	int i;
	for (i = 0; i < Potop; i++)
	{
		if (Po[i].id == sid)
		{
			Po[i].Showone();
			break;
		}
	}
	if (i == Potop)
	{
		cout << "该编号不存在" << endl;
	}
}
void Politician::Seachname()
{
	string sname;
	cout << "请输入姓名" << endl; cin >> sname;
	int i;
	for (i = 0; i < Potop; i++)
	{
		if (Po[i].name == sname)
		{
			Po[i].Showone();
			break;
		}
	}
	if (i == Potop)
	{
		cout << "该姓名不存在" << endl;
	}
}
void Politician::Showone()
{
	cout << "编号\t" << id << "\t姓名\t" << name << "\t性别\t" << sex << "\t年龄\t" << age << "\t政治面貌\t" << p_look << "\t职称\t" << p_post << endl;
}


//教师兼行政人员类
class Tea_Po :public Teacher,Politician
{
public:
	void Add();//添加
	void Seach();//查询
	void Show();//显示
	void Edit();//编辑
	void Delete();//删除,调用
	void Save();//保存
	void Read();//读取
	void Seachid();//编号查询
	void Seachname();//姓名查询
	void Showone();
}; Tea_Po T_P[MAX]; int T_Ptop;



void Tea_Po::Add()
{
	Tea_Po tp_temp;//临时变量
	if (T_Ptop < MAX)
	{
		cout << "编号:"; cin >> id;
		for (int i = 0; i < T_Ptop; i++)
		{
			if (T_P[i].id == id)
			{
				cout << "id已存在" << endl;
				return;
			}
		}
		tp_temp.id = id;
		cout << "姓名:"; cin >> tp_temp.name;
		cout << "性别:"; cin >> tp_temp.sex;
		cout << "年龄:"; cin >> tp_temp.age;
		cout << "系部:"; cin >> tp_temp.t_dept;
		cout << "专业:"; cin >> tp_temp.t_major;
		cout << "政治面貌:"; cin >> tp_temp.p_look;
		cout << "职称:"; cin >> tp_temp.p_post;
		T_P[T_Ptop] = tp_temp;
		T_Ptop++;
	}
	else
	{
		cout << "当前教师兼行政人员已招满" << endl;
	}
}
void Tea_Po::Seach()
{
	while (true)
	{
		int c;
		cout << endl;
		cout << "  |￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣|" << endl;
		cout << "  |  教师兼行政人员管理平台-查询       |" << endl;
		cout << "  |   1.编号查询                 |" << endl;
		cout << "  |   2.姓名查询                 |" << endl;
		cout << "  |   0.退出                     |" << endl;
		cout << "  |______________________________|" << endl << endl;
		cout << "请选择"; cin >> c;
		switch (c)
		{
		case 1:
			Seachid();
			break;
		case 2:
			Seachname();
			break;
		case 0:
			return;
			break;
		default:
			cout << "输入错误请重新输入" << endl;
			break;
		}
	}
}
void Tea_Po::Show()
{
	if (T_Ptop == 0)
	{
		cout << "当前教师兼行政人员系统内为空" << endl;
	}
	else
	{
		for (int i = 0; i < T_Ptop; i++)
		{
			T_P[i].Showone();
		}
	}
}
void Tea_Po::Edit()
{
	Tea_Po tp_temp;
	while (true)
	{
		int c;
		cout << endl;
		cout << "  |￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣|" << endl;
		cout << "  |  教师兼行政人员管理平台-编辑       |" << endl;
		cout << "  |   1.根据编号查询并修改       |" << endl;
		cout << "  |   2.根据姓名查询并修改       |" << endl;
		cout << "  |   0.退出                     |" << endl;
		cout << "  |______________________________|" << endl << endl;
		cout << "请选择"; cin >> c;
		if (c == 1)
		{
			string sid;
			int i;
			cout << "请输入编号"; cin >> sid;
			for (i = 0; i < T_Ptop; i++)
			{
				if (T_P[i].id == sid)
					break;
			}
			if (i == T_Ptop)
				cout << "输入编号无效，系统内不存在" << endl;
			else
			{
				cout << "编号:"; cin >> tp_temp.id;
				cout << "姓名:"; cin >> tp_temp.name;
				cout << "性别:"; cin >> tp_temp.sex;
				cout << "年龄:"; cin >> tp_temp.age;
				cout << "系部:"; cin >> tp_temp.t_dept;
				cout << "专业"; cin >> tp_temp.t_major;
				cout << "政治面貌:"; cin >> tp_temp.p_look;
				cout << "职称:"; cin >> tp_temp.p_post;
				T_P[i] = tp_temp;
			}
			break;
		}
		else if (c == 2)
		{
			string sname;
			int i;
			cout << "请输入姓名"; cin >> sname;
			for (i = 0; i < T_Ptop; i++)
			{
				if (T_P[i].name == sname)
					break;
			}
			if (i == T_Ptop)
				cout << "输入编号无效，系统内不存在" << endl;
			else
			{
				cout << "编号:"; cin >> tp_temp.id;
				cout << "姓名:"; cin >> tp_temp.name;
				cout << "性别:"; cin >> tp_temp.sex;
				cout << "年龄:"; cin >> tp_temp.age;
				cout << "系部:"; cin >> tp_temp.t_dept;
				cout << "专业"; cin >> tp_temp.t_major;
				cout << "政治面貌:"; cin >> tp_temp.p_look;
				cout << "职称:"; cin >> tp_temp.p_post;
				T_P[i] = tp_temp;
			}
			break;
		}
		else if (c == 0)
		{
			break;
		}
		else
		{
			cout << "输入错误请重新选择" << endl;
		}
	}
}
void Tea_Po::Delete()
{
	Tea_Po tp_temp;
	if (T_Ptop == 0)
	{
		cout << "当前记录为空" << endl;
		return;
	}
	while (true)
	{
		int c;
		cout << endl;
		cout << "  |￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣|" << endl;
		cout << "  |   教师兼行政人员管理平台-删除      |" << endl;
		cout << "  |   1.根据编号查询并删除       |" << endl;
		cout << "  |   2.根据姓名查询并删除       |" << endl;
		cout << "  |   0.退出                     |" << endl;
		cout << "  |______________________________|" << endl << endl;
		cout << "请选择"; cin >> c;
		if (c == 1)
		{
			string sid;
			int i;
			cout << "请输入编号"; cin >> sid;
			for (i = 0; i < T_Ptop; i++)
			{
				if (T_P[i].id == sid)
					break;
			}
			if (i == T_Ptop)
				cout << "输入编号无效，系统内不存在" << endl;
			else
			{
				for (i; i < T_Ptop; i++)
				{
					T_P[i] = T_P[i + 1];
				}
				cout << "删除成功" << endl;
				T_Ptop--;
			}
			break;
		}
		else if (c == 2)
		{
			string sname;
			int i;
			cout << "请输入姓名"; cin >> sname;
			for (i = 0; i < T_Ptop; i++)
			{
				if (T_P[i].name == sname)
					break;
			}
			if (i == T_Ptop)
				cout << "输入姓名无效，系统内不存在" << endl;
			else
			{
				for (i; i < T_Ptop; i++)
				{
					T_P[i] = T_P[i + 1];
				}
				T_Ptop--;
			}
			break;
		}
		else if (c == 0)
		{
			break;
		}
		else
		{
			cout << "输入错误请重新选择" << endl;
		}
	}
}

void Tea_Po::Save()
{
	int i;
	ofstream outfile, outfile1;
	outfile1.open("T_Ptop.dat", ios::out);	//写文件T_Ptop.dat
	outfile1 << T_Ptop;
	outfile.open("T_P_data.dat", ios::binary);
	if (!outfile)
	{
		cerr << "open error!" << endl; return;
	}
	for (i = 0; i < T_Ptop; i++)
		outfile.write((char *)&T_P[i], sizeof(T_P[i]));
	outfile.close();
	cout << "保存成功！" << endl;
}
void Tea_Po::Read()
{
	int i;
	ifstream infile, infile1;
	infile1.open("T_Ptop.dat", ios::in);
	infile1 >> T_Ptop;
	infile.open("T_P_data.dat", ios::binary);
	if (!infile)
	{
		cerr << "open error!" << endl; return;
	}
	for (i = 0; i < T_Ptop; i++)
		infile.read((char *)&T_P[i], sizeof(T_P[i]));
	infile.close();
	cout << "读取成功！" << endl;
}
void Tea_Po::Seachid()
{
	string sid;
	cout << "请输入编号" << endl; cin >> sid;
	int i;
	for (i = 0; i < T_Ptop; i++)
	{
		if (T_P[i].id == sid)
		{
			T_P[i].Showone();
			break;
		}
	}
	if (i == T_Ptop)
	{
		cout << "该编号不存在" << endl;
	}
}
void Tea_Po::Seachname()
{
	string sname;
	cout << "请输入姓名" << endl; cin >> sname;
	int i;
	for (i = 0; i < T_Ptop; i++)
	{
		if (T_P[i].name == sname)
		{
			T_P[i].Showone();
			break;
		}
	}
	if (i == T_Ptop)
	{
		cout << "该姓名不存在" << endl;
	}
}
void Tea_Po::Showone()
{
	cout << "编号\t" << id << "\t姓名\t" << name << "\t性别\t" << sex << "\t年龄\t" << age <<"\t系部\t"<<t_dept<<"\t专业"<<t_major<< "\t政治面貌\t" << p_look << "\t职称\t" << p_look << endl;
}

/*分别设计四类人员的菜单
教师菜单	void Tea_menu(Teacher tea)
实验人员菜单void Exper_menu(Experiment exper)
行政人员菜单void P_menu(Politician po)
老师兼行政人员菜单void TeaPo_menu(Tea_Po t_p)
*/

//教师菜单
void Tea_menu(Teacher tea)
{
	while (true)
	{
		system("pause");
		system("cls");//清除主菜单
		int c;
		cout << endl;
		cout << "  |￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣|" << endl;
		cout << "  |      教师管理平台            |" << endl;
		cout << "  |   1.添加                     |" << endl;
		cout << "  |   2.查询                     |" << endl;
		cout << "  |   3.显示                     |" << endl;
		cout << "  |   4.编辑                     |" << endl;
		cout << "  |   5.删除                     |" << endl;
		cout << "  |   6.统计                     |" << endl;
		cout << "  |   7.保存                     |" << endl;
		cout << "  |   8.读取                     |" << endl;
		cout << "  |   0.退出                     |" << endl;
		cout << "  |______________________________|" << endl << endl;
		cout << "请选择"; cin >> c;
		switch (c)
		{
		case 1:
			tea.Add();
			break;
		case 2:
			tea.Seach();
			break;
		case 3:
			tea.Show();
			break;
		case 4:
			tea.Edit();
			break;
		case 5:
			tea.Delete();
			break;
		case 6:
			Statistics();
			break;
		case 7:
			tea.Save();
			break;
		case 8:
			tea.Read();
			break;
		case 0:
			break;
		default:
			cout << "输入错误请重新输入" << endl;
			break;
		}
		if (c == 0)
			break;
	}
}

//实验人员菜单
void Exper_menu(Experiment exper)
{
	while (true)
	{
		system("pause");
		system("cls");//清除主菜单
		int c;
		cout << endl;
		cout << "  |￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣|" << endl;
		cout << "  |      实验人员管理平台        |" << endl;
		cout << "  |   1.添加                     |" << endl;
		cout << "  |   2.查询                     |" << endl;
		cout << "  |   3.显示                     |" << endl;
		cout << "  |   4.编辑                     |" << endl;
		cout << "  |   5.删除                     |" << endl;
		cout << "  |   6.统计                     |" << endl;
		cout << "  |   7.保存                     |" << endl;
		cout << "  |   8.读取                     |" << endl;
		cout << "  |   0.退出                     |" << endl;
		cout << "  |______________________________|" << endl << endl;
		cout << "请选择"; cin >> c;
		switch (c)
		{
		case 1:
			exper.Add();
			break;
		case 2:
			exper.Seach();
			break;
		case 3:
			exper.Show();
			break;
		case 4:
			exper.Edit();
			break;
		case 5:
			exper.Delete();
			break;
		case 6:
			Statistics();
			break;
		case 7:
			exper.Save();
			break;
		case 8:
			exper.Read();
			break;
		case 0:
			break;
		default:
			cout << "输入错误请重新输入" << endl;
			break;
		}
		if (c == 0)
			break;
	}
}

//行政人员类菜单
void P_menu(Politician po)
{
	while (true)
	{
		system("pause");
		system("cls");//清除主菜单
		int c;
		cout << endl;
		cout << "  |￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣|" << endl;
		cout << "  |      行政人员管理平台        |" << endl;
		cout << "  |   1.添加                     |" << endl;
		cout << "  |   2.查询                     |" << endl;
		cout << "  |   3.显示                     |" << endl;
		cout << "  |   4.编辑                     |" << endl;
		cout << "  |   5.删除                     |" << endl;
		cout << "  |   6.统计                     |" << endl;
		cout << "  |   7.保存                     |" << endl;
		cout << "  |   8.读取                     |" << endl;
		cout << "  |   0.退出                     |" << endl;
		cout << "  |______________________________|" << endl << endl;
		cout << "请选择"; cin >> c;
		switch (c)
		{
		case 1:
			po.Add();
			break;
		case 2:
			po.Seach();
			break;
		case 3:
			po.Show();
			break;
		case 4:
			po.Edit();
			break;
		case 5:
			po.Delete();
			break;
		case 6:
			Statistics();
			break;
		case 7:
			po.Save();
			break;
		case 8:
			po.Read();
			break;
		case 0:
			break;
		default:
			cout << "输入错误请重新输入" << endl;
			break;
		}
		if (c == 0)
			break;
	}
}


//教师兼行政人员菜单
void TeaPo_menu(Tea_Po t_p)
{
	while (true)
	{
		system("pause");
		system("cls");//清除主菜单
		int c;
		cout << endl;
		cout << "  |￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣|" << endl;
		cout << "  |  教师兼行政人员人员管理平台  |" << endl;
		cout << "  |   1.添加                     |" << endl;
		cout << "  |   2.查询                     |" << endl;
		cout << "  |   3.显示                     |" << endl;
		cout << "  |   4.编辑                     |" << endl;
		cout << "  |   5.删除                     |" << endl;
		cout << "  |   6.统计                     |" << endl;
		cout << "  |   7.保存                     |" << endl;
		cout << "  |   8.读取                     |" << endl;
		cout << "  |   0.退出                     |" << endl;
		cout << "  |______________________________|" << endl << endl;
		cout << "请选择"; cin >> c;
		switch (c)
		{
		case 1:
			t_p.Add();
			break;
		case 2:
			t_p.Seach();
			break;
		case 3:
			t_p.Show();
			break;
		case 4:
			t_p.Edit();
			break;
		case 5:
			t_p.Delete();
			break;
		case 6:
			Statistics();
			break;
		case 7:
			t_p.Save();
			break;
		case 8:
			t_p.Read();
			break;
		case 0:
			break;
		default:
			cout << "输入错误请重新输入" << endl;
			break;
		}
		if (c == 0)
			break;
	}
}



void Statistics()
{
	while (true)
	{
		int c;
		cout << endl;
		cout << "  |￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣|" << endl;
		cout << "  |      人数统计                |" << endl;
		cout << "  |   1.四类人员数量             |" << endl;
		cout << "  |   2.男员工数量               |" << endl;
		cout << "  |   3.女员工数量               |" << endl;
		cout << "  |   4.某年龄段数量             |" << endl;
		cout << "  |   0.退出                     |" << endl;
		cout << "  |______________________________|" << endl << endl;
		cout << "请选择"; cin >> c;
		switch (c)
		{
		case 1:
			Statisticstotal();
			break;
		case 2:
			Statisticman();
			break;
		case 3:
			Statisticwomen();
			break;
		case 4:
			Statisticage();
			break;
		case 0:
			return;
			break;
		default:
			cout << "输入错误请重新输入" << endl;
			break;
		}
	}
}
//四类员工数量
void Statisticstotal()
{
	cout << "四类员工总数量" << Teatop + Expertop + Potop + T_Ptop << endl;
	cout << "教师总数量" << Teatop << endl;
	cout << "实验员总数量" << Expertop << endl;
	cout << "行政人员总数量" << Potop << endl;
	cout << "教师兼行政人员总数量" << T_Ptop << endl;
}
//男员工数量
void Statisticman()
{
	int man = 0;
	for (int i = 0; i < Teatop; i++)
	{
		if (Tea[i].sex == "男")
			man++;
	}
	for (int i = 0; i < Expertop; i++)
	{
		if (Exper[i].sex == "男")
			man++;
	}
	for (int i = 0; i < Potop; i++)
	{
		if (Po[i].sex == "男")
			man++;
	}
	for (int i = 0; i < T_Ptop; i++)
	{
		if (T_P[i].sex == "男")
			man++;
	}
	cout << "男员工的数量为" << man << endl;
}
//女员工数量
void Statisticwomen()
{
	int women = 0;
	for (int i = 0; i < Teatop; i++)
	{
		if (Tea[i].sex == "女")
			women++;
	}
	for (int i = 0; i < Expertop; i++)
	{
		if (Exper[i].sex == "女")
			women++;
	}
	for (int i = 0; i < Potop; i++)
	{
		if (Po[i].sex == "女")
			women++;
	}
	for (int i = 0; i < T_Ptop; i++)
	{
		if (T_P[i].sex == "女")
			women++;
	}
	cout << "女员工的数量为" << women << endl;
}
//年龄段统计
void Statisticage()
{
	int age1, age2;
	cout << "年龄区间:age1="; cin >> age1;
	cout << "年龄区间:age2="; cin >> age2;
	int num = 0;
	for (int i = 0; i < Teatop; i++)
	{
		if (Tea[i].age > age1&& Tea[i].age < age2)
			num++;
	}
	for (int i = 0; i < Expertop; i++)
	{
		if (Exper[i].age > age1&&Exper[i].age < age2)
			num++;
	}
	for (int i = 0; i < Potop; i++)
	{
		if (Po[i].age > age1&&Po[i].age < age2)
			num++;
	}
	for (int i = 0; i < T_Ptop; i++)
	{
		if (T_P[i].age > age1&&T_P[i].age < age2)
			num++;
	}
	cout << age1 << "～" << age2 << "的数量为" << num << endl;
}
int main()
{
	string password = "1";	//初始密码
	string inputpass;	//输入密码
	int f = 3;			//输入次数
	int c;		//主菜单选择

	//定义四类人员,调用菜单
	Teacher mt;
	Experiment me;
	Politician mp;
	Tea_Po mtp;

	cout << endl;
	cout << "  |￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣|" << endl;
	cout << "  |      高校人员信息管理系统    |" << endl;
	cout << "  |______________________________|" << endl << endl;

	while (true)
	{
		cout << "请输入密码" << endl; cin >> inputpass;
		if (inputpass == password)
		{
			cout << "密码正确" << endl;
			break;
		}
		else
		{
			cout << "密码错误" << endl;
			if (f > 1)
			{
				cout << "你还有" << f - 1 << "次机会" << endl;
				f--;
			}
			else
				exit(0);
		}
	}
	system("pause");
	while (true)
	{
		system("cls");
		cout << endl;
		cout << "  |￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣|" << endl;
		cout << "  |      高校人员信息管理系统    |" << endl;
		cout << "  |   1.教师管理                 |" << endl;
		cout << "  |   2.实验员管理               |" << endl;
		cout << "  |   3.行政人员管理             |" << endl;
		cout << "  |   4.教师兼行政人员管理管理   |" << endl;
		cout << "  |   0.退出                     |" << endl;
		cout << "  |______________________________|" << endl << endl;
		cout << "请选择" << endl;
		cin >> c;
		switch (c)
		{
		case 1:
			Tea_menu(mt);
			break;
		case 2:
			Exper_menu(me);
			break;
		case 3:
			P_menu(mp);
			break;
		case 4:
			TeaPo_menu(mtp);
			break;
		case 0:break;
		default:
			cout << "输入错误请重新输入" << endl;
			system("pause");
			break;
		}
		if (c == 0)
			break;
	}


	return 0;
}