#include <iostream>

using namespace std;

int get_cycle(int n) {
  int cnt = 1;
  while (n != 1) {
    // if using n % 2 == 1, it will cost extra 10 ms!
    // TODO: figure out why
    if (n % 2)
      n = 3 * n + 1;
    else
      n /= 2;
    cnt++;
  }

  return cnt;
}

int main() {
  int i, j;

  while (cin >> i >> j) {
    int x = (i > j) ? j : i;
    int y = (i > j) ? i : j;

    int max_cycle = 0;
    int tmp = 0;
    for (int k = x; k <= y; k++) {
      tmp = get_cycle(k);

      if (tmp > max_cycle)
        max_cycle = tmp;
    }

    cout << i << " " << j << " " << max_cycle << endl;
  } 
}
