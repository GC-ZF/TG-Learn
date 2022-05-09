//#include<iostream>
//using namespace std;
//
//class Point
//{
//private:
//	double x, y;
//public:
//	
//	Point() {};
//	Point(double x, double y)
//	{
//		this->x = x;
//		this->y = y;
//	}
//	void show()
//	{
//		cout << "(" << this->x << "," << this->y << ")" << endl;
//	}
//	friend Point operator + (Point &p1,Point &p2);
//	friend Point operator - (Point &p1, Point &p2);
//	friend Point operator ++(Point &p0);
//	friend Point operator ++(Point &p0,int);
//	friend Point operator --(Point &p0);
//	friend Point operator --(Point &p0,int);
//	friend ostream& operator <<(ostream &out, Point &p0);
//	friend istream &operator>>(istream &in, Point &p0);
//};
//Point operator + (Point &p1, Point &p2)
//{
//	Point p;
//	p.x = p1.x + p2.x;
//	p.y = p1.y + p2.y;
//	return p;
//}
//Point operator - (Point &p1, Point &p2)
//{
//	Point p;
//	p.x = p1.x - p2.x;
//	p.y = p1.y - p2.y;
//	return p;
//}
////前增
//Point operator ++(Point &p0)
//{
//	//先加
//	p0.x++;
//	p0.y++;
//	//后使用
//	return p0;
//}
////后增
//Point operator ++(Point &p0,int)
//{
//	//先使用(备份)
//	Point temp = p0;
//	//后加
//	p0.x++;
//	p0.y++;
//	return temp;
//}
////前减
//Point operator --(Point &p0)
//{
//	p0.x--;
//	p0.y--;
//	return p0;
//}
////后减
//Point operator --(Point &p0,int)
//{
//	Point temp = p0;
//	p0.x--;
//	p0.y--;
//	return temp;
//}
//
//ostream &operator <<(ostream &out, Point &p0)
//{
//	out <<"("<< p0.x<<","<<p0.y<<")";
//	return out;
//}
//istream &operator>>(istream &in, Point &p0)
//{
//	in >> p0.x >> p0.y;
//	return in;
//}
//int main()
//{
//	Point p0;
//	cout << "输入p0" << endl;
//	cin >> p0;//提取运算符重载
//	cout << "p0="<< p0 << endl;//流插入运算符重载
//
//	Point p1;
//	cout << "输入p1" << endl;
//	cin >> p1;//提取运算符重载
//	cout << "p1=" << p1 << endl;//流插入运算符重载
//	
//	Point p3 = p0 + p1;//加法重载
//	cout << "p3=p0+p1=" << p3 << endl;
//	Point p4 = p0 - p1;//减法重载
//	cout << "p4=p0-p1=" << p4 << endl;
//	cout << "++p0:";//前置++
//	(++p0).show();
//	cout << "p0++:";//后置++
//	(p0++).show();
//	cout << "p0++后p0:";
//	p0.show();
//	cout << "--p1:";//前置--
//	(--p1).show();
//	cout << "p1--:";//后置--
//	(p1--).show();
//	cout << "p1--后p1:";
//	p1.show();
//
//
//	system("pause");
//	return 0;
//}
