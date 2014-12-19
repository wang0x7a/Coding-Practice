#include <iostream>
#include <algorithm>
#include <string>

#define MAX_PIECE_NUM 64

using namespace std;

int pieces[MAX_PIECE_NUM];
int piece_num;
int piece_len_sum;
int is_visited[MAX_PIECE_NUM];

void solve();

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

  if (acc == tgt_value)
    return true && dfs(piece_num - 1, tgt_value, 0, rest - acc);

  if (idx < 0 || tgt_value < acc)
    return false;

  bool res;
  int next_idx;

  next_idx = get_next_idx(idx, tgt_value, acc);

  res = dfs(next_idx, tgt_value, acc, rest);

  if (res)
    return true;

  if (!is_visited[idx]) {
    is_visited[idx] = true;

    acc += pieces[idx];
  }

  next_idx = get_next_idx(idx, tgt_value, acc);

  res = dfs(next_idx, tgt_value, acc, rest);

  return res;
} 

int get_next_idx(int idx, int tgt_value, int acc) {
  int next_idx = idx - 1;

  while (next_idx >= 0) {
    if (acc + pieces[next_idx] <= tgt_value && !is_visited[next_idx])
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
