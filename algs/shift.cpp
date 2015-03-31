/**
 * Implemented two array shifting algorithms:
 *
 * 1) shift: shift by m steps
 * 2) del_shift: delete the nth element, and make a m-step shift
 * */

#include <iostream>

using namespace std;

void shift(int a[], int n, int steps);
void del_shift(int a[], int n, int d);

int main() {
  int a[] = {0, 1, 2, 3, 4, 5, 6};

  for (int i = 0; i < 7; i++)
    cout << a[i] << " ";

  cout << endl;

  shift(a, 7, 4);

  del_shift(a, 7, 3);
}

void shift(int a[], int n, int steps) {
  for (int i = 0; i < n; i++)
    cout << (a[i] + steps) % n << " ";

  cout << endl;
}

void del_shift(int a[], int n, int d) {
  for (int i = 0; i < n; i++) {
    if (i == d)
      continue;

    cout << ((a[i] + d + 1) % n) % (n - 1) << " ";
  }

  cout << endl;
}
