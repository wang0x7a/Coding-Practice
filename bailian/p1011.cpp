#include <iostream>

#define MAX_PIECE_NUM 64

using namespace std;

int pieces[MAX_PIECE_NUM];
int piece_num;
int piece_len_sum;
unsigned long all_visited;


void solve();
void solve_helper(int tgt_value, int curr_sum, int rest_sum, 
    int curr_grp, int visited, bool& res);
void print();
unsigned long get_all_visited(int piece_num);

int main() {
  int n;

  int res;
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

    cout << res << endl;
    //print();
  }
}

unsigned long get_all_visited(int piece_num) {
  unsigned long res = 0;

  for (int i = 0; i < piece_num; i++)
    res = res || (1 << i);

  return res;
}

void solve() {
  sort(pieces, pieces + piece_num);

  int tgt_value = pieces[piece_num - 1];
  bool res = false;
  for (int tgt_value = pieces[piece_num - 1]; tgt_value < piece_len_sum;
      tgt_value++) {

    if (piece_len_sum % tgt_value)
      continue;

    solve_helper(tgt_value, 0, piece_len_sum, 0, 0, res);
    if (res) {
      cout << tgt_value << endl;
      break;
    }
  }

  return;
}

void solve_helper(int tgt_value, int curr_sum, int rest_sum, 
    int curr_grp, int visited, bool& res) {

  if (rest_sum % tgt_value) {
    res = false;
    return;
  }

  return;
}

void print() {
  for (int i = 0; i < piece_num; i++)
    cout << pieces[i] << " ";

  cout << endl;
}
