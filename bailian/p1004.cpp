#include <iostream>
#include <cmath>

using namespace std;

int main() {
  float acc = 0.0;
  float tmp = 0.0;

  for (int i = 0; i < 12; i++) {
    cin >> tmp;
    acc += tmp;
  }

  acc /= 12;
  acc *= 100;
  acc = round(acc);

  cout << "$" << acc / 100 << endl;
}
