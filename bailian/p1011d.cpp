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

int get_next_idx(int idx, unsigned long visited, int tgt_value, int acc);
bool dfs(int idx, int tgt_value, int acc, unsigned long visited, int rest);

int get_next_idx(int idx, int tgt_value, int acc);
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
  for (int tgt_value = pieces[piece_num - 1]; tgt_value <= piece_len_sum;
      tgt_value++) {

    if (piece_len_sum % tgt_value)
      continue;

    //res = dfs(piece_num - 1, tgt_value, 0, 0, piece_len_sum);
    res = dfs(piece_num - 1, tgt_value, 0, piece_len_sum);
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

  bool res1 = false;
  int next_idx;

  next_idx = get_next_idx(idx, visited, tgt_value, acc);

  res1 = dfs(next_idx, tgt_value, acc, visited, rest);

  unsigned long mask = 1 << idx;
  if ((mask & visited) == 0) {
    visited = visited | mask;

    acc += pieces[idx];
  }

  next_idx = get_next_idx(idx, visited, tgt_value, acc);

  bool res2 = dfs(next_idx, tgt_value, acc, visited, rest);

  return res1 || res2;
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

bool dfs(int idx, int tgt_value, int acc, int rest) {
  if (rest == 0)
    return true;

  if (acc == tgt_value)
    return true && dfs(piece_num - 1, tgt_value, 0, rest - acc);

  if (idx < 0 || acc > tgt_value)
    return false;

  bool res = false;
  int next_idx;

  next_idx = get_next_idx(idx, tgt_value, acc);

  res = dfs(next_idx, tgt_value, acc, rest);

  if (res)
    return true;

  if (!is_visited[idx]) {
    acc += pieces[idx];

    is_visited[idx] = true;

    next_idx = get_next_idx(idx, tgt_value, acc);
    res = dfs(next_idx, tgt_value, acc, rest);

    if (res)
      return true;

    is_visited[idx] = false;
  }

  return res;
}

int get_next_idx(int idx, int tgt_value, int acc) {
  int next_idx = idx - 1;

  while (next_idx >= 0) {
    if (!is_visited[next_idx] && acc + pieces[next_idx] <= tgt_value)
      break;

    next_idx--;
  }

  return next_idx;
}


void print() {
  for (int i = 0; i < piece_num; i++)
    cout << pieces[i] << " ";

  cout << endl;
}
