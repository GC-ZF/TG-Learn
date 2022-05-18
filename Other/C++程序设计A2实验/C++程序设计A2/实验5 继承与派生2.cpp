//#include <iostream>
//using namespace std;
//#define PI 3.1415926
//
////点类
//class Point
//{
//public:
//	Point() {};
//	Point(double px, double py)
//	{
//		this->px = px;
//		this->py = py;
//	}
//	void setpx(double px)
//	{
//		this->px = px;
//	}
//	void setpy(double py)
//	{
//		this->py = py;
//	}
//	double getpx()
//	{
//		return px;
//	}
//	double getpy()
//	{
//		return py;
//	}
//	double px;
//	double py;
//};
//
//
////圆类
//class Circle :public Point
//{
//public:
//	Circle() {};
//	Circle(double r, Point p)
//	{
//		this->r = r;
//		px = p.getpx();
//		py = p.getpy();
//	}
//	Circle(double x, double y, double r)
//	{
//		this->r = r;
//		px = x;
//		py = y;
//	}
//	void setR(double r)
//	{
//		this->r = r;
//	}
//	double getR()
//	{
//		return r;
//	}
//	virtual double area()
//	{
//		return PI * r*r;
//	}
//	double r;
//};
//
////圆柱体类
//class Cylinder :public Circle
//{
//public:
//	Cylinder(double h, Circle c)
//	{
//		this->h = h;
//		r = c.getR();
//		px = c.px;
//		py = c.py;
//	}
//	void seth(double h)
//	{
//		this->h = h;
//	}
//	double geth()
//	{
//		return h;
//	}
//	double area()
//	{
//		return 2 * PI*r*r + PI * 2 * r*h;
//	}
//	double volume()
//	{
//		return PI * r*r*h;
//	}
//	double h;
//};
//int main()
//{
//	Point p(0, 0);
//	cout << "测试Point类 p(" << p.getpx() << "," << p.getpy() << ")" << endl;
//
//	Circle c1(2, p);
//	cout << "测试Circle类 c1的半径  " << c1.getR() << "  坐标(" << c1.getpx() << "," << c1.getpy() << ")  面积为  " << c1.area() << endl;
//	c1.setpx(10);
//	c1.setpy(10);
//	c1.setR(10);
//	cout << "测试Circle类 set函数  半径  " << c1.getR() << "  坐标(" << c1.getpx() << "," << c1.getpy() << ")" << endl;
//
//	Circle c2(0, 0, 2);
//	cout << "测试Circle类 c2的半径  " << c2.getR() << "  坐标(" << c2.getpx() << "," << c2.getpy() << ")  面积为  " << c2.area() << endl;
//
//	Cylinder * cy = new Cylinder(1, c1);
//	cout << "测试Cylinder类 cy的半径  " << cy->getR() << "  坐标(" << cy->getpx() << "," << cy->getpy() << ")  面积为  " << cy->area() << " 体积为  " << cy->volume() << "  高为" << cy->geth() << endl;
//	cy->seth(10);
//	cy->setpx(10);
//	cy->setpy(10);
//	cy->setR(10);
//	cout << "测试Cylinder类 set函数  半径  " << cy->getR() << "  坐标(" << cy->getpx() << "," << cy->getpy() << ")  高为" << cy->geth() << endl;
//	delete cy;
//
//	system("pause");
//	return 0;
//}