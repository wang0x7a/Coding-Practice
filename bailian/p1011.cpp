#include <iostream>

#define MAX_PIECE_NUM 64

using namespace std;

typedef struct {
  //unsigned long curr_grp;
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

    //cout << res << endl;
    //print();
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

  int tgt_value = pieces[piece_num - 1];
  bool res = false;
  for (int tgt_value = pieces[piece_num - 1]; tgt_value <= piece_len_sum;
      tgt_value++) {

    if (piece_len_sum % tgt_value)
      continue;

    Record record;
    record.curr_sum = 0;
    //record.curr_grp = 0;
    record.visited  = 0;
    record.rest_sum = piece_len_sum;

    //solve_helper(tgt_value, 0, piece_len_sum, 0, 0, 0, res);
    solve_helper(tgt_value, record, 0, res);
    if (res) {
      cout << tgt_value << endl;
      break;
    }
  }

  return;
}

void solve_helper(int tgt_value, Record record, int idx, bool& res) {
  // find a legal solution
  if (record.rest_sum == 0 && record.visited == all_visited) {
    res = true;
    return;
  }

  // find a legal subset, whose sum equals to the target value
  if (record.curr_sum == tgt_value) {
    record.rest_sum -= tgt_value;
    record.curr_sum = 0;
    
    // merge the subset into the visited set
    //record.visited = (record.visited | record.curr_grp);
    //record.curr_grp = 0;

    // start to search another possible subset from index#0
    solve_helper(tgt_value, record, 0, res);

    return;
  }

  // idx exceeds piece_num, while the sum of current group is not equal to 
  // the target value
  // 1) ? then start new search, reseting the current group
  // 2) return immediately 
  if (idx >= piece_num || record.curr_sum > tgt_value) {
    return;
  }

  // don't select the piece to which the idx points
  Record record_copy = record;
  solve_helper(tgt_value, record_copy, idx + 1, res);

  // select the piece indexed by idx
  unsigned long mask = 1 << idx;

  // current piece was not visited
  if ((mask & record.visited) == 0) {
    record.curr_sum += pieces[idx];

    //record.curr_grp = mask | record.curr_grp;
    record.visited  = mask | record.visited;
    solve_helper(tgt_value, record, idx + 1, res);
  }

}

void print() {
  for (int i = 0; i < piece_num; i++)
    cout << pieces[i] << " ";

  cout << endl;
}
