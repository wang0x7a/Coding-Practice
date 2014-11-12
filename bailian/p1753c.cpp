#include <iostream>
#include <climits>

#define EDGE_LEN 4

using namespace std;

void print(int a);
void flipOnce(int& a, int pos);

int main() {
  int a = 0;

  for (int i = 0; i < EDGE_LEN; i++) {
    string line;
    cin >> line;

    for (int j = 0; j < EDGE_LEN; j++) {
      char c = line[j];

      int tmp = (c == 'b') ? 1 : 0;

      int shift = EDGE_LEN * EDGE_LEN - 1 - i * EDGE_LEN - j;

      a = (tmp << shift) | a;
    }
  }

  /*
  print(a);
  cout << "===" << endl;
  flip(a, 0);
  print(a);
  cout << "===" << endl;
  flip(a, 4);
  print(a);
  cout << "===" << endl;
  */
}

void flipOnce(int& a, int pos) {
  int row = pos / 4;
  int col = pos % 4;

  for (int i = row - 1; i <= row + 1; i++)
    for (int j = col - 1; j <= col + 1; j++) {
      if (i < 0 || i >= EDGE_LEN || j < 0 || j >= EDGE_LEN)
        continue;

      if (((i == row - 1) && (j == col - 1 || j == col + 1))
          || ((i == row + 1) && (j == col - 1 || j == col + 1)))
        continue;

      int shift = i * EDGE_LEN + j;
      int tmp = a;

      tmp = (tmp >> shift) & 1;
      int mask = 1 << shift;

      if (tmp)
        a = a & (~mask);
      else
        a = a | mask;
    }

  return;
}

void print(int a) {
  for (int shift = EDGE_LEN * EDGE_LEN - 1; shift >= 0; shift--) {
    int tmp = a;

    int digit = (tmp >> shift) & 1;

    cout << digit;

    if (shift % EDGE_LEN == 0)
      cout << endl;
  }
}
