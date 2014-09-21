#include <iostream>
#include <cmath>

#define PI 3.1415

using namespace std;

int main() {
  int num;

  cin >> num;

  float x, y;
  int i = 1;
  while (i <= num && (cin >> x >> y)) {
  //for (int i = 1; i <= num; i++) {
  //  cin >> x >> y;

    float s = (x * x + y * y) * PI / 2; 

    int year = ceil(s / 50);

    cout << "Property " << i << ":" << " This property will begin "
      << "eroding in year " << year << "." << endl;
    
    i++;
  }

  cout << "END OF OUTPUT." << endl;
}
