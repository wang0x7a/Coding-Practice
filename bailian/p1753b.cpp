#include <iostream>
#include <climits>

using namespace std;

int flip(int a, int pos);
void flip(int* a, int pos);
void print(int a);
int solve(int a, int tgt);
int topDown2LeftRight(int a);
int reverseDirection(int a);

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

  int arr[4];
  arr[0] = a;
  /*
  print(arr[0]);
  arr[2] = reverseDirection(a);
  print(arr[2]);
  */
  arr[1] = reverseDirection(a);
  arr[2] = topDown2LeftRight(a);
  arr[3] = reverseDirection(arr[2]);

  int minFlip = INT_MAX;
  for (int i = 0; i < 2; i++)
    for (int j = 0; j < 4; j++) {
      int tmp = solve(arr[j], i);

      if (tmp < INT_MAX)
        minFlip = tmp;
    }

  if (minFlip == INT_MAX)
    cout << "Impossible" << endl;
  else
    cout << minFlip << endl;
}

int topDown2LeftRight(int a) {
  int ret   = 0;
  int shift = 0;
  for (int col = 0; col < 4; col++) {
    for (int row = 0; row < 4; row++) {
      int pos = row * 4 + col;
      int tmp = a;

      ret = ret | (((tmp >> pos) & 1) << shift);

      shift++;
    }
  }

  return ret;
}

int reverseDirection(int a) {
  int ret = 0;

  int mask = 0xf;

  int shift = 12;
  while (a) {
    ret = ret | ((a & mask) << shift);

    a = a >> 4;
    shift -= 4;
  }

  return ret;
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
    //print(a);
    step++;
  }

  return step;
}
