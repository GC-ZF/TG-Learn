//#include <iostream>
//#include <string>
//using namespace std;
//#define PI 3.1415926
//
//class Circle
//{
//public:
//	double R;
//	virtual void show() = 0;
//private:
//
//};
//
////球
//class Ball :public Circle
//{
//public:
//	Ball(double radius)
//	{
//		R = radius;
//	}
//	void show()
//	{
//		cout << "球的表面积为  " << 4 * PI*R*R << "  球的体积为  " << 4.00 / 3.00*PI*R*R*R << endl;
//	}
//private:
//
//};
//
////圆柱
//class Cylinder :public Circle
//{
//public:
//	Cylinder(double radius, double heigh)
//	{
//		R = radius;
//		H = heigh;
//	}
//	void show()
//	{
//		cout << "圆柱的表面积为  " << 2 * PI*R*R + PI * 2 * R*H << "  圆柱的体积为  " << PI * R*R*H << endl;
//	}
//private:
//	double H;
//};
//
////圆锥
//class Cone :public Circle
//{
//public:
//	Cone(double radius, double heigh, double line)
//	{
//		R = radius;
//		H = heigh;
//		L = line;
//	}
//	void show()
//	{
//		cout << "圆锥的表面积为  " << PI * R*L + PI * R*R << "  圆锥的体积为  " << PI * R*R*H / 3 << endl;
//	}
//private:
//	double H;
//	double L;
//};
//
//int main()
//{
//	Circle * ball = new Ball(2);
//	ball->show();
//	delete ball;
//
//	Circle * cylinder = new Cylinder(2, 2);
//	cylinder->show();
//	delete cylinder;
//
//	Circle * cone = new Cone(2, 2, 2);
//	cone->show();
//	delete cone;
//
//	system("pause");
//	return 0;
//}