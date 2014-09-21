#include <iostream>

#define MAX_CARD_NUM 280

using namespace std;

int main() {
  float l;

  while (cin >> l) {
    if (l == 0.00)
      break;

    float acc = 0.0;
    for (int i = 2; i <= MAX_CARD_NUM; i++) {
      acc += 1.0 / i;

      if (acc > l) {
        // since the length of the input is only 3, while the length of acc
        // would always be longer that 3, it is very unlikely that acc == l
        cout << i - 1 << " card(s)" << endl; 
        acc = 0.0;
        break;
      }
    }
  }
}
