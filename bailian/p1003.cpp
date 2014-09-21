#include <iostream>

#define MAX_CARD_NUM 280

using namespace std;

int main() {
  float l;

  while (cin >> l) {
    if (l == 0.00)
      break;

    float acc = 0.0;
    for (int i = 2; i <= MAX_CARD_NUM + 1; i++) {
      acc += 1.0 / i;

      if (acc >= l) {
        cout << i - 1 << " card(s)" << endl; 
        acc = 0.0;
        break;
      }
    }
  }
}
