#include <iostream>

using namespace std;

int main() {
  int a = 0;

  for (int i = 0; i < 4; i++) {
    string line;
    cin >> line;

    for (int j = 0; j < 4; j++) {
      char c = line[j];

      int tmp = (c == 'b') ? 1 : 0;

      int shift = i * 4 + j;

      a = a | (tmp << shift);
    }
  }

  /*
  for (int i = 0; i <= 15; i++) {
    int tmp = a;
    cout << ((tmp >> i) & 1) << " ";
  }
  cout << endl;
  */
}
