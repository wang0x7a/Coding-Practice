#include <iostream>

using namespace std;

int flip(int a, int pos);
void print(int a) {
  for (int i = 0; i <= 15; i++) {
    int tmp = a;
    cout << ((tmp >> i) & 1) << " ";

    if (i % 4 == 3)
      cout << endl;
  }
  cout << endl;
}

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
  int b = flip(a, 0);
  int c = flip(a, 4);
  int d = flip(a, 3);
  int e = flip(a, 6);
  int f = flip(a, 15);
  print(a);
  print(b);
  print(c);
  print(d);
  print(e);
  print(f);
  */
}

int flip(int a, int pos) {
  int row = pos / 4;
  int col = pos % 4;

  for (int i = row - 1; i <= row + 1; i++)
    for (int j = col - 1; j <= col + 1; j++) {
      if (i < 0 || i > 3 || j < 0 || j > 3)
        continue;

      if (((i == row - 1) && (j == col - 1)) 
          || ((i == row - 1) && (j == col + 1))
          || ((i == row + 1) && (j == col - 1))
          || ((i == row + 1) && (j == col + 1)))
        continue;

      int shift = i * 4 + j;

      int mask = 1 << shift;

      if (mask & a)
        a = (~mask) & a;
      else
        a = mask | a;
    }

  return a;
}
