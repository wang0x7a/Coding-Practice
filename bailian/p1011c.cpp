#include <iostream>
#include <algorithm>

#define MAX_PIECE_NUM 64

using namespace std;

int pieces[MAX_PIECE_NUM];
int piece_num;
int piece_len_sum;

void solve();

int get_next_idx(int idx, unsigned long visited, int tgt_value, int acc);
bool dfs(int idx, int tgt_value, int acc, unsigned long visited, int rest);

void print();

int main() {
  int n;

  while (cin >> n && n != 0) {
    piece_num = n;
    piece_len_sum = 0;

    for (int i = 0; i < piece_num; i++) {
      cin >> n;
      pieces[i] = n;
      piece_len_sum += n;
    }

    solve();
  }
}

unsigned long get_all_visited(int piece_num) {
  unsigned long res = 0;

  for (int i = 0; i < piece_num; i++)
    res = res | (1 << i);

  return res;
}

void solve() {
  sort(pieces, pieces + piece_num);

  bool res = false;
  for (int tgt_value = pieces[piece_num - 1]; tgt_value <= piece_len_sum;
      tgt_value++) {

    if (piece_len_sum % tgt_value)
      continue;

    res = dfs(piece_num - 1, tgt_value, 0, 0, piece_len_sum);

    if (res) {
      cout << tgt_value << endl;
      break;
    }
  }

  return;
}

bool dfs(int idx, int tgt_value, int acc, unsigned long visited, int rest) {
  if (rest == 0)
    return true;

  if (acc == tgt_value)
    return true && dfs(piece_num - 1, tgt_value, 0, visited, rest - acc);

  if (idx < 0 || tgt_value < acc)
    return false;

  bool res = false;
  int next_idx;

  next_idx = get_next_idx(idx, visited, tgt_value, acc);

  res = dfs(next_idx, tgt_value, acc, visited, rest);

  if (res)
    return true;

  unsigned long mask = 1 << idx;
  if ((mask & visited) == 0) {
    visited = visited | mask;

    acc += pieces[idx];
  }

  next_idx = get_next_idx(idx, visited, tgt_value, acc);

  res = dfs(next_idx, tgt_value, acc, visited, rest);

  return res;
} 

int get_next_idx(int idx, unsigned long visited, int tgt_value, int acc) {
  int next_idx = idx - 1;
  unsigned long mask = 1 << (idx - 1);

  while (next_idx >= 0) {
    unsigned long is_visited = mask & visited;

    if (acc + pieces[next_idx] <= tgt_value && is_visited == 0)
      break;

    next_idx--;
    mask = mask << 1;
  }

  return next_idx;
}


void print() {
  for (int i = 0; i < piece_num; i++)
    cout << pieces[i] << " ";

  cout << endl;
}
