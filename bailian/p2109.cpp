#include <iostream>
#include <cmath>

using namespace std;

int main() {
  double p, n;

  while (cin >> n >> p) {
    cout << pow(10, log10(p) / n) << endl;
  }
}
