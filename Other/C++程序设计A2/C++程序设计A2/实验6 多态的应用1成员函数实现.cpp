//#include<iostream>
//using namespace std;
//
//class Point
//{
//private:
//	double x, y;
//public:
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
//	Point operator + (Point &p2) 
//	{
//		Point p;
//		p.x =x+ p2.x;
//		p.y =y+ p2.y;
//		return p;
//	}
//	Point operator - (Point &p2)
//	{
//		Point p;
//		p.x = x - p2.x;
//		p.y = y - p2.y;
//		return p;
//	}
//	//前增
//	Point operator ++()
//	{
//		//先加
//		x++;
//		y++;
//		//后使用
//		return *this;
//	}
//	//后增
//	Point operator ++(int)
//	{
//		//先使用(备份)
//		Point temp=*this;
//		//后加
//		++(*this);
//		return temp;
//	}
//	//前减
//	Point operator --()
//	{
//		x--;
//		y--;
//		return *this;
//	}
//	//后减
//	Point operator --(int)
//	{
//		Point temp = *this;
//		--(*this);
//		return temp;
//	}
//};
//
//int main()
//{
//	Point p0(1, 1);
//	Point p1(1, 1);
//	cout << "p0";
//	p0.show();
//	cout << "p1";
//	p1.show();
//	cout << "p0+p1=";
//	Point p2 = p0 + p1;
//	p2.show();
//	Point p3 = p1 - p0;
//	cout << "p1-p0=";
//	p3.show();
//	cout << "++p0:";
//	(++p0).show();
//	cout << "p0++:";
//	(p0++).show();
//	cout << "p0++后p0:";
//	p0.show();
//	cout << "--p1:";
//	(--p1).show();
//	cout << "p1--:";
//	(p1--).show();
//	cout << "p1--后p1:";
//	p1.show();
//	
//	system("pause");
//	return 0;
//}
