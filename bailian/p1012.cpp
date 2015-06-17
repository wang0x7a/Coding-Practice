#include <iostream>

using namespace std;

bool execute(int k, int m);

int main() {
  int k;
  int m;

  while (cin >> k && k) {
    for (m = k + 1; ;m++) {

      if (execute(k, m)) {
        cout << m << endl;
        break;
      }

    }
  }
}

bool execute(int k, int m) {
  int n = 2 * k;
  int rest = n;
  int e = 0;

  bool res = true;

  for (int i = 1; i <= k; i++) {
    rest = n - i + 1;

    e = (e + m - 1) % rest;

    if (e < k) {
      res = false;
      break;
    }
  }

  return res;
}
