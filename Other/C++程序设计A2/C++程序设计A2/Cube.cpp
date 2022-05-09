#include "Cube.h"

void Cube::set()
{
	cin >> c_length >> c_wide >> c_height;
}
double Cube::show()
{
	double c_volume = c_length * c_wide*c_height;
	return c_volume;
}
