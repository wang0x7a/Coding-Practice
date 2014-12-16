#include <iostream>

#define MAX_PIECE_NUM 64

using namespace std;

int pieces[MAX_PIECE_NUM];
int piece_num;
int piece_len_sum;

int solve();
void print();

int main() {
  int n;

  int res;
  while (cin >> n && n != 0) {
    piece_num = n;
    piece_len_sum = 0;

    for (int i = 0; i < piece_num; i++) {
      cin >> n;
      pieces[i] = n;
      piece_len_sum += n;
    }

    res = solve();

    cout << res << endl;
    //print();
  }
}

int solve() {
  int res;

  sort(pieces, pieces + piece_num);

  return res;
}

void print() {
  for (int i = 0; i < piece_num; i++)
    cout << pieces[i] << " ";

  cout << endl;
}
