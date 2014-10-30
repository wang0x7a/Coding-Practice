#include <iostream>

using namespace std;

int flip(int a, int pos);
void flip(int* a, int pos);
void print(int a);
int solve(int a, int tgt);

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

  int minFlip = INT_MAX;

  for (int i = 0; i < 16; i++) {
  }
}

void print(int a) {
  for (int i = 0; i <= 15; i++) {
    int tmp = a;
    cout << ((tmp >> i) & 1) << " ";

    if (i % 4 == 3)
      cout << endl;
  }
  cout << endl;
}

void flip(int* a, int pos) {
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

      if (mask & *a)
        *a = (~mask) & *a;
      else
        *a = mask | *a;
    }
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

int solve(int a, int tgt) {
  int step = 0;

  for (int i = 0; i < 16; i++) {
    int tmp = a;
    
    int bit = (tmp >> i) & 1;
    if (bit == tgt)
      continue;

    int pos = i + 4;
    if (pos >= 16)
      return INT_MAX;
    
    flip(&a, pos);
    print(a);
    step++;
  }

  return step;
}
