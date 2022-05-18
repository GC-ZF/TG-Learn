#pragma once
#include <iostream>
using namespace std;

class Cube
{
public:

	void set();
	//输出函数
	double show();
private:
	double c_length; //长
	double c_wide; //宽
	double c_height; //高
};