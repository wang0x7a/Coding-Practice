#include <iostream>
#include <climits>
#include <queue>
#include <cstring>

#define EDGE_LEN      4
#define MAX_STATE_NUM 65536

using namespace std;

void print(int a);
int flipOnce(int a, int pos);

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

  if (a == 0 || a == 65535) {
    cout << 0 << endl;
    return 0;
  }

  bool state[MAX_STATE_NUM];
  memset(state, false, sizeof(state));
  queue<int> q;
  q.push(a);
  
  int level = 0, minStep = INT_MAX;
  int thisLevelCnt = 0, lastLevelCnt = 1;
  while (!q.empty()) {
    int b = q.front();
    q.pop();
    lastLevelCnt--;

    for (int i = 0; i < EDGE_LEN * EDGE_LEN; i++) {
      int c = flipOnce(b, i); 

      if (state[c])
        continue;

      thisLevelCnt++;

      if (c == 0 || c == 65535) {
        minStep = ++level;
        break;
      }

      q.push(c);
      state[c] = true;
    }

    if (minStep < INT_MAX)
      break;

    if (lastLevelCnt == 0) {
      level++;
      lastLevelCnt = thisLevelCnt;
      thisLevelCnt = 0;
    }
  }


  if (minStep < INT_MAX)
    cout << minStep << endl;
  else
    cout << "Impossible" << endl;

  /*
  print(a);
  cout << "===" << endl;
  int b = flipOnce(a, 0);
  print(b);
  cout << "===" << endl;
  int c = flipOnce(b, 4);
  print(c);
  cout << "===" << endl;
  */
}

int flipOnce(int a, int pos) {
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

  return a;
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
