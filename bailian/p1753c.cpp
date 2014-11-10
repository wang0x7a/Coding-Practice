#include <iostream>
#include <climits>

#define EDGE_LEN 4

using namespace std;

void print(int a);
void flipOnce(int& a, int pos);
void dfs(int a, int pos, int tgt, bool isVisited[], int& minStep, int step);
bool isAllVisited(bool isVisited[]);

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

  bool isVisited[EDGE_LEN * EDGE_LEN];
  memset(isVisited, false, sizeof(isVisited));
  int minStep = INT_MAX;
  dfs(a, 0, 0, isVisited, minStep, 0);
  memset(isVisited, false, sizeof(isVisited));
  dfs(a, 0, 1, isVisited, minStep, 0);

  if (minStep < INT_MAX)
    cout << minStep << endl;
  else
    cout << "Impossible" << endl;


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

void dfs(int a, int pos, int tgt, bool isVisited[], int& minStep, int step) {
  if (a == 0 || a == 0xf) {
    if (minStep > step)
      minStep = step;
    return;
  }

  if (isAllVisited(isVisited)) {
    minStep = INT_MAX;
    return;
  }

  int tmp = a;
  int bit = (tmp >> pos) & 1;

  if (bit == tgt) {
    if (!isVisited[pos]) {
      isVisited[pos] = true;
      flipOnce(a, pos);
      step++;
      dfs(a, (pos + 1) % (EDGE_LEN * EDGE_LEN), tgt, isVisited, minStep, step);
      flipOnce(a, pos);
      isVisited[pos] = false;
      step--;
      dfs(a, (pos + 1) % (EDGE_LEN * EDGE_LEN), tgt, isVisited, minStep, step);
    }
    else
      dfs(a, (pos + 1) % (EDGE_LEN * EDGE_LEN), tgt, isVisited, minStep, step);
  }
  else {
    dfs(a, (pos + 1) % (EDGE_LEN * EDGE_LEN), tgt, isVisited, minStep, step);
  }
}

bool isAllVisited(bool isVisited[]) {
  int totalCount = EDGE_LEN * EDGE_LEN;

  for (int i = 0; i < totalCount; i++)
    if (! isVisited[i])
      return false;

  return true;
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
