#include <iostream>
#include <string>
#include <map>
#include <algorithm>
#include <vector>

using namespace std;

int translate(string s);
void print(int elem, int cnt);

bool cmp(int a, int b) {
  return a < b;
}

int main() {
  int tel_num;
  string s;

  cin >> tel_num;

  //int *a = new int[tel_num];
  int a[tel_num];

  for (int i = 0; i < tel_num; i++) {
    cin >> s;

    a[i] = translate(s);
  }

  sort(a, a + tel_num, cmp);

  int valid_cnt = 0, elem_cnt = 1;
  int curr = 0, prev = 0;
  for (int i = 1; i < tel_num ; i++) {
    curr = a[i];
    prev = a[i - 1];

    if (curr != prev) {
      if (elem_cnt > 1) {
        print(prev, elem_cnt);
        elem_cnt = 1;
        valid_cnt++;
      }
    }
    else
      elem_cnt++;
  }

  if (elem_cnt > 1) {
    print(curr, elem_cnt);
    valid_cnt++;
  }

  if (valid_cnt == 0)
    cout << "No duplicates." << endl;

}

void print(int elem, int cnt) {
  //cout << elem / 10000 << "-" << elem % 10000 << " " << cnt << endl;
  int i = 1;
  int base = 1000000;
  while (base) {
    if (i == 4)
      cout << "-";

    i++;

    cout << elem / base;
    elem %= base;
    base /= 10;
  }

  cout << " " << cnt << endl;

  return;
}

int translate(string s) {
  int res = 0;

  for (int i = 0; i < s.size(); i++) {
    int d = 0;

    if ((s[i] >= 'A' && s[i] <= 'P') || (s[i] >= 'a' && s[i] <= 'p')) {
      s[i] = toupper(s[i]);

      d = (s[i] - 'A') / 3 + 2;
    }
    else if ((s[i] >= 'R' && s[i] <= 'Y') || (s[i] >= 'r' && s[i] <= 'y')) {
      s[i] = toupper(s[i]);

      d = (s[i] - 'A' - 1) / 3 + 2;
    }
    else if (s[i] >= '0' && s[i] <= '9')
      d = s[i] - '0';
    else
      continue;

    res = res * 10 + d;
  }

  return res;
}
