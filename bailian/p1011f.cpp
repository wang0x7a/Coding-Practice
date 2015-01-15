#include <iostream>
#include <algorithm>
#include <cstring>

#define MAX_PIECE_NUM 64

using namespace std;

int pieces[MAX_PIECE_NUM];
int piece_num;
int piece_len_sum;
bool is_visited[MAX_PIECE_NUM];

void solve();

bool dfs(int idx, int tgt_value, int acc, int rest);

void print();

int main() {
  int n;

  while (cin >> n && n != 0) {
    piece_num = n;
    piece_len_sum = 0;

    memset(is_visited, false, sizeof(is_visited));

    for (int i = 0; i < piece_num; i++) {
      cin >> n;
      pieces[i] = n;
      piece_len_sum += n;
    }

    solve();
  }
}

void solve() {
  sort(pieces, pieces + piece_num);

  bool res = false;
  for (int tgt_value = pieces[piece_num - 1]; 
      tgt_value <= piece_len_sum; tgt_value++) {

    if (piece_len_sum % tgt_value)
      continue;

    res = dfs(piece_num - 1, tgt_value, 0, piece_len_sum);

    if (res) {
      cout << tgt_value << endl;
      break;
    }
  }

  return;
}

bool dfs(int idx, int tgt_value, int acc, int rest) {
  if (rest == 0)
    return true;

  if (idx < 0)
    return false;

  int i;
  for (i = idx; i >= 0; i--) {
    
    if (!is_visited[i] && acc + pieces[i] <= tgt_value) {

      is_visited[i] = true;
      if (acc + pieces[i] == tgt_value) {

        if (dfs(piece_num - 1, tgt_value, 0, rest - tgt_value))
          return true;
      }
      else {

        if (dfs(i - 1, tgt_value, acc + pieces[i], rest))
          return true;
      }
      is_visited[i] = false;

      // pruning
      if (pieces[i] == 0 || acc == 0)
        return false;

      while (i >= 1 && pieces[i - 1] == pieces[i])
        i--;
    }
  }

  return false;
}

void print() {
  for (int i = 0; i < piece_num; i++)
    cout << pieces[i] << " ";

  cout << endl;
}
