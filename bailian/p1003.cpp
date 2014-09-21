#include <iostream>
#include <cmath>

#define MAX_CARD_NUM 280

using namespace std;

int main() {
  float l;

  while (cin >> l) {
    if (l == 0.00)
      break;

    float acc = 0.0, prev = 0.0;
    for (int i = 2; i <= MAX_CARD_NUM; i++) {
      acc += 1.0 / i;

      if (acc >= l && prev < l) {
        cout << i - ceil(acc - l) << " card(s)" << endl; 
        acc = 0.0;
        prev = 0.0;
        break;
      }

      prev = acc;
    }
  }
}
