#include <iostream>

#define MAX_PIECE_NUM 64

using namespace std;

typedef struct {
  unsigned long visited;

  int curr_sum;
  int rest_sum;
} Record;

int pieces[MAX_PIECE_NUM];
int piece_num;
int piece_len_sum;
unsigned long all_visited;

void solve();
void solve_helper(int tgt_value, Record record, int idx, bool& res);

void print();
unsigned long get_all_visited(int piece_num);

int main() {
  int n;

  while (cin >> n && n != 0) {
    piece_num = n;
    piece_len_sum = 0;

    all_visited = get_all_visited(piece_num);

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
  /*
  for (int tgt_value = pieces[piece_num - 1]; tgt_value <= 6;
      tgt_value++) {
  */
    if (piece_len_sum % tgt_value)
      continue;

    Record record;
    record.curr_sum = 0;
    record.visited  = 0;
    record.rest_sum = piece_len_sum;

    solve_helper(tgt_value, record, piece_num - 1, res);
    if (res) {
      cout << tgt_value << endl;
      break;
    }
  }

  return;
}

void solve_helper(int tgt_value, Record record, int idx, bool& res) {
  /* find a legal solution */
  if (record.rest_sum == 0 && record.visited == all_visited) {
    res = true;
    return;
  }

  /* find a legal subset, whose sum equals to the target value */
  if (record.curr_sum == tgt_value) {
    record.rest_sum -= tgt_value;
    record.curr_sum = 0;

    solve_helper(tgt_value, record, piece_num - 1, res);

    return;
  }

  /*if (idx < 0 || record.curr_sum + pieces[idx] > tgt_value) {*/
  /* TODO
   * Figure out why the statement above does not work
   * */
  if (idx < 0 || record.curr_sum > tgt_value) {
    return;
  }

  /* don't select the piece to which the idx points */
  int next_idx = idx - 1;
  while (next_idx >= 0) {
    if (record.curr_sum + pieces[next_idx] <= tgt_value)
      break;

    next_idx--;
  }

  /*
  if (next_idx < 0)
    return;
  */

  Record record_copy = record;
  solve_helper(tgt_value, record_copy, next_idx, res);

  /* select the piece indexed by idx */
  unsigned long mask = 1 << idx;

  /* current piece was not visited */
  if ((mask & record.visited) == 0) {
    record.curr_sum += pieces[idx];

    record.visited = mask | record.visited;
    next_idx = idx - 1;
    while (next_idx >= 0) {
      if (record.curr_sum + pieces[next_idx] <= tgt_value)
        break;

      next_idx--;
    }

    /*
    if (next_idx < 0)
      return;
    */
    
    solve_helper(tgt_value, record, next_idx, res);
  }
}

void print() {
  for (int i = 0; i < piece_num; i++)
    cout << pieces[i] << " ";

  cout << endl;
}
